/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class SimulationWaTor extends Simulation	{
	public static final Color DEFAULT_COLOR = Color.BLUE;

	public int START_PREY;
	public int START_PRED;
	public int PREY_BREED_AGE;
	public int PRED_BREED_AGE;
	public int PRED_ENERGY;
	public int PRED_REGAIN_ENERGY;

	public SimulationWaTor(int start_prey, int start_pred, int prey_breed_age, int pred_breed_age, int pred_energy, int pred_regain_energy)	{
		super();
		possStates = new HashMap<String, Color>()	{{
			put("FREE", Color.BLUE);
			put("PREY", Color.YELLOW);
			put("PREDATOR", Color.BLACK);
		}};
		neighborhood = new NonDiagNeighborhood();

		START_PREY = start_prey;
		START_PRED = start_pred;
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
		super.update();
		moveCells(updatedGrid, );
	}

	/**
	 * gets what state Ceel c should be after update
	 * This is where the conditions of simulation are held
	 * @param
	 * @param
	 */
	@Override
	private String getNextState(Cell c, Neighborhood n)	{
		HashMap<String, ArrayList<Point2D>> nStates = getNeighborStates(n);
		HashMap<Cell, Point2D> toMove = new HashMap<Cell, Point2D>();

		c.breedAge++;
		if (c.getColor() == possStates.get("PREDATOR"))	{
			c.loseEnergy();
			if (nStates.containsKey("PREY"))	{	// if any prey to eat
				addToMove(c, nStates, "PREY", toMove);
				c.eat(PRED_REGAIN_ENERGY);

				return checkBreed(c);
			}
			else if (nStates.containsKey("FREE"))	{	// if free spots
				addToMove(c, nStates, "FREE", toMove);

				return checkBreed(c);
			}
			else	{	// stay
				return "PREDATOR";
			}
		}
		else if (c.getColor() == possStates.get("PREY"))	{
			if (nStates.containsKey("FREE"))	{	// if free spots
				addToMove(c, nStates, "FREE", toMove);
				c.loseEnergy();
				checkBreed(c);
			}
			else	{	// no spaces to move to, no movement, no reproduction
				return "PREY";
			}
		}
	}

	/**
	 * Returns number of cells in each state, in Cell c's neighborhood
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

	/**
	 *
	 * @param
	 */
	private String checkBreed(Cell c)	{
		if (c.getColor() == possStates.get("PREY"))	{
			if (c.breedAge >= PREY_BREED_AGE)	{	// breed
				c.breedAge = 0;// insert new prey cell
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