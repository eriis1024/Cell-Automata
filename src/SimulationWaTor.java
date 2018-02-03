/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public class SimulationWaTor extends Simulation	{
	public SimulationWaTor()	{
		possStates = new HashMap<String, Color>()	{{
			put("free", Color.BLUE);
			put("prey", Color.YELLOW);
			put("predator", Color.BLACK);
		}};
	}
}