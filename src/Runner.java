import javafx.application.Application;
import javafx.stage.Stage;


public class Runner extends Application{
	
	@Override
	public void start (Stage primaryStage) throws Exception {
		Viewer Viewer = new Viewer();
		Viewer.paintScene(primaryStage);
	}

	public static void main (String[] args) {
		launch(args);
	}

}
