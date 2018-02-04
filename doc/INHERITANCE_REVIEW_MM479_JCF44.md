Maya Messinger (mm479)
Jack Fitzpatrick (jcf44)

Inheritance Review
===

### Part 1
1. Different types of cells (subclasses of abstract class Cell), each updates state itself and contains an ArrayList of its neighbors. Cell information is encapsulated within its subclasses, Grid will only call Cell.update(). Neighbords will also be encapsulated, Cells themselves will not have access to that information.
2. Cell has subclasses based on states, ex. AliveCell and DeadCell. No simulation class, cells themselves determine their states and when you want a new type of simulation, you add a new type of cell to the grid. These cells will act differently upon their neighbors, and also have different requirements for changing based on their state. But all types of cells, all have states and act on other cells (have neighbors). No other inheritance heirarchies.
3. Cell class is very closed, has a very specific format to follow, but once make a new cell subclass, most flexible part is updateState() method. Most openness happens when you make a new subclass - as it should be, open to extension but not modification.
4. 
5. Lots of encapsulation within Cell classes, information doesn't have to be handled by other classes accessing cells. Likes it because adding a simulation doesn't take a whole new class, only need to make one new Cell and edit one method within it.

### Part 2
1. Trying to limit that dependencies on other classes. Only link between work and others' is interface between Cell and Grid (which places cell). 
2. Dependency with Grid is based on behavior.
3. Changing dependencies to behaviorally-based over implementation-based. Have fewer paramenter variables within dependent classes/methods.
4. Cell superclass has state, getNeighborState, and neighborStates. Subclasses take care of determining next state based on neighborStates. Has a good idea of what instance variables should be in Cell superclass vs subclasses, instance variables all initialized in superclass and all neighbor information gotten by superclass, but subclasses have their own methods for determining how to deal with the neighbor information. Really strongly written classes, commonalities and differences in right classes.

### Part 3