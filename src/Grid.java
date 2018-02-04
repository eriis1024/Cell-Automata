import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;

public interface Grid{
	
	public int getWidth();
	public int getHeight();
	public Cell get(int x, int y);
	public void set(int x, int y, Color c);
	public void insert(Cell c);
	public Grid copy();
}
