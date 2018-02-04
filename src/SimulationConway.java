/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public class SimulationConway extends Simulation	{
	public static final Color DEFAULT_COLOR = Color.BLACK;

	public SimulationConway()	{
		super();
		possStates = new HashMap<String, Color>()	{{
			put("ALIVE", Color.WHITE);
			put("DEAD", Color.BLACK);
		}};
		neighborhood = new BasicNeighborhood();
	}

	/**
	 * gets what state Ceel c should be after update
	 * This is where the conditions of simulation are held
	 */
	@Override
	private String getNextState(Cell c, Neighborhood n)	{
		HashMap<String, Integer> nStates = getNeighborStates(g, c, n);

		if (c.getColor() == possStates.get("ALIVE"))	{
			if (nStates.get("ALIVE") < 2 || nStates.get("ALIVE") > 3)	{
				return "DEAD";
			}
			else if (nStates.get("ALIVE") == 2 | nStates.get"ALIVE") == 3)	{
				return "ALIVE";
			}
		}
		else if (c.getColor() == possStates.get("DEAD"))	{
			if (nStates.get("ALIVE") == 3)	{
				return "ALIVE";
			}
			else	{
				return "DEAD";
			}
		}

		return "DEAD";	// should never get to this line, all cases handled in if tree
	}

	/**
	 * Returns number of cells in each state, in Cell c's neighborhood
	 */
	@Override
	private HashMap<String, Integer> getNeighborStates(Cell c, Neighborhood n)	{
		HashMap<String, Integer> nStates = new HashMap<String, Integer>();
		int neighborsAlive = 0;
		int neighborsDead = 0;

		for (Cell neighbor:n.getNeighbors(grid, c))	{
			if (neighbor.getColor() == possStates.get("ALIVE"))	{
				neighborsAlive++;
			}
			else if (neighbor.getColor() == possStates.get("DEAD"))	{
				neighborsDead++;
			}
		}

		nStates.put("ALIVE", neighborsAlive);
		nStates.put("DEAD", neighborsDead);

		return nStates;
	}
}