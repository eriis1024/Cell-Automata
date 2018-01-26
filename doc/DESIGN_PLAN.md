Cell Society Team 05 DESIGN_PLAN
===
### Authors
Jeremy Chen, jc587
Maya Messinger, mm479
Erik Riis, evr4

## Introduction

This project is an animation of several cellular automata (CA) simulations. Provided a type of CA simulation and its conditions for maintaining/changing cell states (so far simulation types are Game of Life, predator-prey, segregation, and fire spreading), dimensions of a 2D grid in which the cells will be displayed, and initial starting states of cells, the program should iteratively update cell states and animate the updates for an undefined number of cycles.

Some design goals of the project include writing code that is flexible, meaning that it is written without knowing the exact details of the next steps beforehand. The code should written in such a way that it will be able to easily handle later parts being added on. Another goal includes writing clean code that is formatted and structured consistently while adhering to modern coding standards. 

The primary architecture of this CA design will include several abstract classes - at least Cell, Runner, Simulation. Following the idea of “open for extension, closed for modification”, there will be a large use of abstraction and subclasses. Each class should be closed to method modifications, and instead of accessing classes having methods to determine subclasses, individual subclasses should instead implement their own version of an abstract method, so only Superclass.method() needs to be called and no code needs to be edited in order to implement new features.

### Overview
This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe specific components you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other. It should also include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should discuss specific classes, methods, and data structures, but not individual lines of code.

* public class Runner extends Application: 
* public class ParseXML: 
* Given an XML file, will return an instance of Grid, with appropriate dimensions, initial layout, and simulation type
* public class Viewer
* Paints grid onto scene
* public class Grid{}
	* Contains 2D array of Cell
* Contains Rule matching simulation type 
* public abstract class Cell{}
	* Has a state and a color attached to that state
* public class ConwayCell{}
* public class PredPreyCell{}
* public class SegregationCell{}
* public class FireCell{}
* public abstract Class Rule{}
	* getNeighbors(Cell c)
	* getNewState(Cell c)
* public class ConwayRule extends Rule{}
* public class PredPreyRule extends Rule{}
* public class SegregationRule extends Rule{}
* public class FireRule extends Rule{}

### User Interface

The user interface will consist of six main elements:
1. **Choose a Simulation**: This is the first button the user should click. All other features are greyed out and not available until the user chooses an XML file for a simulation. As seen in the above images, when the button is clicked, a window will appear, prompting the user to choose an XML file in the file system. We will use FileChooser with an ExtensionFilter to provide the GUI when the user clicks browse available XML files in their filesystem. Our program will validate the file, and if it is valid, the user has use of the other UI elements. If the XML is invalid, another window (using vMenu) will appear, prompting the user to choose another XML file.
2. **Current Simulation**: This simply displays the current simulation that is loaded into the program.
3. **Play**: This button is enabled when the simulation is not playing, and a proper simulation has been loaded. It will run the simulation at the selected frame rate.
4. **Pause**: This button is enabled (not greyed out) when the simulation is currently playing. Pressing this button will pause the simulation.
5. **Step**: This button is enabled (not greyed out) when the simulation has not been played yet, or is currently paused. It will proceed one step in the simulation when pressed.
6. **Speed**: The user can input a positive integer value to determine how many frame updates they would like per second. This feature affects the refresh rate when the simulation is “Playing”. The default Speed is 1 frame per second.
	
### Design Details
This section describes each component introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). It should describe how each component handles specific features given in the assignment specification, what resources it might use, how it collaborates with other components, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Include the steps needed to complete the Use Cases below to help make your descriptions more concrete. Finally, justify the decision to create each component with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say.

* public class Runner extends Application{}
* public class ParseXML{}
* public class Viewer: Paints grid onto scene
* public class Grid{}
	* public 
* public abstract class Cell{}
	* Contains HashMap
* public class ConwayCell{}
* public class PredPreyCell{}
* public class SegregationCell{}
* public class FireCell{}
* public abstract class Rule{}
	* getNeighbors(Cell c)
	* getNewState(Cell c)
* public abstract class NeighborHood{}
	* getAdjacentNeighbors()
	* getSkipOneNeighbors()
	* getSkipTwoNeighbors()
* public class ConwayRule extends Rule{}
* public class PredPreyRule extends Rule{}
* public class SegregationRule extends Rule{}
* public class FireRule extends Rule{}

### Design Considerations

Before we start working on creating the project, design considerations we need to address or have addressed are as follows.
* We need to define how to implement cell states in different simulations (alive, dead, color, on fire, etc). Because the list of possible types of cells is not static, we need to abstract state as an aspect of each Cell, and determine what information the method holds while maintaining the ability to implement new types of simulations or conditions. Previously we had thought that State would be its own class, but realized that making it an object wouldn’t really serve any purpose, and to make it a variable in each cell was better.
* Determining what classes specific methods should be in has taken a lot of time for us. We have ideas of some methods that should exist and do x, but determining the best class to place these methods in took a lot of time to decide/agree on.
* What class should hold the actual Scene that contains the animation (makes sense for Runner to have the Scene, but calling the objects on the scene happens repeatedly from other classes - should we put the Scene inside whichever class calls the objects most?). We have a Viewer class that refreshes the Scene (enacts step), but we also could make a Scene within Grid, or even as something called from ParseXML.
* What should be the function of Cell? Despite Cell being the foundation of this project, we kind of think it better for Cell to just have a few simple variables - currState, nextState and color - and other classes are the smart classes that act upon cells. At what level should changing cell state be implemented? We’re still deciding if the grid should modify the cells placed within it, as it can access indices (therefore surrounding cells) more easily than individual cells can. For scalability, implementing a feature that affects many things might be done at the level of a larger object. Or, because there are so many Cells, each one having methods that call on conditions that affect their state would be helpful.
* How do we handle getNeighbors() method? Because the definition of neighbor is not just “adjacent to current cell”, what class makes the most sense for implementation? Can cells access cells around the Grid? Does it make more sense for the grid to access all its cells and do a check rather than a cell calling up to its grid and then looking back down to check individual cells?

Assumptions/dependencies:
* It seems like ParseXML will need to have an if tree that creates different types of Rules (simulations) based on “if xmlfile.simType = this”. This means that we will only be able to implement a whitelist of grids, and the ParseXML file will throw errors if any formatting within the XML file is unsupported.

### Team Responsibilities
This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.

#### Maya

#### Erik

#### Jeremy
