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
	public static final int PRED_STARVE_AGE = ;

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

		// add to breed timer
		if (c.getColor() == possStates.get("PREDATOR"))	{
			if (nStates.containsKey("PREY"))	{	// if any prey to eat
				// move to prey (random if multiple)
				// lose energy
				// regain emergy
				if ()	{	// breed
					// reset breed timer
					return "PREDATOR";
				}
				else	{
					return "FREE";
				}
			}
			else if (nStates.containsKey("FREE"))	{	// if free spots
				// randomly move to free spot
				// lose one energy
				if ()	{	// breed
					// reset breed timer
					return "PREDATOR";
				}
				else	{
					return "FREE";
				}
			}
			else	{	// stay
				// lose one energy
				return "PREDATOR";
			}
		}
		else if (c.getColor() == possStates.get("PREY"))	{
			if (nStates.containsKey("FREE"))	{	// if free spots
				// randomly move to free spot
				// lose one energy
				if ()	{	// breed
					// reset brred timer
					return "PREY";
				}
				else	{
					return "FREE";
				}
			}
			else	{	// no spaces to move to, no movement, no reproduction
				return "PREY";
			}
		}
		else	{
			// lose one energy
			if ()	{	// breed
				return "PREDATOR";
			}
			else	{
				return "FREE";
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
	 */
	private void moveCell(Cell c, Point2D newLoc)	{

	}
}