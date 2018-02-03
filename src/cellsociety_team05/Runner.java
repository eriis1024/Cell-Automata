package cellsociety_team05;

import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application{
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		//runner updates the grid
		
		Viewer scene = new Viewer(); //calls viewer
		scene.paintScene(arg0);
	}
	
	protected void step (double elapsedTime) {
		//animation
	}
	
	public static void main (String[] args) {
		launch(args);
	}
}