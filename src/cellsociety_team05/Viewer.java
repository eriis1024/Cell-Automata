package cellsociety_team05;

import javafx.scene.Scene;
import javafx.stage.Stage;



//public abstract Viewer - Paints a Grid onto a Scene
//public Viewer(Grid g, Scene s)


public class Viewer extends UserInterface {
	//create scene
	//calls grid to populate scene
	
	public void paintScene(Stage stage) {
		setupScene();
		myScene = new Scene(root, SIZE_X, SIZE_Y, BACKGROUND_COLOR);	
		myScene.getStylesheets().add("CellSociety.css");
		
		stage.setScene(myScene);
		stage.show();
	}

}
