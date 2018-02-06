import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;

/**
 * @author Jeremy Chen (jc587)
 *	Subclass of XMLHelper that is used to instantiate SimulationFire from an XML file
 */
public class FireXMLHelper extends XMLHelper{
	
	private static final SimulationFire fooSim = new SimulationFire(fooGrid, 0);
	private static final String PROBABILITY = "prob";
	
	/* (non-Javadoc)
	 * @see XMLHelper#getGrid(org.w3c.dom.NodeList, java.util.ArrayList)
	 */
	@Override
	public Grid getGrid(NodeList dims, ArrayList<Cell> cells) {
		int[] dimensions = getDimensions(dims);
		return new BasicGrid(dimensions[0], dimensions[1], cells, SimulationFire.DEFAULT_COLOR);
	}
	
	/* (non-Javadoc)
	 * @see XMLHelper#getStates()
	 */
	@Override
	public HashMap<String, Color> getStates(){
		return fooSim.getStates();
	}
	
	/* (non-Javadoc)
	 * @see XMLHelper#initSimulation(org.w3c.dom.NodeList, Grid)
	 */
	@Override
	public Simulation initSimulation(NodeList params, Grid g) {
		double prob = Double.parseDouble(((Element)(params.item(0))).getAttribute(PROBABILITY));
		return new SimulationFire(g, prob);
	}
}
