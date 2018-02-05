import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Cell extends Polygon{
	private int myX;
	private int myY;
	
	public Cell (Color color, int x, int y) {
		myX=x;
		myY=y;
		colorCell(color);
	}
	
	public int getX() {
		return myX;
	}
	
	public int getY() {
		return myY;
	}
	
	public Color getColor() {
		return (Color)getFill();
	}
	
	public void colorCell(Color c) {
		setFill(c);
	}
	
	public Cell copy() { //deep copy
		return new Cell((Color)getFill(), myX, myY);
	}
}
