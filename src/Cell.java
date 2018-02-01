/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */
import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;

public class Cell extends Shape	{
	public Cell(Color color)	{
		makeCell(color);
	}

	public void makeCell(Color color)	{
		Rectangle cell = new Rectangle();
		cell.setWidth(50);
		cell.setHeight(50);
		cell.fill(color);
	}
}