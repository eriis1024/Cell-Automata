import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestRunner extends Application{
	
	protected Stage myStage;

	public static Simulation myConway;

	public Simulation makeConway(int width, int height, ArrayList<Cell> cells) {

		Grid myGrid = new BasicGrid(width, height, cells, Color.BLACK);

		return new ConwaySimulation(myGrid);
	}

	@Override
	public void start (Stage primaryStage) throws Exception {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		cells.add(new Cell(Color.WHITE, 0, 0));
		cells.add(new Cell(Color.WHITE, 1, 0));
		cells.add(new Cell(Color.WHITE, 2, 0));
		cells.add(new Cell(Color.WHITE, 4, 0));
		cells.add(new Cell(Color.WHITE, 5, 0));
		cells.add(new Cell(Color.WHITE, 30, 0));
		cells.add(new Cell(Color.WHITE, 40, 0));
		cells.add(new Cell(Color.WHITE, 49, 0));
		cells.add(new Cell(Color.WHITE, 35, 0));

		myConway = makeConway(50, 50, cells);

		Viewer Viewer = new Viewer();
		Viewer.paintScene(myStage);
	}

	protected void step (double elapsedTime) {
		//animation
		//calls update() 
	}

	public static void main(String[] args) {
		launch(args);

	}

}
