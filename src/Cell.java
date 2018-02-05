/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Cell extends Polygon	{
	protected String state;
	protected int xInd;
	protected int yInd;

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
	public int getX()	{
		return xInd;
	}

	/**
	 *
	 */
	public int getY()	{
		return yInd;
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
	public Cell copy()	{
		return new Cell((Color)getFill(), xInd, yInd);
	}
}
