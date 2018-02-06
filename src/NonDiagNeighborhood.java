import java.util.ArrayList;

/**
 * @author Jeremy Chen
 *	Defines "neighbors" as all Cells one coordinate space away, excluding diagonal neighbors, implements the Neighborhood interface
 */
public class NonDiagNeighborhood implements Neighborhood{
	/* (non-Javadoc)
	 * @see Neighborhood#getNeighbors(Grid, Cell)
	 */
	public ArrayList<Cell> getNeighbors(Grid g, Cell c){
		int x = c.getX();
		int y = c.getY();
		
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		for(int i = -1; i <= 1; i+=2) {
			int nX = x + i;
			int nY = y + i;
			if(nX>=0 && nX < g.getWidth()) neighbors.add(g.get(nX, y));
			if(nY>=0 && nY < g.getHeight()) neighbors.add(g.get(y, nY));
		}
		return neighbors;
	}
}
