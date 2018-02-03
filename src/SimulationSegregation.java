/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public class SimulationSegregation extends Simulation	{
	public SimulationSegregation()	{
		possStates = new HashMap<String, Color>()	{{
			put("empty", Color.WHITE);
			put("x", Color.BLUE);
			put("o", Color.RED);
		}};
	}
}