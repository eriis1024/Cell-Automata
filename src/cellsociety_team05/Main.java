package cellsociety_team05;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start (Stage stage) {
		Viewer scene = new Viewer(); //calls viewer
		scene.paintScene(stage);
	}
	public static void main (String[] args) {
		launch(args);
	}

}
