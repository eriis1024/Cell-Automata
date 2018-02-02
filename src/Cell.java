import javafx.scene.paint.Color;

public class Cell{
	private Color myColor;
	private int myX;
	private int myY;
	
	public Cell (int x, int y, Color c) {
		myX=x;
		myY=y;
		myColor = c;
	}
	
	public int getX() {
		return myX;
	}
	
	public int getY() {
		return myY;
	}
	
	public Color getColor() {
		return myColor;
	}
	
	public void setColor(Color c) {
		myColor = c;
	}
	
	public Cell copy() { //deep copy
		return new Cell(myX, myY, myColor);
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Cell && myColor == ((Cell)other).getColor();
	}
}
