
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;

public class ConwayXMLHelper extends XMLHelper{
	
	private static final SimulationConway fooSim = new SimulationConway(fooGrid);
	
	public Grid getGrid(NodeList dims, ArrayList<Cell> cells) {
		int[] dimensions = getDimensions(dims);
		return new BasicGrid(dimensions[0], dimensions[1], cells, SimulationConway.DEFAULT_COLOR);
	}
	
	public HashMap<String, Color> getStates(){
		return fooSim.getStates();
	}
	
	public Simulation initSimulation(NodeList params, Grid g) {
		return new SimulationConway(g);
	}
}
