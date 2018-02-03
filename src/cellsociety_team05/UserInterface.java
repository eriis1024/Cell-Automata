package cellsociety_team05;
//This class deals with the user interface components of the project from mouse/keyboard inputs. 
//Anything the user interacts with directly is handled here.
//public class userInterface{} - This class deals with the user interface components of the project from mouse/keyboard inputs
//handles exceptions
//handles toolbar, file selection
//handles mouse input
//handles keyboard input

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

//to do: create separate factory classes, write functions for buttons, finish layout, CSS file and final variables for button locations

public class UserInterface extends Runner {
	
	public static final int BUTTON_SIZE_X = 80;
	public static final int BUTTON_SIZE_Y = 70;
	public static final int SIZE_X = 500;
	public static final int SIZE_Y = 600;
	public static final int FRAMES_PER_SECOND = 70;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	protected double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND_COLOR = Color.LIGHTGRAY;
	protected Scene myScene;
	protected Stage myStage;
	protected static Group root = new Group(); //change from static by adding labels directly to dropdown menu
	protected Timeline animation;
	protected KeyFrame myFrame;
	
	public void setupScene() {
		myFrame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.getKeyFrames().clear();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(myFrame);
		
		//add grid to the scene, root.getChildren.add(grid);
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
		
		FileChooser XMLchooser = new FileChooser();
		XMLchooser.setTitle("Select Simulation");
		
		Label title = Factory.setLabel(20, 10, "CELL SOCIETY: Team 5");
		title.getStyleClass().add("titleLabel");
		root.getChildren().add(title);

		TextField speedTextField = Factory.setTextField(350, 560, 125, 30);
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
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
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
		
		simButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				XMLchooser.showOpenDialog(myStage); //error
				//check if file is null or if the file chosen is of the wrong format
				//if XML file = simulation, print simulation type
				//root.getChildren().add(conwayLabel);
				//root.getChildren().add(predpreyLabel);
				//root.getChildren().add(fireLabel);
			}
		});
		
		 speedTextField.setOnKeyPressed(event -> {
		      if (event.getCode() == KeyCode.ENTER) {
		    	  	int speed = Integer.parseInt(speedTextField.getText());
		        SECOND_DELAY = speed;
		        System.out.println(speed);
		      }
		    });
		
	}
	
	//	protected void handleKeyInput (KeyCode code) { //handles keyboard/mouse input
	//		if (code == KeyCode.RIGHT) {
	//
	//		}
	//	}
}
