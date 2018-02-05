//This class deals with the user interface components of the project from mouse/keyboard inputs. 
//Anything the user interacts with directly is handled here.
//public class userInterface{} - This class deals with the user interface components of the project from mouse/keyboard inputs
//handles exceptions
//handles toolbar, file selection
//handles mouse input
//handles keyboard input

import java.io.File;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserInterface extends Runner {

	public static final int BUTTON_SIZE_X = 80;
	public static final int BUTTON_SIZE_Y = 70;
	public static final int SIZE_X = 500;
	public static final int SIZE_Y = 600;
	protected int FRAMES_PER_SECOND = 5;
	protected int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	protected double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND_COLOR = Color.LIGHTGRAY;
	protected Scene myScene;
	protected Group root = new Group(); 
	protected Timeline animation;
	protected KeyFrame myFrame;
	protected Grid myGrid;
	protected Simulation mySim;
	protected ArrayList<Rectangle> gridCells = new ArrayList<Rectangle>();
	protected Stage myStage;

	public void setupScene() {

		myFrame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.getKeyFrames().clear();
		animation.setCycleCount(50);
		animation.getKeyFrames().add(myFrame);

		Button startButton = Factory.setButton(165, 470, 135, 38, "START");
		startButton.getStyleClass().add("startButton");
		root.getChildren().add(startButton);

		Button pauseButton = Factory.setButton(165, 512, 135, 38, "STOP");
		pauseButton.getStyleClass().add("pauseButton");
		root.getChildren().add(pauseButton);

		Button stepButton = Factory.setButton(305, 470, 170, 80, "STEP");
		stepButton.getStyleClass().add("stepButton");
		root.getChildren().add(stepButton);

		Button simButton = Factory.setButton(30, 470, 130, 80, "Choose Simulation");
		simButton.getStyleClass().add("stepButton");
		root.getChildren().add(simButton);

		Label title = Factory.setLabel(20, 10, "CELL SOCIETY: Team 5");
		title.getStyleClass().add("titleLabel");
		root.getChildren().add(title);

		TextField speedTextField = Factory.setTextField(350, 560, 125, 30);
		speedTextField.setPromptText("frames/second");
		root.getChildren().add(speedTextField);

		Label speedLabel = Factory.setLabel(302, 565, "Speed:");
		root.getChildren().add(speedLabel);

		Label simLabel = Factory.setLabel(30, 565, "Current Simulation: ");
		root.getChildren().add(simLabel);

		Label conwayLabel = Factory.setLabel(150, 565, "Conway");
		conwayLabel.getStyleClass().add("simLabel");

		Label predpreyLabel = Factory.setLabel(150, 565, "Predator-Prey");
		predpreyLabel.getStyleClass().add("simLabel");

		Label fireLabel = Factory.setLabel(150, 565, "Fire");
		fireLabel.getStyleClass().add("simLabel");

		Alert speedAlert = Factory.setAlert("Wrong User Input", "Please enter a positive integer value");

		Alert fileAlert = Factory.setAlert("Simulation File Chooser", "Incompatible file, please choose a simulation.xml file");

		FileChooser XMLChooser = new FileChooser();
		XMLChooser.setTitle("Open Data File");
		XMLChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		XMLChooser.getExtensionFilters().setAll(new ExtensionFilter("Text Files", "*.xml"));

		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				animation.getKeyFrames().clear();
				animation.setCycleCount(50);
				animation.getKeyFrames().add(myFrame);
				animation.play();
			}
		});

		pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				animation.stop();
			}
		});

		stepButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				animation.stop();
				animation.setCycleCount(1);
				animation.play();
			}
		});

		speedTextField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				try {
					int speed = Integer.parseInt(speedTextField.getText());
					if (speed <= 0) {
						speedAlert.show();
					}
					FRAMES_PER_SECOND = speed;
					System.out.println(speed);
				} catch (NumberFormatException e) {
					System.out.println("Wrong number");
					speedAlert.show();
				}
			}
		});

		simButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { //should be in viewer
				File dataFile = XMLChooser.showOpenDialog(myStage);
				if (dataFile != null) {
					mySim = new ParseXML("simulation").getSimulation(dataFile); //create method for this
					System.out.println(mySim);
					//root.getChildren().add(conwayLabel);
					myGrid = mySim.getGrid();
					int scalingFactorX = 400/myGrid.getWidth();
					int scalingFactorY = 400/myGrid.getHeight();
					for (int i = 0; i < myGrid.getWidth(); i++) {
						for (int j = 0; j < myGrid.getHeight(); j++) {
							Cell cell = myGrid.get(i, j);
							Rectangle add = CellTransformer.toRectangle(cell, scalingFactorX, scalingFactorY);
							gridCells.add(add);
							root.getChildren().add(add);
						}
					}
				} else {
					fileAlert.show();
				}
			}
		});
	}

	protected void step (double elapsedTime) {
		int scalingFactorX = 400/myGrid.getWidth();
		int scalingFactorY = 400/myGrid.getHeight();
		for(int i = 0; i < gridCells.size(); i++) {
			root.getChildren().remove(gridCells.get(i));
		}
		Grid newGrid = mySim.update();
		for (int i = 0; i < newGrid.getWidth(); i++) {
			for (int j=0; j<newGrid.getHeight(); j++) {
				Cell cell = newGrid.get(i, j);
				Rectangle add1 = CellTransformer.toRectangle(cell, scalingFactorX, scalingFactorY);
				root.getChildren().add(add1);
			}
		}
	}

		protected void addSim(Simulation sim) {
			int scalingFactorX = 400/myGrid.getWidth();
			int scalingFactorY = 400/myGrid.getHeight();
			for (int i = 0; i < myGrid.getWidth(); i++) {
				for (int j = 0; j < myGrid.getHeight(); j++) {
					Cell cell = myGrid.get(i, j);
					Rectangle add = CellTransformer.toRectangle(cell, scalingFactorX, scalingFactorY);
					gridCells.add(add);
					root.getChildren().add(add);
				}
			}
		}
}

