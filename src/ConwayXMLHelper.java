
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;

/**
 * @author Jeremy Chen (jc587)
 *
 *	Child of XMLHelper that parses XML files for Conway simulation
 *
 */
public class ConwayXMLHelper extends XMLHelper{
	
	private static final SimulationConway fooSim = new SimulationConway(fooGrid);
	
	/* (non-Javadoc)
	 * @see XMLHelper#getGrid(org.w3c.dom.NodeList, java.util.ArrayList)
	 */
	public Grid getGrid(NodeList dims, ArrayList<Cell> cells) {
		int[] dimensions = getDimensions(dims);
		return new BasicGrid(dimensions[0], dimensions[1], cells, SimulationConway.DEFAULT_COLOR);
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
		return new SimulationConway(g);
	}
}
