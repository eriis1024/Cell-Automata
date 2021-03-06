/**
 * @author Maya Messinger (mm479)
 * Started 4 Feb 18
 */
import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class CellPredator extends Cell	{
	public int breedAge;
	public int energy;

	/**
	 *
	 * @param
	 * @param
	 * @param
	 */
	public CellPredator(Color c, int x, int y, int energyStart)	{
		super(c, x, y);
		breedAge = 0;
		energy = energyStart;
	}

	public void loseEnergy()	{
		energy--;
	}

	public void eat(int energyRegained)	{
		energy += energyRegained;
	}
}