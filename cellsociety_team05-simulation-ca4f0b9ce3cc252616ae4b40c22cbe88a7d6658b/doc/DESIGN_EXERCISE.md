Jeremy Chen (jc587)
Maya Messinger (mm479)
Erik Riis (evr4)

# Cell Society

General:
User gives XML file configured to our specifications. XML contains simulation type, 
* cell class at core of project
* create wrapper class that's a more empowered 2D array?

Classes:
* public class ParseXML{}
	* parse XML data into usable forms, initialize grid
	* loadUI()
	* loadXML(filepath)
	* buildRuleObject()
	* buildGrid()
	* getGrid()
	* getRule()

* public abstract class Rule{}
	* Subclasses:
		* ConwayRule
			* 
		* PredPreyRule
			* 
		* SegregationRule
			* 
		* FireRule
			* 
		* c.setState(rule.getNewState(c))

* public class State{}
	* stateNum
	* color
	
* public class Viewer{}
	* scene
	* paintGrid()
	* showGrid(grid)

* Runner - runs simulation
	* update()
	* step()
	* start()
	* pause()

* Grid - contains cells based on user-input dimension. Basically smart wrapper class for 2d array
	* height
	* width
	* getCell(x, y)
	* setCell(x, y)
	* rule

* Cell
	* state
	* color
	* position
	* neighborsAlive, neighborsDead, etc
	* 9 (possible) subclasses - middle, top, left, right, bottom, and 4 corners