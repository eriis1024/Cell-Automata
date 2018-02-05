import java.util.ArrayList;

public class WrapNeighborhood extends NonDiagNeighborhood{

	@Override
	public ArrayList<Cell> getNeighbors(Grid g, Cell c){
		
		int x = c.getX();
		int y = c.getY();
		
		ArrayList<Cell> neighbors = super.getNeighbors(g, c);
		
		for(int i = -1; i <= 1; i+=2) {
			int nX = x + i;
			int nY = y + i;
			if(nX<0) neighbors.add(g.get(g.getWidth()-1, y));
			else if(nX>=g.getWidth()) neighbors.add(g.get(0, y));
			if(nY<0) neighbors.add(g.get(x, g.getHeight()-1));
			else if(nX>=g.getWidth()) neighbors.add(g.get(x, 0));
		}
		return neighbors;
	}

}
