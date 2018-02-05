import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;

public class PredPreyXMLHelper extends XMLHelper {

	private static final SimulationWaTor fooSim = new SimulationWaTor(fooGrid);
	
	public ArrayList<Cell> getCells(NodeList cellNodes) {
		for(int i = 0; i<cellNodes.getLength(); i++) { 
			System.out.println();
		}
		return null;
	}
	
	public Grid getGrid(NodeList dims, ArrayList<Cell> cells) {
		return null;
	}
	
	public HashMap<String, Color> getStates(){
		return fooSim.getStates();
	}
	
	public Simulation initSimulation(NodeList params, Grid g) {
		return null;
	}
}
