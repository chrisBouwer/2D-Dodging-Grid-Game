//Christiaan Bouwer
import java.awt.event.KeyEvent;

public class Player implements GameObject {
	
	private Game game;
	private Grid grids;
	private Location location;
	
  public void init(Game game, Grid grid, Location location) {
    // Initialize the player object with a reference to the game, grid and the 
    // player's starting location.
	  this.game = game;
      this.grids = grid;
      this.location = location;
  }
  
  public GameObject collision(GameObject otherObject) {
    // Handle a player object's collision with another object. Return the object that
    // remains if this collision occurs. Note that you can use the "instanceof" keyword
    // to test whether otherObject is of a given type.
	  /*
	   * just a nested if statement to check when the player collides with an object
	   */
	  if (otherObject instanceof GoodThing) {
          Game.globalVarGame.adjustScore(1);
      } else if(otherObject instanceof BadThing){
          Game.globalVarGame.signalGameOver();
          
      }
      return this;
  }
  
  public Location move() {
    // Calculate the player's new location. Here, you should handle
    // keyboard input from the player. It is suggested that you update
    // the player's location every 100ms.
	  /*
	   * Moves the player according to the input from the user
	   */
	  if (Game.globalVarGame.getTimeElapsed() % 100 == 0) {
          int x = location.getCol(), y = location.getRow();
          
          if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
              y++;
          } else 
        	  if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
        		  y--;
          }
          
          Location locat = new Location(y, x);
          
          if (grids.isValid(locat) == true) {
              location = locat;
          }
      }
      return location;
  }
  
  public void draw(int centerX, int centerY, int cellSize) {
    // Draw the player at location x and y, with width/height of cellSize.
    // You can use user.gif as a test image for this.
	  
	  StdDraw.picture(centerX, centerY + 8, "Shark.png", cellSize, cellSize);
  }
}