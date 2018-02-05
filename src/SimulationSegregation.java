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

	ArrayList<Cell> cellstoMove;

	public SimulationSegregation(Grid g, int satisfied)	{
		super();
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
	public void update()	{
		cellstoMove = new ArrayList<Cell>();
		super.update();
		moveCells(updatedGrid, cellstoMove);
	}

	/**
	 * gets what state Ceel c should be after update
	 * This is where the conditions of simulation are held
	 * @param
	 * @param
	 */
	@Override
	private String getNextState(Cell c, Neighborhood n)	{
		HashMap<String, Integer> nStates = getNeighborStates(n);
		int numNeighbors = nStates.get("X") + nStates.get("O");

		if ((double) nStates.get(getState(c)) / numNeighbors < SATISFIED)	{
			cellstoMove.add(c);
			return "EMPTY";
		}
		else	{
			return getState(c);	// cell does not move or change state
		}
	}

	/**
	 * Returns number of cells in each state, in Cell c's neighborhood
	 * @param
	 * @param
	 */
	@Override
	private HashMap<String, Integer> getNeighborStates(Cell c, Neighborhood n)	{
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
	 * Helper method for getnextState, gets state of a Cell as a String from Cell's color
	 * @param
	 */
	private String getState(Cell c)	{
		for (Map.Entry state:possStates.entrySet())	{
			if (state.getValue() == c.getColor())	{
				return state.getKey();
			}
		}
	}

	/**
	 * Gets Cells in grid that are *currently* empty
	 */
	private ArrayList<Cell> getEmptyCells()	{
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();

		for (int i = 0; i < grid.getWidth(); i++)	{
			for (int j = 0; j < grid.getHeight(); i++)	{
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
	private void moveCells(ArrayList<Cell> toMove)	{
		ArrayList emptyCells = getEmptyCells();

		// move cell in UPDATEGRID
		for (Cell moveMe:toMove)	{
			Cell randomEmpty = getEmptyCells.get(Math.random() * emptyCells.size());	// find a place to move unsatisfied cell
			randomEmpty.colorCell(moveMe.getColor());	// "put" cell in empty spot
			moveMe.colorCell(possStates.get("EMPTY"));	// make spot cell left empty
			emptyCells.remove(randomEmpty);	// remove now taken spot from list of empty spots
		}

	}
}
