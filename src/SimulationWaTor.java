/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class SimulationWaTor extends Simulation	{
	public static final Color DEFAULT_COLOR = Color.BLUE;
	public static final int START_PREY = ;
	public static final int START_PRED = ;
	public static final int PREY_BREED_AGE = ;
	public static final int PRED_BREED_AGE = ;
	public static final int PRED_ENERGY = ;
	public static final int PRED_REGAIN_ENERGY = ;

	public SimulationWaTor()	{
		super();
		possStates = new HashMap<String, Color>()	{{
			put("FREE", Color.BLUE);
			put("PREY", Color.YELLOW);
			put("PREDATOR", Color.BLACK);
		}};
		neighborhood = new NonDiagNeighborhood();
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
			if (nStates.containsKey("PREY"))	{	// if any prey to eat
				addToMove(c, nStates, "PREY", toMove);
				loseEnergy(c);
				eat(c);

				return checkBreed(c);
			}
			else if (nStates.containsKey("FREE"))	{	// if free spots
				addToMove(c, nStates, "FREE", toMove);
				loseEnergy(c);

				return checkBreed(c);
			}
			else	{	// stay
				loseEnergy(c);

				return "PREDATOR";
			}
		}
		else if (c.getColor() == possStates.get("PREY"))	{
			if (nStates.containsKey("FREE"))	{	// if free spots
				addToMove(c, nStates, "FREE", toMove);
				loseEnergy(c);
				checkBreed(c);
			}
			else	{	// no spaces to move to, no movement, no reproduction
				return "PREY";
			}
		}
		else	{
			loseEnergy(c);
			checkBreed(c);
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
	private void loseEnergy(Cell c)	{
		c.energy--;
	}

	/**
	 *
	 * @param
	 */
	private void eat(Cell c)	{
		c.energy += PRED_REGAIN_ENERGY;
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