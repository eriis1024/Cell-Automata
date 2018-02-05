Inheritance Review Questions
===

#### Jeremy Chen & Samson (sr307)


## Part 1

#### What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?
We both hide a 2D array of Cell objects in our Grid class that is not visible by other areas of our program. It is accessed with getter and setter methods.

#### What inheritance hierarchies are you intending to build within your area and what behavior are they based around?
We have a similarly structured Grid and Simulation inheritance hierarchy, but Samson's group is using an inheritance hierarchy for their Cell object and State objects for waht color the cell takes.

#### What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?
The superclasses that serve as interfaces are open to all their children, while the children are not open to one another. This is so that only desired shared behavior & fields are inherited and accessible.

#### What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?
We handle IndexOutOfBounds within our Grid class, in case Cells with invalid indices are put into my Grid. Additionally, we will be throwing a custom error if a wrong cell type or cell state is attempted to be created in the wrong simulation/game type.

#### Why do you think your design is good (also define what your measure of good is)?
Flexibility is our main measurement of how "good" our design is. Being able to add game types and other features easily means our design is flexible. We use interfaces throughout our design, which all make our game more flexible.

## Part 2

#### How is your area linked to/dependent on other areas of the project?
Frontend will need to call our areas to create the objects they need to display. XML will instantiate our Grid and our Simulation, with user defined fields.

#### Are these dependencies based on the other class's behavior or implementation?
All other components of the program depend on backend, while backend is somewhat self-standing in that respect. XML requires the constructor to be structured ina  certain way for Grid, but that is a minimal dependency.

#### How can you minimize these dependencies?
There are little to no dependencies to minimize.

#### Go over one pair of super/sub classes in detail to see if there is room for improvement. Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).
One improvement for me: Possible fold the Neighborhood class into respective Grid classes, as getNeighbor() behavior is consistent across simulations for a specific grid. Our superclasses all have Width, Height, get, and set, while our child classes are currently square grids, with defined behavior for neighbors and updating.

Part 3

#### Come up with at least five use cases for your part (most likely these will be useful for both teams).
1. GUI/Runner queries Simulation/Grid to make one update (STEP)
2. GUI/Runner queries Simulation/Grid to make many updates in succession (PLAY)
3. XMLParser takes an XML file to initialize our Grid and choose a simulation
4. User chooses a new simulation in the UI, XMLParser has to load new parameters, and creates new Grid and Simulation
5. User attempts to load XML with bad positions (out of bounds) for cells or cells with the wrong states/colors: Grid will throw an exception

#### What feature/design problem are you most excited to work on?
I am most excited to define the format of my XML documents and create intentionally flawed XML document to troubleshoot error throwing.

#### What feature/design problem are you most worried about working on?
I am most worried about integration, and the different depencies required by the other parts of the program.