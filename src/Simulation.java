/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 * Subclasses of this abstract class will control updating of cells in each instance of Grid with traits/values specific to simulation type (ConwaySim, FireSim, etc.)
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public abstract class Simulation	{
	/**
	 * @param Grid
	 */

	HashMap<String, Color> possStates;

	public Simulation(Grid g)	{
	}

	/**
	 * Will have a subclass of Neighborhood (cells that affect selected cell's state) based on simulation
	 */
	public static final Neighborhood neighbor;

	/**
	 * Creates copy of Grid, updates each cell according to neighbors and the specific simulation's rules, and replaces current grid with copy when all Cell states have been updated
	 */
	public void update()	{
	}
}