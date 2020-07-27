//Christiaan Bouwer
import java.awt.Color;

public class Grid {
  // Add instance variables.
    private Game game;
    private final int CellSize;
    private final int NumberRows;
    private final int NumberCols;
	private GameObject grid[][];

  public Grid(int numRows, int numCols, int gridCellSize)
  {
    // Initialize a net grid with the given dimensions (rows by columns). Each cell
    // in the grid should be draw with width and height of gridCellSize on the
    // screen. Calls reset() to reset the state of the grid to what it should be at
    // the start of the game.
	  /*
	   * Initializes the grid with the specific dimensions that it receives
	   * Then resets the grid to the default
	   */
      this.CellSize = gridCellSize;
      this.NumberRows = numRows;
      this.NumberCols = numCols;
      grid = new GameObject[numRows][numCols];
      reset();
  }

  public void reset()
  {    
    // Set up an initial grid of game objects. This could be just an empty grid, but
    // this depends on how you want your game to start off initially.
    // This can be called from outside to reset the board at the start of play after
    // a game over condition has been reached.
    // Remember to include a Player object on this new grid, otherwise you won't
    // have anything to control in the game!
	  /*
	   * Set the default grid with the player object
	   */
	  for (int i = 0; i < getNumRows(); i++) {
          for (int j = 0; j < getNumCols(); j++) {
              grid[i][j] = null;
          }
      }
	  
	  Player player = new Player();
      Location playerLocat = new Location(0, 0);
      player.init(game, this, playerLocat);
      setObjectAt(playerLocat, player);
	  
  }
  
  public void populateRightEdge()
  {
    // Place new objects at the rightmost edge of the grid. This function depends
    // entirely on you, but make sure that this results in interesting gameplay.
	  
	  /*
	   * Generates random numbers, depending on the number a good thing or bad thing is 
	   * then generated on the right
	   */
	  int newY = (int) (Math.random() * (NumberRows));
      int spawnType = (int) (Math.random() * (10));

      if (spawnType < 2) {
    	  
          GoodThing point = new GoodThing();
          Location pointLoc = new Location(newY, NumberCols - 1);
          
          point.init(game, this, pointLoc);
          setObjectAt(pointLoc, point);

      } else if (spawnType > 4) {
          BadThing avoid = new BadThing();
          Location avoidLoc = new Location(newY, NumberCols - 1);
          
          avoid.init(game, this, avoidLoc);
          setObjectAt(avoidLoc, avoid);
      }
  }
  
  public int getNumRows()
  {
    // Return the number of rows in the grid.
    return NumberRows;
  }
  
  public int getNumCols()
  {
    // Return the number of columns in the grid.
    return NumberCols;
  }
  
  
  public GameObject getObjectAt(Location loc) {
    // Return the object at location loc.
    return grid[loc.getRow()][loc.getCol()];
  }
  
  
  public void setObjectAt(Location loc, GameObject obj) {
    // Set the object at location loc.
	  grid[loc.getRow()][loc.getCol()] = obj;
  }
  
  public int getCellSize() {
    // Return the cell size of the grid.
    return CellSize;
  }
  
  public void moveAll() {
    // Ask each object in the grid what its new location should be. Make a new array
    // representing the objects in the grid at their new locations. If two objects
    // ask to be moved to the same location, resolve this by calling the collision 
    // method on one of them and use the result of that in the new grid's 
    // corresponding location.
	  /*
	   * Create a 2D array with nested for loops used to move the objects to the left one step
	   */
	  GameObject tempGrid[][] = new GameObject[NumberRows][NumberCols];
      for (int i = 0; i < getNumRows(); i++) {
          for (int j = 0; j < getNumCols(); j++) {
              if (grid[i][j] != null) {
                  
                  Location newLoc = grid[i][j].move();
                  if (newLoc.getCol() < 0) {
                      grid[i][j] = null;
                  } else {
                      
                      if (tempGrid[newLoc.getRow()][newLoc.getCol()] != null) {
                          tempGrid[newLoc.getRow()][newLoc.getCol()] = grid[i][j].collision(tempGrid[newLoc.getRow()][newLoc.getCol()]);    
                      } else {
                          tempGrid[newLoc.getRow()][newLoc.getCol()] = grid[i][j];
                      }
                      
                  }
              }
          }
      }
      grid = tempGrid;
  }

  public void draw(int atX, int atY) {
    // Draw every object in the grid, going through every row and column. The origin
    // of the grid should be at drawAtX and drawAtY. You must delegate each object's
    // painting the object itself.
	  /*
	   * Using nested for loops I place every object in each position
	   */
	  for (int i = 0; i < NumberRows; i++) {
          for (int j = 0; j < NumberCols; j++) {
              if (grid[i][j] != null) {
                  grid[i][j].draw(j * CellSize + (getCellSize() / 2) + atX, i * CellSize + (getCellSize() / 2) + atY, getCellSize());
              }
          }
      }
  }

  public boolean isValid(Location loc)
  {
    // Returns true if the location Loc is on the grid, otherwise it returns false.
	  /*
	   * Checks weather or not the location is out of bounds
	   */
	  if (loc.getRow() >= getNumRows() || loc.getCol() >= getNumCols() || loc.getCol() < 0 || loc.getRow() < 0) {
          return false;
      } else {
          return true;
      }
  }
}