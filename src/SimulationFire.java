/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public class SimulationFire extends Simulation	{
	public SimulationFire()	{
		possStates = new HashMap<String, Color>()	{{
			put("empty", Color.WHITE);
			put("tree", Color.GREEN);
			put("burning", Color.ORANGE);
		}};
	}

	/**
	 * gets what state Ceel c should be after update
	 * This is where the conditions of simulation are held
	 */
	@Override
	private String getNextState(Cell c, Neighborhood n)	{
		HashMap<String, Integer> nStates = getNeighborStates(n);

		// empty cells do nothing
		if (c.getFill() == possStates.get("tree"))	{
			double probCatch = 0.15;

			if (nStates.get("burning") >= 1)	{
				if (Math.random() <= probCatch)	{
					return "burning";
				}
			}

			return "tree";
		}		
		else if (c.getFill() == possStates.get("burning"))	{
			return "empty";
		}
	}

	/**
	 * Returns number of cells in each state, in Cell c's neighborhood
	 */
	@Override
	private HashMap<String, Integer> getNeighborStates(Neighborhood n)	{
		HashMap<String, Integer> nStates = new HashMap<String, Integer>();
		int neighborsEmpty = 0;
		int neighborsTree = 0;
		int neighborsBurning = 0;

		for (Cell neighbor:n)	{
			if (neighbor.getFill() == possStates.get("empty"))	{
				neighborsEmpty++;
			}
			else if (neighbor.getFill() == passStates.get("tree"))	{
				neighborsTree++;
			}
			else if (neighbor.getFill() == passStates.get("burning"))	{
				neighborsBurning++;
			}
		}

		nStates.put("empty", neighborsEmpty);
		nStates.put("tree", neighborsTree);		
		nStates.put("burning", neighborsBurning);

		return nStates;
	}
}