/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class SimulationWaTor extends Simulation	{
	public static final Color DEFAULT_COLOR = Color.BLUE;

	public int PREY_BREED_AGE;
	public int PRED_BREED_AGE;
	public int PRED_ENERGY;
	public int PRED_REGAIN_ENERGY;

	public SimulationWaTor(Grid g, int prey_breed_age, int pred_breed_age, int pred_energy, int pred_regain_energy)	{
		super(g);
		possStates = new HashMap<String, Color>()	{{
			put("FREE", Color.BLUE);
			put("PREY", Color.YELLOW);
			put("PREDATOR", Color.BLACK);
		}};
		neighborhood = new NonDiagNeighborhood();

		PREY_BREED_AGE = prey_breed_age;
		PRED_BREED_AGE = pred_breed_age;
		PRED_ENERGY = pred_energy;
		PRED_REGAIN_ENERGY = pred_regain_energy;
	}

	/**
	 *
	 */
	@Override
	public void update()	{
		Grid updatedGrid = grid.copy();

		for (int i = 0; i < grid.getWidth(); i++)	{
			for (int j = 0; j < grid.getHeight(); j++; )	{
				Color nextState = possStates.get(getNextState(grid.get(i, j), neighborhood, updatedGrid));
				updatedGrid.set(i, j, nextState);
			}
		}

		// Above code is copy of Simulation superclass update, but passing getNextState updatedGrid also

		moveCells(updatedGrid, );
	}

	/**
	 * gets what state Ceel c should be after update
	 * This is where the conditions of simulation are held
	 * @param
	 * @param
	 */
	@Override
	private String getNextState(Cell c, Neighborhood n, Grid updatedGrid)	{
		HashMap<String, ArrayList<Point2D>> nStates = getNeighborStates(n);
		HashMap<Cell, Point2D> toMove = new HashMap<Cell, Point2D>();

		c.breedAge++;
		if (c.getColor() == possStates.get("PREDATOR"))	{
			if (nStates.containsKey("PREY"))	{	// if any prey to eat in neighborhood
				addToMove(c, nStates, "PREY", toMove);	// move to prey location (random if multiple)
				c.eat(PRED_REGAIN_ENERGY);	// regain energy from eating

				c.loseEnergy();	// predator loses energy every round, no matter what
				checkAlive(c);

				return checkBreed(c, updatedGrid);
			}
			else if (nStates.containsKey("FREE"))	{	// if no prey, but free cells
				addToMove(c, nStates, "FREE", toMove);	// move predator to free cell (random if multiple)

				c.loseEnergy();	// predator loses energy every round, no matter what
				checkAlive(c);

				return checkBreed(c, updatedGrid);
			}
			else	{	// if nowhere to move (all neighbors are also predators), stay in place
				c.loseEnergy();	// predator loses energy every round, no matter what
				checkAlive(c);

				return "PREDATOR";
			}
		}
		else if (c.getColor() == possStates.get("PREY"))	{
			if (nStates.containsKey("FREE"))	{	// if free spots
				addToMove(c, nStates, "FREE", toMove);
				checkBreed(c, updatedGrid);
			}
			else	{	// no spaces to move to, no movement, no reproduction
				return "PREY";
			}
		}
	}

	/**
	 * Returns the locations of cells of each type in cell's neighborhood
	 * @param
	 * @param
	 */
	@Override
	private NeighborInfo getNeighborStates(Cell c, Neighborhood n)	{
		HashMap<String, ArrayList<Point2D>> nStates = new HashMap<String, ArrayList<Point2D>>();

		for (Cell neighbor:n.getNeighbors(grid, c))	{
			if (neighbor.getColor() == possStates.get("FREE"))	{
				if (!nStates.containsKey("FREE"))	{
					ArrayList<Point2D> indices = new ArrayList<Point2D>();
					indices.add(new Point2D(neighbor.getX(), neighbor.getY()));
					nStates.put("FREE", indices);
				}
				else	{
					nStates.get("FREE").add(new Point2D(neighbor.getX(), neighbor.getY()));
				}
			}
			else if (neighbor.getColor() == possStates.get("PREY"))	{
				if (!nStates.containsKey("PREY"))	{
					ArrayList<Point2D> indices = new ArrayList<Point2D>();
					indices.add(new Point2D(neighbor.getX(), neighbor.getY()));
					nStates.put("PREY", indices);
				}
				else	{
					nStates.get("PREY").add(new Point2D(neighbor.getX(), neighbor.getY()));
				}
			}
			else if (neighbor.getColor() == possStates.get("PREDATOR"))	{
				if (!nStates.containsKey("PREDATOR"))	{
					ArrayList<Point2D> indices = new ArrayList<Point2D>();
					indices.add(new Point2D(neighbor.getX(), neighbor.getY()));
					nStates.put("PREDATOR", indices);
				}
				else	{
					nStates.get("PREDATOR").add(new Point2D(neighbor.getX(), neighbor.getY()));
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
	public void addToMove(Cell c, HashMap<String, ArrayList<Point2D>> nStates, String state, HashMap<Cell, Point2D> toMove)	{
		int moveTo = (int) Math.random() * nStates.get(state).length();
		toMove.put(c, mStates.get(state).get(moveTo));
	}

	/**
	 *
	 * @param
	 * @param
	 */
	private void moveCells(Grid updatedGrid, HashMap<Cell, Point2D> toMove)	{
		for (Cell sprite:toMove.keySet())	{
			sprite.setX((int)toMove.get(sprite).getX());
			sprite.setY((int)toMove.get(sprite).getY());
			updatedGrid.insert(sprite);
		}
	}

	private void checkAlive(Cell c)	{
		if (c.energy == 0)	{

		}
	}

	/**
	 *
	 * @param
	 */
	private String checkBreed(Cell c, Grid updatedGrid)	{
		if (c.getColor() == possStates.get("PREY"))	{
			if (c.breedAge >= PREY_BREED_AGE)	{	// breed
				c.breedAge = 0;	// insert new prey cell

				updatedGrid.insert();

				return "PREY";
			}
			else	{
				return "FREE";
			}
		}
		else if (c.getColor() == possStates.get("PREDATOR")) {
			if (c.breedAge >= PRED_BREED_AGE)	{	// breed
				c.breedAge = 0;

				// insert new predator cell

				return "PREDATOR";	// put NEW predator cell at this index
			}
			else	{
				return "FREE";
			}
		}
}