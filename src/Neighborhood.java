import java.util.ArrayList;

/**
 * @author Jeremy Chen
 *	Interface that defines what a "neighbor" is for a Cell on a Grid
 */
public interface Neighborhood {
	/**
	 * @param g
	 * @param c
	 * @return ArrayList<Cell> of Cells neighboring Cell c on Grid g
	 * 
	 * Will retrieve neighbors (definition varying by child class), of a Cell c on a Grid g
	 */
	public ArrayList<Cell> getNeighbors(Grid g, Cell c);
}
