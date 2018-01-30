import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;

public class BasicGrid implements Grid {
	
	private int myWidth;
	private int myHeight;
	private Cell[][] cellArray;
	private HashMap<String, Color> states;
	
	public BasicGrid(int width, int height, ArrayList<Cell> cells, HashMap<String, Color> availableStates) {
		myWidth = width;
		myHeight = height;
		cellArray = new Cell[height][width];
		states = availableStates;
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
	
	public void set(int x, int y, String state) {
		get(x,y).setColor(states.get(state));
	}
	
	public void insert(Cell c) {
		if(c.getY() >= myHeight || c.getX() >= myWidth || c.getX() < 0 || c.getY() < 0) System.out.println("should throw an error here");
		cellArray[c.getY()][c.getX()] = c;
	}
	
	private void init(ArrayList<Cell> cells) {
		if(cells.size()>myWidth*myHeight) System.out.println("should throw an error here");
		for (Cell c: cells) insert(c);
	}
}
