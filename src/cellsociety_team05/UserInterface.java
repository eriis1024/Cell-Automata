package cellsociety_team05;
//This class deals with the user interface components of the project from mouse/keyboard inputs. 
//Anything the user interacts with directly is handled here.
//public class userInterface{} - This class deals with the user interface components of the project from mouse/keyboard inputs
//handles exceptions
//handles toolbar, file selection
//handles mouse input
//handles keyboard input

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class UserInterface {
	protected StackPane root = new StackPane();

	public void setupScene() {
		Button startButton = setButton(-70, 210, 100, 50, "Start");
		//startButton.setStyle("-fx-background-color: #00ff00; -fx-font: 20 Arial; -fx-text-fill: #ffffff;");
		root.getChildren().add(startButton);

		Button stopButton = setButton(-180, 210, 100, 50, "Stop");
		//stopButton.setStyle("-fx-background-color: #ff0000; -fx-font: 20 Arial; -fx-text-fill: #ffffff;");
		root.getChildren().add(stopButton);

		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//startButton.setStyle("-fx-background-color: #98fb98; -fx-font: 20 Arial; -fx-text-fill: #ffffff;");
				//stopButton.setStyle("-fx-background-color: #ff0000; -fx-font: 20 Arial; -fx-text-fill: #ffffff;");
				//animation.start;
			}
		});

		stopButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//stopButton.setStyle("-fx-background-color: #ffa07a; -fx-font: 20 Arial; -fx-text-fill: #ffffff;");
				//startButton.setStyle("-fx-background-color: #00ff00; -fx-font: 20 Arial; -fx-text-fill: #ffffff;");
				//animation.start;
			}
		});
		
		Label title = setLabel(-90, -215, "CELL SOCIETY - Team 5" );
		title.setStyle("-fx-font: 20 Arial");
		root.getChildren().add(title);

		Button stepButton = setButton(40, 210, 100, 50, "Step");
		//stepButton.setStyle("-fx-background-color: #0000ff; -fx-font: 20 Arial; -fx-text-fill: #ffffff;");
		root.getChildren().add(stepButton);

		String[] simTypes = {"Conway", "Predator-Prey", "Fire"};
		ChoiceBox<String> selectSim = setMenu(170, -210, "Select Simulation", simTypes);
		root.getChildren().add(selectSim);

		TextField speedTextField = setTextField(170, 220, 125, 30, "Speed: ");
		root.getChildren().add(speedTextField);
	}

	//-fx-border-width, -fx-border-color, -fx-background-color, -fx-font-size, -fx-text-fill
	private Button setButton(int x, int y, int size_x, int size_y, String text) {
		Button button = new Button();
		button.setText(text);
		button.setTranslateX(x);
		button.setTranslateY(y);
		button.setMaxSize(size_x, size_y);
		return button;
	}

	private ChoiceBox<String> setMenu(int x, int y, String label, String[] items) {
		ChoiceBox<String> menu = new ChoiceBox<String>();
		menu.setItems(FXCollections.observableArrayList(items));
		menu.setTranslateX(x);
		menu.setTranslateY(y);
		Label lbl = setLabel(x-5, y-25, label); //make applicable to all cases (size)
		root.getChildren().add(lbl);
		return menu;
	}

	private TextField setTextField(int x, int y, int size_x, int size_y, String label) {
		TextField textField = new TextField();
		textField.setTranslateX(x);
		textField.setTranslateY(y);
		textField.setMaxSize(size_x, size_y);
		Label lbl = setLabel(x-(size_x/3) + 2, y-size_y, label);
		root.getChildren().add(lbl);
		return textField;
	}

	private Label setLabel(int x, int y, String text) {
		Label lbl = new Label();
		lbl.setText(text);
		lbl.setTranslateX(x);
		lbl.setTranslateY(y);
		return lbl;
	}

	//	protected void handleKeyInput (KeyCode code) { //handles keyboard/mouse input
	//		if (code == KeyCode.RIGHT) {
	//
	//		}
	//	}
}
