/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class SimulationWaTor extends Simulation	{
	public static final Color DEFAULT_COLOR = Color.BLUE;

	private int PREY_BREED_AGE;
	private int PRED_BREED_AGE;
	private int PRED_ENERGY;
	private int PRED_REGAIN_ENERGY;

	public SimulationWaTor(Grid g, int prey_breed_age, int pred_breed_age, int pred_energy, int pred_regain_energy)	{
		super(g);
		possStates = new HashMap<String, Color>()	{{
			put("EMPTY", Color.BLUE);
			put("PREY", Color.YELLOW);
			put("PREDATOR", Color.BLACK);
		}};
		neighborhood = new WrapNeighborhood();

		PREY_BREED_AGE = prey_breed_age;
		PRED_BREED_AGE = pred_breed_age;
		PRED_ENERGY = pred_energy;
		PRED_REGAIN_ENERGY = pred_regain_energy;
	}

	/**
	 *
	 */
	@Override
	public Grid update()	{
		Grid updatedGrid = grid.copy();
		HashMap<Cell, Cell> toMove = new HashMap<Cell, Cell>();

		for (int i = 0; i < grid.getWidth(); i++)	{
			for (int j = 0; j < grid.getHeight(); j++)	{
				if (grid.get(i, j).getColor() == possStates.get("PREDATOR"))	{
					Color nextState = possStates.get(getNextState(grid.get(i, j), neighborhood, updatedGrid, toMove));
					updatedGrid.set(i, j, nextState);
				}
			}
		}

		for (int i = 0; i < grid.getWidth(); i++)	{
			for (int j = 0; j < grid.getHeight(); j++)	{
				if (grid.get(i, j).getColor() == possStates.get("PREY") && ((CellPrey)grid.get(i, j)).getAlive())	{
					Color nextState = possStates.get(getNextState(grid.get(i, j), neighborhood, updatedGrid, toMove));
					updatedGrid.set(i, j, nextState);
				}
			}
		}

		for (int i = 0; i < grid.getWidth(); i++)	{
			for (int j = 0; j < grid.getHeight(); j++)	{
				if (grid.get(i, j).getColor() == possStates.get("EMPTY"))	{
					updatedGrid.set(i, j, possStates.get("EMPTY"));
				}
			}
		}

		// Above code is copy of Simulation superclass update, but passing getNextState updatedGrid also

		moveCells(updatedGrid, toMove);

		grid = updatedGrid;

		return grid;
	}

	/**
	 *
	 */
	protected String getNextState(Cell c, Neighborhood n)	{
		return null;
	}

	/**
	 * gets what state Cell c should be after update
	 * This is where the conditions of simulation are held
	 * @param
	 * @param
	 * @param
	 * @param
	 */
	private String getNextState(Cell c, Neighborhood n, Grid updatedGrid, HashMap<Cell, Cell> toMove)	{
		HashMap<String, ArrayList<Cell>> nStates = getNeighborStateLocs(c, n);

		if (c.getColor() == possStates.get("PREDATOR"))	{
			((CellPredator)c).breedAge++;
			if (nStates.containsKey("PREY"))	{	// if any prey to eat in neighborhood
				addToMove(c, nStates, "PREY", toMove);	// move to prey location (random if multiple)
				((CellPredator)c).eat(PRED_REGAIN_ENERGY);	// regain energy from eating

				if (!checkAlive(c, updatedGrid))	{	// if dead, end
					return "EMPTY";
				}

				return checkBreed(c, updatedGrid);
			}
			else if (nStates.containsKey("EMPTY"))	{	// if no prey, but EMPTY cells
				addToMove(c, nStates, "EMPTY", toMove);	// move predator to EMPTY cell (random if multiple)

				if (!checkAlive(c, updatedGrid))	{	// if dead, end
					return "EMPTY";
				}

				return checkBreed(c, updatedGrid);
			}
			else	{	// if nowhere to move (all neighbors are also predators), stay in place
				if (!checkAlive(c, updatedGrid))	{	// if dead, end
					return "EMPTY";
				}

				return "PREDATOR";
			}
		}
		if (c.getColor() == possStates.get("PREY"))	{
			((CellPrey)c).breedAge++;
			if (nStates.containsKey("EMPTY"))	{	// if no prey, but EMPTY cells
				addToMove(c, nStates, "EMPTY", toMove);	// move predator to EMPTY cell (random if multiple)

				return checkBreed(c, updatedGrid);
			}
			else	{	// if nowhere to move (all neighbors are also predators), stay in place
				return "PREY";
			}
		}
		else	{	// EMPTY cell, assume stay EMPTY and later move method will cover if necessary
			return "EMPTY";
		}
	}

	/**
	 * Returns the locations of cells of each type in cell's neighborhood
	 * @param
	 * @param
	 */
	@Override
	protected HashMap<String, Integer> getNeighborStates(Cell c, Neighborhood n)	{
		return null;
	}

	private HashMap<String, ArrayList<Cell>> getNeighborStateLocs(Cell c, Neighborhood n)	{
		HashMap<String, ArrayList<Cell>> nStates = new HashMap<String, ArrayList<Cell>>();

		for (Cell neighbor:n.getNeighbors(grid, c))	{
			if (neighbor.getColor() == possStates.get("EMPTY"))	{
				if (!nStates.containsKey("EMPTY"))	{
					ArrayList<Cell> indices = new ArrayList<Cell>();
					indices.add(neighbor);
					nStates.put("EMPTY", indices);
				}
				else	{
					nStates.get("EMPTY").add(neighbor);
				}
			}
			else if (neighbor.getColor() == possStates.get("PREY"))	{
				if (!nStates.containsKey("PREY"))	{
					ArrayList<Cell> indices = new ArrayList<Cell>();
					indices.add(neighbor);
					nStates.put("PREY", indices);
				}
				else	{
					nStates.get("PREY").add(neighbor);
				}
			}
			else if (neighbor.getColor() == possStates.get("PREDATOR"))	{
				if (!nStates.containsKey("PREDATOR"))	{
					ArrayList<Cell> indices = new ArrayList<Cell>();
					indices.add(neighbor);
					nStates.put("PREDATOR", indices);
				}
				else	{
					nStates.get("PREDATOR").add(neighbor);
				}
			}
		}

		return nStates;
	}

	/**
	 *
	 * @param
	 * @param
	 * @param
	 * @param
	 */
	private void addToMove(Cell c, HashMap<String, ArrayList<Cell>> nStates, String state, HashMap<Cell, Cell> toMove)	{
		int moveTo = (int) (Math.random() * nStates.get(state).size());
		toMove.put(c, nStates.get(state).get(moveTo));
	}

	/**
	 *
	 * @param
	 * @param
	 */
	private void moveCells(Grid updatedGrid, HashMap<Cell, Cell> toMove)	{
		for (Cell sprite:toMove.keySet())	{
			if (toMove.get(sprite).getColor() == possStates.get("PREY"))	{
				((CellPrey)toMove.get(sprite)).getEaten();
			}
			updatedGrid.remove(toMove.get(sprite));

			if (updatedGrid.get(sprite.getX(), sprite.getY()).getColor() != possStates.get("EMPTY")
				&& updatedGrid.get(sprite.getX(), sprite.getY()).getColor() != possStates.get("PREDATOR"))	{
				updatedGrid.insert(new Cell(possStates.get("EMPTY"), sprite.getX(), sprite.getY()));
			}

			sprite.setX((int)toMove.get(sprite).getX());
			sprite.setY((int)toMove.get(sprite).getY());
			updatedGrid.insert(sprite);
		}
	}

	private boolean checkAlive(Cell c, Grid updatedGrid)	{
		((CellPredator)c).loseEnergy();	// predator loses energy every round, no matter what
		if (((CellPredator)c).energy <= 0)	{
			((CellPredator)c).breedAge = 0;
			updatedGrid.remove(c);

			return false;
		}

		return true;
	}

	/**
	 *
	 * @param
	 */
	private String checkBreed(Cell c, Grid updatedGrid)	{
		if (c.getColor() == possStates.get("PREY"))	{
			if (((CellPrey)c).breedAge >= PREY_BREED_AGE)	{	// breed
				((CellPrey)c).breedAge = 0;

				// insert new prey cell
				updatedGrid.insert(new CellPrey(possStates.get("PREY"), c.getX(), c.getY()));

				return "PREY";
			}
			else	{
				return "EMPTY";
			}
		}
		else if (c.getColor() == possStates.get("PREDATOR")) {
			if (((CellPredator)c).breedAge >= PRED_BREED_AGE)	{	// breed
				((CellPredator)c).breedAge = 0;

				// insert new predator cell
				updatedGrid.insert(new CellPredator(possStates.get("PREDATOR"), c.getX(), c.getY(), PRED_ENERGY));

				return "PREDATOR";	// put NEW predator cell at this index
			}
			else	{
				return "EMPTY";
			}
		}

		return "EMPTY";
	}
}
