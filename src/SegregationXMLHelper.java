import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;

public class SegregationXMLHelper extends XMLHelper{
	
	private static final SimulationSegregation fooSim = new SimulationSegregation(fooGrid, 0);
	private static final String PROBABILITY = "prob";
	
	public Grid getGrid(NodeList dims, ArrayList<Cell> cells) {
		int[] dimensions = getDimensions(dims);
		return new BasicGrid(dimensions[0], dimensions[1], cells, FireSimulation.DEFAULT_COLOR);
	}
	
	public HashMap<String, Color> getStates(){
		return fooSim.getStates();
	}
	
	public Simulation initSimulation(NodeList params, Grid g) {
		double prob = Double.parseDouble(((Element)(params.item(0))).getAttribute(PROBABILITY));
		return new FireSimulation(g, prob);
	}
}
