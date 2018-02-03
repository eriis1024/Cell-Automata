import java.util.ArrayList;

public class NonDiagNeighborhood implements Neighborhood{
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
