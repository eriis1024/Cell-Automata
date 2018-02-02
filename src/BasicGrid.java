import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;

public class BasicGrid implements Grid {
	
	private int myWidth;
	private int myHeight;
	private Color defaultColor;
	private Cell[][] cellArray;
	
	public BasicGrid(int width, int height, ArrayList<Cell> cells, Color defaultCellColor) {
		defaultColor = defaultCellColor;
		myWidth = width;
		myHeight = height;
		cellArray = new Cell[height][width];
		init(cells);
	}
	
	public int getWidth() {
		return myWidth;
	}
	
	public int getHeight() {
		return myHeight;
	}
	
	public Cell get(int x, int y) {
		return cellArray[y][x];
	}
	
	public void set(int x, int y, Color c) {
		get(x,y).setColor(c);
	}
	
	public void insert(Cell c) {
		try {
			cellArray[c.getY()][c.getX()] = c;
		}catch(IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("bad index for grid");
		}
	}
	
	private void init(ArrayList<Cell> cells) {
		for(int i = 0; i<myWidth; i++) {
			for(int j = 0; j<myHeight; j++) insert(new Cell(i, j, defaultColor));
		}
		for (Cell c: cells) insert(c);
	}
	
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
