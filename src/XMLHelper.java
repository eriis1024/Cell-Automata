
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author  Jeremy Chen (jc587)
 * 	Helper class that has utility methods, and serves as a superclass for all XMLHelpers for different simulation types
 *  Called by ParseXML to read in and initialize simulations from XML files
 *
 */
public abstract class XMLHelper {
	protected static final Grid fooGrid = new BasicGrid(5,5,new ArrayList<Cell>(), Color.BLACK);
	protected static final String WIDTH = "width";
	protected static final String HEIGHT = "height";
	protected static final String X = "x";
	protected static final String Y = "y";
	protected static final String STATE = "state";
	
	/**
	 * @param cellNodes
	 * @return Will return ArrayList<Cell> read from XML File
	 * 
	 * Will return an ArrayList of Cells taken from initial cell positions and states from the XML FIle
	 * 
	 */
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

	/**
	 * @param dims
	 * @return array of int, with 0th index being width, and 1st index being height
	 * 
	 * Will read in Node in XML File containing dimensions for Grid
	 */
	protected int[] getDimensions(NodeList dims) {
		Element dimensions = (Element)(dims.item(0));
		int width = Integer.parseInt(dimensions.getAttribute(WIDTH));
		int height = Integer.parseInt(dimensions.getAttribute(HEIGHT));
		return new int[] {width, height};
	}
	
	/**
	 * @return Will returns HashMap<String, Color> containing states specific to a simulation
	 * 
	 * Will return states specific to each type of XMLHelper's respective simulation
	 */
	public abstract HashMap<String, Color> getStates();
	
	/**
	 * @param dims
	 * @param cells
	 * @return Grid generated from XML
	 * 
	 * Will take in dimensions and cells to generate a Grid according to the XML specifications
	 */
	public abstract Grid getGrid(NodeList dims, ArrayList<Cell> cells);
	public abstract Simulation initSimulation(NodeList params, Grid g);
	
    /**
     * @param e
     * @param attributeName
     * @return
     * 
     * Simple helper class that returns the Element's Attribute in string form
     */
    protected String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }
}
