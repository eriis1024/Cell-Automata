import java.util.ArrayList;

public class BasicNeighborhood implements Neighborhood{
	public ArrayList<Cell> getNeighbors(Grid g, Cell c){
		int x = c.getX();
		int y = c.getY();
		
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++)
			{		
				if(i!=0 && j!=0) {
					int nX = x + i;
					int nY = y + j;
					if(nY>=0 && nY < g.getHeight() && nX>=0 && nX < g.getWidth()) neighbors.add(g.get(nX, nY));
				}
			}
		}
		return neighbors;
	}
}
