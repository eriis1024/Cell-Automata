/**
 * @author Maya Messinger (mm479)
 * Started 1 Feb 18
 */
import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class CellRectangle extends Cell	{
	private double dimension = 50.0;

	public CellRectangle(Color color)	{
		super(color);
		this.makeCell(color);
	}

	/**
	 * Draws a rectangular cell, 50px x 50px
	 */
	@Override
	public void makeCell(Color color)	{
		super.makeCell(color);
		this.getPoints().addAll(new Double[]{
			0.0, 0.0,
			dimension, 0.0,
			dimension, 0 - dimension,
			0.0, 0 - dimension
		});
	}
}