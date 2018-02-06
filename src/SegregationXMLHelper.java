import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;

/**
 * @author Jeremy Chen (jc587)
 *	Child of XMLHelper used to instantiate SimulationConway from XML files
 */
public class SegregationXMLHelper extends XMLHelper{
	
	private static final SimulationSegregation fooSim = new SimulationSegregation(fooGrid, 0);
	private static final String SIMILARITY = "similar";
	
	/* (non-Javadoc)
	 * @see XMLHelper#getGrid(org.w3c.dom.NodeList, java.util.ArrayList)
	 */
	public Grid getGrid(NodeList dims, ArrayList<Cell> cells) {
		int[] dimensions = getDimensions(dims);
		return new BasicGrid(dimensions[0], dimensions[1], cells, SimulationSegregation.DEFAULT_COLOR);
	}
	
	/* (non-Javadoc)
	 * @see XMLHelper#getStates()
	 */
	public HashMap<String, Color> getStates(){
		return fooSim.getStates();
	}
	
	/* (non-Javadoc)
	 * @see XMLHelper#initSimulation(org.w3c.dom.NodeList, Grid)
	 */
	public Simulation initSimulation(NodeList params, Grid g) {
		double similar = Double.parseDouble(((Element)(params.item(0))).getAttribute(SIMILARITY));
		return new SimulationSegregation(g, similar);
	}
}
