/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public class SimulationSegregation extends Simulation	{
	public static final Color DEFAULT_COLOR = Color.WHITE;
	public static final double SATISFIED = ______;	// get from XML

	public SimulationSegregation()	{
		super();
		possStates = new HashMap<String, Color>()	{{
			put("empty", Color.WHITE);
			put("x", Color.BLUE);
			put("o", Color.RED);
		}};
		neighborhood = new BasicNeighborhood();
	}

	/**
	 * gets what state Ceel c should be after update
	 * This is where the conditions of simulation are held
	 */
	@Override
	private String getNextState(Cell c, Neighborhood n)	{
		HashMap<String, Integer> nStates = getNeighborStates(n);
		int numNeighbors = nStates.get("x") + nStates.get("o");

		if ((double) nStates.get(getState(c)) / numNeighbors < SATISFIED)	{
			moveDissatisfiedCell();
			return "empty";
		}
	}

	/**
	 * Returns number of cells in each state, in Cell c's neighborhood
	 */
	@Override
	private HashMap<String, Integer> getNeighborStates(Neighborhood n)	{
		HashMap<String, Integer> nStates = new HashMap<String, Integer>();
		int neighborsX = 0;
		int neighborsO = 0;

		for (Cell neighbor:n.getNeighbors(grid, c))	{
			if (neighbor.getColor() == possStates.get("x"))	{
				neighborsX++;
			}
			else if (neighbor.getColor() == possStates.get("o"))	{
				neighborsO++;
			}
		}

		nStates.put("x", neighborsX);
		nStates.put("o", neighborsO);

		return nStates;
	}

	/**
	 * Helper method for getnextState, gets state of a cell as a String
	 */
	private String getState(Cell c)	{
		for (Map.Entry state:possStates.entrySet())	{
			if (state.getValue() == c.getColor())	{
				return state.getKey();
			}
		}
	}

	/**
	 *
	 */
	private ArrayList<Cell> getEmptyCells()	{
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();

		for (int i = 0; i < grid.getWidth(); i++)	{
			for (int j = 0; j < grid.getHeight(); i++)	{
				if (grid.get(i, j).getColor() == possStates.get("empty"))	{
					emptyCells.add(grid.get(i, j));
				}
			}
		}

		return emptyCells;
	}

	/**
	 *
	 */
	private void moveDissatisfiedCell()	{
		ArrayList emptyCells = getEmptyCells();
		int chooseRandCell = Math.random() * emptyCells.size();

		// move cell in UPDATEGRID
	}
}