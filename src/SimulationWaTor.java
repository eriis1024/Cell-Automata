/**
 * @author Maya Messinger (mm479)
 * Started 31 Jan 18
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

public class SimulationWaTor extends Simulation	{
	public static final Color DEFAULT_COLOR = Color.BLUE;
	public static final int START_PREY = ;
	public static final int START_PRED = ;
	public static final int PREY_BREED_AGE = ;
	public static final int PRED_BREED_AGE = ;
	public static final int PRED_STARVE_AGE = ;

	public SimulationWaTor()	{
		super();
		possStates = new HashMap<String, Color>()	{{
			put("FREE", Color.BLUE);
			put("PREY", Color.YELLOW);
			put("PREDATOR", Color.BLACK);
		}};
		neighborhood = new NonDiagNeighborhood();
	}
}