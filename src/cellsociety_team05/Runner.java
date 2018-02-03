package cellsociety_team05;

import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application{
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		Viewer scene = new Viewer(); //calls viewer
		scene.paintScene(arg0);
	}
	
	protected void step (double elapsedTime) {
		//animation
		//calls update() 
	}
	
	public static void main (String[] args) {
		launch(args);
	}
}