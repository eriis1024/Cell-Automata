/**
 * @author Maya Messinger (mm479)
 * Started 3 Feb 18
 */
import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class CellPrey extends Cell	{
	public int breedAge;
	private boolean alive;

	/**
	 *
	 */
	public CellPrey(Color c, int x, int y)	{
		super(c, x, y);
		breedAge = 0;
		alive = true;
	}

	public void getEaten()	{
		alive = false;
	}

	public boolean getAlive()	{
		return alive;
	}
}