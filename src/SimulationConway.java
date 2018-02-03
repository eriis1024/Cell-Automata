/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public class SimulationConway extends Simulation	{
	public SimulationConway()	{
		possStates = new HashMap<String, Color>()	{{
			put("alive", Color.WHITE);
			put("dead", Color.BLACK);
		}};
	}

	/**
	 * gets what state Ceel c should be after update
	 * This is where the conditions of simulation are held
	 */
	@Override
	private String getNextState(Grid g, Cell c, Neighborhood n)	{
		HashMap<String, Integer> nStates = getNeighborStates(g, c, n);

		if (c.getFill() == possStates.get("alive"))	{
			if (nStates.get("alive") < 2 || nStates.get("alive") > 3)	{
				return "dead";
			}
			else if (nStates.get("alive") == 2 | nStates.get"alive") == 3)	{
				return "alive";
			}
		}
		else if (c.getFill() == possStates.get("dead"))	{
			if (nStates.get("alive") == 3)	{
				return "alive";
			}
		}
	}

	/**
	 * Returns number of cells in each state, in Cell c's neighborhood
	 */
	@Override
	private HashMap<String, Integer> getNeighborStates(Neighborhood n)	{
		HashMap<String, Integer> nStates = new HashMap<String, Integer>();
		int neighborsAlive = 0;
		int neighborsDead = 0;

		for (Cell neighbor:n)	{
			if (neighbor.getFill() == possStates.get("alive"))	{
				neighborsAlive++;
			}
			else if (neighbor.getFill() == passStates.get("dead"))	{
				neighborsDead++;
			}
		}

		nStates.put("alive", neighborsAlive);
		nStates.put("dead", neighborsDead);

		return nStates;
	}
}