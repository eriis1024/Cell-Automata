package cellsociety_team05;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class XMLHelper {
	protected static final Grid fooGrid = new BasicGrid(1,1,new ArrayList<Cell>(), Color.BLACK);
	protected static final String WIDTH = "width";
	protected static final String HEIGHT = "height";
	protected static final String X = "x";
	protected static final String Y = "y";
	protected static final String STATE = "state";
	
	public ArrayList<Cell> getCells(NodeList cellNodes) {
		Element cellNode = (Element)(cellNodes.item(0));
		ArrayList<Cell> cells = new ArrayList<Cell>();
		HashMap<String, Color> states = getStates();
		
		for (Node child = cellNode.getFirstChild(); child != null; child = child.getNextSibling()){ 
			if(child.getNodeName().equals("cell")) {
				Element cellElement = (Element)child;
				int x = Integer.parseInt(cellElement.getAttribute(X));
				int y = Integer.parseInt(cellElement.getAttribute(Y));
				Color cellColor = states.get(cellElement.getAttribute(STATE));
				Cell c = new Cell(cellColor, x, y);
				cells.add(c);
			}
		}
		return cells;
	}

	protected int[] getDimensions(NodeList dims) {
		Element dimensions = (Element)(dims.item(0));
		int width = Integer.parseInt(dimensions.getAttribute(WIDTH));
		int height = Integer.parseInt(dimensions.getAttribute(HEIGHT));
		return new int[] {width, height};
	}
	
	public abstract HashMap<String, Color> getStates();
	public abstract Grid getGrid(NodeList dims, ArrayList<Cell> cells);
	public abstract Simulation initSimulation(NodeList params, Grid g);
	
	// Get value of Element's attribute
    protected String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }
}