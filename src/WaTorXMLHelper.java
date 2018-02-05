import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;

public class WaTorXMLHelper extends XMLHelper{
	
	private static final SimulationWaTor fooSim = new SimulationWaTor(fooGrid, 0, 0, 0, 0);
	private static final String PREY_AGE = "prey_age";
	private static final String PRED_AGE = "pred_age";
	private static final String PRED_ENERGY = "pred_energy";
	private static final String PRED_REGAIN_ENERGY = "pred_regain_energy";
	private static final String START_ENERGY = "e_start";
	
	public Grid getGrid(NodeList dims, ArrayList<Cell> cells) {
		int[] dimensions = getDimensions(dims);
		return new BasicGrid(dimensions[0], dimensions[1], cells, SimulationWaTor.DEFAULT_COLOR);
	}
	
	public HashMap<String, Color> getStates(){
		return fooSim.getStates();
	}
	
	@Override
	public ArrayList<Cell> getCells(NodeList cellNodes) {
		Element cellNode = (Element)(cellNodes.item(0));
		ArrayList<Cell> cells = new ArrayList<Cell>();
		HashMap<String, Color> states = getStates();
		
		for (Node child = cellNode.getFirstChild(); child != null; child = child.getNextSibling()){ 
			if(child.getNodeName().equals("cell")) {
				Element cellElement = (Element)child;
				int x = Integer.parseInt(cellElement.getAttribute(X));
				int y = Integer.parseInt(cellElement.getAttribute(Y));
				String cellType = cellElement.getAttribute(STATE);
				Color cellColor = states.get(cellType);
				Cell c;
				if(cellType.equals("PREDATOR")) {
					int startEnergy = Integer.parseInt(cellElement.getAttribute(START_ENERGY));
					c = new CellPredator(cellColor, x, y, startEnergy);
				}
				else if(cellType.equals("PREY")) {
					c = new CellPrey(cellColor, x, y);
				}
				else c = new Cell(cellColor, x, y);
				cells.add(c);
			}
		}
		return cells;
	}
	
	public Simulation initSimulation(NodeList params, Grid g) {
		int preyAge = Integer.parseInt(((Element)(params.item(0))).getAttribute(PREY_AGE));
		int predAge = Integer.parseInt(((Element)(params.item(0))).getAttribute(PRED_AGE));
		int predEnergy = Integer.parseInt(((Element)(params.item(0))).getAttribute(PRED_ENERGY));
		int predRegainEnergy = Integer.parseInt(((Element)(params.item(0))).getAttribute(PRED_REGAIN_ENERGY));
		return new SimulationWaTor(g, preyAge, predAge, predEnergy, predRegainEnergy);
	}
}
