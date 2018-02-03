/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public class SimulationFire extends Simulation	{
	public static final Color DEFAULT_COLOR = Color.WHITE;

	public SimulationFire()	{
		super();
		possStates = new HashMap<String, Color>()	{{
			put("empty", Color.WHITE);
			put("tree", Color.GREEN);
			put("burning", Color.ORANGE);
		}};
		neighborhood = new NonDiagNeighborhood();
	}

	/**
	 * gets what state Ceel c should be after update
	 * This is where the conditions of simulation are held
	 */
	@Override
	private String getNextState(Cell c, Neighborhood n)	{
		HashMap<String, Integer> nStates = getNeighborStates(n);

		// empty cells do nothing
		if (c.getColor() == possStates.get("tree"))	{
			double probCatch = 0.15;

			if (nStates.get("burning") >= 1)	{
				if (Math.random() <= probCatch)	{
					return "burning";
				}
			}

			return "tree";
		}		
		else if (c.getColor() == possStates.get("burning"))	{
			return "empty";
		}
	}

	/**
	 * Returns number of cells in each state, in Cell c's neighborhood
	 */
	@Override
	private HashMap<String, Integer> getNeighborStates(Neighborhood n)	{
		HashMap<String, Integer> nStates = new HashMap<String, Integer>();
		int neighborsBurning = 0;

		for (Cell neighbor:n.getNeighbors(grid, c))	{
			if (neighbor.getColor() == possStates.get("burning"))	{
				neighborsBurning++;
			}
		}

		nStates.put("burning", neighborsBurning);

		return nStates;
	}
}