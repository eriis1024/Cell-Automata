import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.paint.Color;

/**
 * @author Jeremy
 *	This Grid class is a wrapper for a 2D array of Cells. It contains utility methods for manipulating these cells.
 */
public interface Grid{
	
	/**
	 * @return an integer representing the width of the grid (number of cells, not pixels)
	 */
	public int getWidth();
	
	/**
	 * @return an integer representing the height of the grid (number of cells, not pixels)
	 */
	public int getHeight();
	
	/**
	 * @param x
	 * @param y
	 * @return an instance of Cell at index [y][x]
	 * 	
	 * 	This method is a getter that will get the Cell at (x,y)
	 */
	public Cell get(int x, int y);
	
	/**
	 * @param x
	 * @param y
	 * @param c
	 * 
	 *  This method is a setter that allows for Cell at (x,y) to be set to Color c
	 */
	public void set(int x, int y, Color c);
	
	/**
	 * @param c
	 * 
	 * This method will insert a Cell c using the Cell's x & y fields
	 */
	public void insert(Cell c);
	
	/**
	 * @return a deep copy of this Grid
	 * 
	 */
	public Grid copy();
}
