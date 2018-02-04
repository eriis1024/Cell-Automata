package cellsociety_team05;

import java.util.*;
import java.util.HashMap;
import javafx.scene.paint.Color;

public abstract class Simulation {
	
	protected HashMap<String, Color> states;
	protected Grid myGrid;
	protected Neighborhood myNeighborhood;
	public static final List<String> SUPPORTED_TYPES = Arrays.asList(new String[] {
	        "Conway",
	        "Segregation",
	        "Fire",
	        "PredPrey"});
	public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
	        "head",
	        "dimensions",
	        "cells"});
	
	public Simulation(Grid g) {
		myGrid = g;
		states = new HashMap<String, Color>();
		myNeighborhood = new BasicNeighborhood();
	}
	
	public void update() {
		Grid nextGrid = myGrid.copy();
		
		for(int i=0; i<myGrid.getWidth(); i++) {
			for(int j=0; j<myGrid.getHeight(); j++) {
				Color nc = states.get(nextState(myGrid, myGrid.get(i, j), myNeighborhood));
				nextGrid.set(i,j, nc);
			}
		}
		myGrid = nextGrid;
	}
	
	protected abstract String nextState(Grid g, Cell c, Neighborhood n);
		
	public HashMap<String, Color> getStates(){
		return states;
	}
	
	protected abstract void initStates();
	
	public Grid getGrid() {
		return myGrid;
	}
	
	public Neighborhood getNeighborhood() {
		return myNeighborhood;
	}
}
