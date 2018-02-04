import java.util.ArrayList;

import javafx.scene.paint.Color;

public class FireSimulation extends Simulation{
	
	private static final String BURNING = "BURNING";
	private static final String EMPTY = "EMPTY";	
	private static final String TREE = "TREE";	
	private double burningProb;
	public static final Color DEFAULT_COLOR = Color.GRAY;
	
	public FireSimulation(Grid g, double prob) {
		super(g);
		burningProb = prob;
		myNeighborhood = new NonDiagNeighborhood();
		initStates();
	}
	
	protected void initStates() {
		states.put(BURNING, Color.RED);
		states.put(TREE, Color.GREEN);
		states.put(EMPTY, Color.GRAY);
	}
	
	protected String nextState(Grid g, Cell c, Neighborhood n) {
		ArrayList<Cell> neighbors = n.getNeighbors(g,  c);
		boolean nburning = false;
		for(Cell nc: neighbors) if(nc.getColor() == states.get(BURNING)) nburning = true;
		if(c.getColor() == states.get(TREE)) {
			if (nburning && Math.random() <= burningProb) return BURNING;
			return TREE;
		}
		else if(c.getColor() == states.get(BURNING)) return EMPTY;
		return EMPTY;
	}
}
