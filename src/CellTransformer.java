
import javafx.scene.shape.Rectangle;

public class CellTransformer {
	
	public static Rectangle toRectangle(Cell cell) {
		Rectangle rectCell = new Rectangle(cell.getX()+200, cell.getY() + 40, 5, 5);
		rectCell.setFill(cell.getColor());
		return rectCell;
	}

}
