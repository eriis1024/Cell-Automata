/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.paint.Color;

public class SimulationSegregation extends Simulation	{
	public static final Color DEFAULT_COLOR = Color.WHITE;

	public double SATISFIED;	// get from XML

	public SimulationSegregation(Grid g, double satisfied)	{
		super(g);
		possStates = new HashMap<String, Color>()	{{
			put("EMPTY", Color.WHITE);
			put("X", Color.BLUE);
			put("O", Color.RED);
		}};
		neighborhood = new BasicNeighborhood();

		SATISFIED = satisfied;
	}

	/**
	 *
	 */
	@Override
	public Grid update()	{		
		Grid updatedGrid = grid.copy();
		ArrayList<Cell> cellstoMove = new ArrayList<Cell>();

		for (int i = 0; i < grid.getWidth(); i++)	{
			for (int j = 0; j < grid.getHeight(); j++)	{
				Color nextState = possStates.get(getNextState(grid.get(i, j), neighborhood, cellstoMove));
				updatedGrid.set(i, j, nextState);
			}
		}

		moveCells(updatedGrid, cellstoMove);

		grid = updatedGrid;

		return grid;
	}

	protected String getNextState(Cell c, Neighborhood n)	{
		return null;
	}

	/**
	 * gets what state Ceel c should be after update
	 * This is where the conditions of simulation are held
	 * @param
	 * @param
	 * @param
	 */
	protected String getNextState(Cell c, Neighborhood n, ArrayList<Cell> cellstoMove)	{
		HashMap<String, Integer> nStates = getNeighborStates(c, n);
		int numNeighbors = nStates.get("X") + nStates.get("O");

		if (c.getColor() == possStates.get("X") || c.getColor() == possStates.get("X"))	{
			if ((double) nStates.get(getState(c)) / numNeighbors < SATISFIED)	{
				cellstoMove.add(c);
				return "EMPTY";
			}
		}
		
		return getState(c);	// cell does not move or change state
	}


	/**
	 * Returns number of cells in each state, in Cell c's neighborhood
	 * @param
	 * @param
	 */
	@Override
	protected HashMap<String, Integer> getNeighborStates(Cell c, Neighborhood n)	{
		HashMap<String, Integer> nStates = new HashMap<String, Integer>();
		int neighborsX = 0;
		int neighborsO = 0;

		for (Cell neighbor:n.getNeighbors(grid, c))	{
			if (neighbor.getColor() == possStates.get("X"))	{
				neighborsX++;
			}
			else if (neighbor.getColor() == possStates.get("O"))	{
				neighborsO++;
			}
		}

		nStates.put("X", neighborsX);
		nStates.put("O", neighborsO);

		return nStates;
	}

	/**
	 * Gets Cells in grid that are *currently* empty
	 */
	private ArrayList<Cell> getEmptyCells()	{
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();

		for (int i = 0; i < grid.getWidth(); i++)	{
			for (int j = 0; j < grid.getHeight(); j++)	{
				if (grid.get(i, j).getColor() == possStates.get("EMPTY"))	{
					emptyCells.add(grid.get(i, j));
				}
			}
		}

		return emptyCells;
	}

	/**
	 * Move dissatisfied Cells to random empty spot on next, updated board
	 * @param
	 */
	private void moveCells(Grid updatedGrid, ArrayList<Cell> toMove)	{
		ArrayList emptyCells = getEmptyCells();

		// move cell in UPDATEGRID
		for (Cell moveMe:toMove)	{
			Cell randomEmpty = getEmptyCells().get((int)Math.random() * emptyCells.size());	// find a place to move unsatisfied cell
			randomEmpty.colorCell(moveMe.getColor());	// "put" cell in empty spot
			moveMe.colorCell(possStates.get("EMPTY"));	// make spot cell left empty

			updatedGrid.insert(randomEmpty);
			updatedGrid.insert(moveMe);

			emptyCells.remove(randomEmpty);	// remove now taken spot from list of empty spots
		}

	}
}