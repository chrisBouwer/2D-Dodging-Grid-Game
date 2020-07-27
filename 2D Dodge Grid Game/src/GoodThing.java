//Christiaan Bouwer
public class GoodThing implements GameObject {  
	
    private Location location;
    private Game game;
    private Grid grid;
    
  public void init(Game game, Grid grid, Location location) {
    // Initialize the player object with a reference to the game, grid and the 
    // player's starting location.
	  this.game = game;
      this.location = location;
      this.grid = grid;
  }
  
  public GameObject collision(GameObject otherObject) {
    // What does the object do if it collides with another object? Return the object
    // that remains if these two objects collide. Note that you can use the "instanceof" keyword
    // to test whether otherObject is of a given type.
	  /*
	   * When a collision occurs points are added
	   */
	  if (otherObject instanceof Player) {
          Game.globalVarGame.adjustScore(1);
      }
      return otherObject;
  }
  
  public void draw(int centerX, int centerY, int cellSize) {
    // Draw the object at location centerX, centerY with 
    // width/height of cellSize by cellSize. You can use get.gif as a placeholder
    // image for this.
	  /*
	   * Adds the image of the good thing(fish)
	   */
	  StdDraw.picture(centerX, centerY + 8, "Fish.png", cellSize, cellSize);
  }

  public Location move() {
    // Update the position of this object, and return the new position. This should
    // move the object to the left.
    // It is recommended that you move the object every 300ms as an initial choice.
	  /*
	   * Moves the good thing to the left every 300ms
	   */
	  if (Game.globalVarGame.getTimeElapsed() % 300 == 0) {
          int x = location.getCol(), y = location.getRow();
          x--;
          Location locat = new Location(y, x);
          location = locat;
      }
	  
      return location;
  }
}