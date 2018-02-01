package cellsociety_team05;


import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

//public abstract Viewer - Paints a Grid onto a Scene
//public Viewer(Grid g, Scene s)


public class Viewer extends UserInterface {
	//create scene
	//calls grid to populate scene
	protected final Paint BACKGROUND_COLOR = Color.WHITE;
	protected Scene myScene;
	protected final int SIZE = 500;
	
	public void paintScene(Stage stage) {
		setupScene();
		myScene = new Scene(root, SIZE, SIZE, BACKGROUND_COLOR);	
		stage.setScene(myScene);
		stage.show();
	}
}
