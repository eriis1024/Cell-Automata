/**
 * @author Erik Riis
 */

import javafx.scene.shape.Rectangle;

public class CellTransformer {
	
	public static Rectangle toRectangle(Cell cell, int scaleX, int scaleY) {
		Rectangle rectCell = new Rectangle(cell.getX()*scaleX + 50, cell.getY()*scaleY + 50, scaleX - 1, scaleY - 1);
		rectCell.setFill(cell.getColor());
		return rectCell;
	}
}
