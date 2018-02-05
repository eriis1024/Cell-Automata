/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 * Subclasses of this abstract class will control updating of cells in each instance of Grid with traits/values specific to simulation type (ConwaySim, FireSim, etc.)
 */

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.paint.Color;

public abstract class Simulation	{
	protected HashMap<String, Color> possStates;
	protected Grid grid;
	protected Neighborhood neighborhood;
	public static final List<String> SUPPORTED_TYPES = Arrays.asList(new String[] {
        "Conway",
        "Segregation",
        "Fire",
        "WaTor"});

	/**
	 * @param
	 */
	public Simulation(Grid g)	{
		grid = g;
	}

	/**
	 * Creates copy of Grid, updates each cell according to neighbors and the specific simulation's rules, and replaces current grid with copy when all Cell states have been updated
	 */
	public void update()	{
		Grid updatedGrid = grid.copy();

		for (int i = 0; i < grid.getWidth(); i++)	{
			for (int j = 0; j < grid.getHeight(); j++)	{
				Color nextState = possStates.get(getNextState(grid.get(i, j), neighborhood));
				updatedGrid.set(i, j, nextState);
			}
		}
	}

	/**
	 * Helper method for getNextState, gets state of a Cell as a String from Cell's color
	 * @param
	 */
	protected String getState(Cell c)	{
		for (Map.Entry state:possStates.entrySet())	{
			if (state.getValue() == c.getColor())	{
				return state.getKey().toString();
			}
		}

		return null;
	}

	/**
	 *
	 */
	protected abstract String getNextState(Cell c, Neighborhood n);

	/**
	 * Called by getNextState, helper method to list the number of cells in each state in neighborhood
	 */
	protected abstract HashMap<String, Integer> getNeighborStates(Cell c, Neighborhood n);

	/**
	 *
	 */
	public HashMap<String, Color> getStates()	{
		return possStates;
	}
}