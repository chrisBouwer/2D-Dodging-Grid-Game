//Christiaan Bouwer
public class BadThing implements GameObject {
	
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
	   * When a collision occurs then the game over screen is displayed
	   */
	  GameObject col = this;
      if (otherObject instanceof Player) {
          Game.globalVarGame.signalGameOver();
          col = otherObject;
      }
      return col;
  }
  
  public void draw(int centerX, int centerY, int cellSize) {
    // Draw the object at location centerX, centerY with 
    // width/height of cellSize by cellSize. You can use avoid.gif as a placeholder
    // image for this.
	  /*
	   * Adds the image of the bad thing(missile)
	   */
	  StdDraw.picture(centerX, centerY + 8, "Missile.png", cellSize, cellSize);
  }

  public Location move() {
    // Update the position of this object, and return the new position. This should
    // move the object to the left.
    // It is recommended that you move the object every 300ms as an initial choice.
	  /*
	   * Moves the bad thing to the left every 300ms
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