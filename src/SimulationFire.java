/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public class SimulationFire extends Simulation	{
	public static final Color DEFAULT_COLOR = Color.WHITE;

	public double probCatch;

	public SimulationFire(Grid g, double prob)	{
		super(g);
		possStates = new HashMap<String, Color>()	{{
			put("EMPTY", Color.WHITE);
			put("TREE", Color.GREEN);
			put("BURNING", Color.ORANGE);
		}};
		neighborhood = new NonDiagNeighborhood();
		probCatch = prob;
	}

	/**
	 * gets what state Ceel c should be after update
	 * This is where the conditions of simulation are held
	 * @param
	 * @param
	 */
	@Override
	private String getNextState(Cell c, Neighborhood n)	{
		HashMap<String, Integer> nStates = getNeighborStates(c, n);

		// empty cells do nothing
		if (c.getColor() == possStates.get("TREE"))	{
			if (nStates.get("BURNING") >= 1)	{
				if (Math.random() <= probCatch)	{
					return "BURNING";
				}
			}

			return "TREE";
		}		
		else if (c.getColor() == possStates.get("BURNING"))	{
			return "EMPTY";
		}

		return "EMPTY";	// all cases of changing states handled in if tree, this is fall-through
	}

	/**
	 * Returns number of cells in each state, in Cell c's neighborhood
	 * @param
	 * @param
	 */
	@Override
	private HashMap<String, Integer> getNeighborStates(Cell c, Neighborhood n)	{
		HashMap<String, Integer> nStates = new HashMap<String, Integer>();
		int neighborsBurning = 0;

		for (Cell neighbor:n.getNeighbors(grid, c))	{
			if (neighbor.getColor() == possStates.get("BURNING"))	{
				neighborsBurning++;
			}
		}

		nStates.put("BURNING", neighborsBurning);

		return nStates;
	}
}