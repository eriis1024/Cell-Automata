/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */
import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Cell extends Polygon	{
	public Cell(Color color)	{
	}

	public void makeCell(Color color)	{
		this.setFill(color);
	}
}