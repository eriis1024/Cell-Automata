import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * @author Jeremy Chen (jc587)
 * 
 * This class implements the Grid interface. It represents a square grid of Cells with a width and a height
 */
public class BasicGrid implements Grid {
	
	private int myWidth;
	private int myHeight;
	private Color defaultColor;
	private Cell[][] cellArray;
	
	
	/**
	 * @param width
	 * @param height
	 * @param cells
	 * @param defaultCellColor
	 * 
	 * Takes in width, height, and an ArrayList of cells to be initialized, will call init() to place cells in grid
	 */
	public BasicGrid(int width, int height, ArrayList<Cell> cells, Color defaultCellColor) {
		defaultColor = defaultCellColor;
		myWidth = width;
		myHeight = height;
		cellArray = new Cell[height][width];
		init(cells);
	}
	
	/* (non-Javadoc)
	 * @see Grid#getWidth()
	 */
	public int getWidth() {
		return myWidth;
	}
	
	/* (non-Javadoc)
	 * @see Grid#getHeight()
	 */
	public int getHeight() {
		return myHeight;
	}
	
	/* (non-Javadoc)
	 * @see Grid#get(int, int)
	 */
	public Cell get(int x, int y) {
		return cellArray[y][x];
	}
	
	/* (non-Javadoc)
	 * @see Grid#set(int, int, javafx.scene.paint.Color)
	 */
	public void set(int x, int y, Color c) {
		get(x,y).setFill(c);
	}
	
	/* (non-Javadoc)
	 * @see Grid#insert(Cell)
	 */
	public void insert(Cell c) {
		try {
			cellArray[c.getY()][c.getX()] = c;
			get(c.getX(), c.getY()).setFill(c.getColor());
		}catch(IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("bad index for grid");
		}
	}

	/* (non-Javadoc)
	 *					JEREMPY LOOK AT THIS
	 *
	 *
	 *						
	 */
	public void remove(Cell c)	{
		cellArray[c.getY()][c.getX()] = new Cell(c.DEFAULT_COLOR, c.getX(), c.getY());
		c = null;
	}
	
	/**
	 * @param cells
	 * 
	 * Will fill Grid with cells given
	 */
	private void init(ArrayList<Cell> cells) {
		for(int i = 0; i<myWidth; i++) {
			for(int j = 0; j<myHeight; j++) insert(new Cell(defaultColor, i, j));
		}
		for (Cell c: cells) insert(c);
	}
	
	/* (non-Javadoc)
	 * @see Grid#copy()
	 */
	public Grid copy() { //deep copy
		ArrayList<Cell> clonedCells = new ArrayList<Cell>();
		for(int i=0; i<getWidth();i++) {
			for(int j = 0; j<getHeight();j++) {
				clonedCells.add(get(i,j).copy());
			}
		}
		return new BasicGrid(getWidth(), getHeight(), clonedCells, defaultColor);
	}
}
