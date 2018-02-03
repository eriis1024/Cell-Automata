/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 * Subclasses of this abstract class will control updating of cells in each instance of Grid with traits/values specific to simulation type (ConwaySim, FireSim, etc.)
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public abstract class Simulation	{
	protected HashMap<String, Color> possStates;
	protected Grid grid;
	protected Neighborhood neighbors;

	/**
	 * @param Grid
	 */
	public Simulation(Grid g)	{
		grid = g;
		neighbors = new Neighborhood();
	}

	/**
	 * Creates copy of Grid, updates each cell according to neighbors and the specific simulation's rules, and replaces current grid with copy when all Cell states have been updated
	 */
	public void update()	{
		Grid updatedGrid = grid.copy();

		for (int i = 0; i < grid.width; i++)	{
			for (int j = 0; j < grid.height; j++; )	{
				updatedGrid.setCell(i, j, ______________);
			}
		}
	}

	/**
	 *
	 */
	private abstract String getNextState(Cell c, Neighborhood n);

	/**
	 * Called by getNextState, helper method to list the number of cells in each state in neighborhood
	 */
	private abstract HashMap<String, Integer> getNeighborStates(Neighborhood n);
}