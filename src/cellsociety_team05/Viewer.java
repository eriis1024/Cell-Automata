package cellsociety_team05;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Viewer extends UserInterface {

	public void paintScene(Stage stage) {
		setupScene();
		myScene = new Scene(root, SIZE_X, SIZE_Y, BACKGROUND_COLOR);	
		myScene.getStylesheets().add("CellSociety.css");
		
		//gets initial grid from parseXML
		//scale grid to fit predetermined size (400 by 400)
		
		Rectangle gridsize = new Rectangle(400, 400, Color.GRAY);
		gridsize.setX(50);
		gridsize.setY(50);
		root.getChildren().add(gridsize);
		
		stage.setScene(myScene);
		stage.show();
	}	
}
