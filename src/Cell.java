/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */
import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Cell extends Polygon	{
	private String state;
	private int xInd;
	private int yInd;

	/**
	 *
	 */
	public Cell(Color color, int x, int y)	{
		colorCell(color);
		xInd = x;
		yInd = y;
	}

	/**
	 *
	 */
	public void colorCell(Color color)	{
		this.setFill(color);
	}

	/**
	 *
	 */
	public Color getColor()	{
		return (Color)(this.getFill());
	}

	/**
	 *
	 */
	public void setX(int newX)	{
		xInd = newX;
	}

	/**
	 *
	 */
	public void setY(int newY)	{
		yInd = newY;
	}

	/**
	 *
	 */
	@Override
	public boolean equals(Object other)	{
		return other instanceof Cell && this.getFill() == ((Cell)other).getFill();
	}

	/**
	 *
	 */
	public Cell copy()	{
		return new Cell((Color)getFill(), xInd, yInd);
	}
}