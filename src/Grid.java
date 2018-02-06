import javafx.scene.paint.Color;

/**
 * @author Jeremy Chen (jc587)
 *
 *	Interface class representing a Grid of Cells
 */
public interface Grid{
	
	/**
	 * @return integer width of the Grid (in cells)
	 */
	public int getWidth();
	
	/**
	 * @return integer height of the Grid (in cells)
	 */
	public int getHeight();
	
	/**
	 * @param x
	 * @param y
	 * @return a Cell at x index, x and y index y
	 */
	public Cell get(int x, int y);
	
	/**
	 * @param x
	 * @param y
	 * @param c
	 * 
	 * Will set a cell at x,y to the given color
	 */
	public void set(int x, int y, Color c);
	
	/**
	 * @param c
	 * Will insert Cell c to the x & y coordinates in the cell
	 */
	public void insert(Cell c);
	
	/**
	 * @return a deep copy of Grid
	 *  This class creates and returns a deep copy of Grid, relies on deep copy of cell
	 */
	public Grid copy();
}
