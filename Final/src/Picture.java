import java.io.Serializable;

/**
 * This class is used to hold information about objects that will be drawn to the screen.
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 *
 */
public class Picture implements Serializable {
	
	String name;
	
	private int xloc;
	private int yloc; 
	
	private int width;
	private int height;
    
	private int widthOrig;
	private int heightOrig;
	
	private Direction direction;
	
	private boolean tagged = false;
	
	/**
	 * Constructor with information about the image's location, size, and direction enum.
	 * @param x the starting x position of the image.
	 * @param y the starting y position of the image.
	 * @param w the scaled width of the image.
	 * @param h the scaled height of the image.
	 * @param ow the original width of the image.
	 * @param oh the original height of the image.
	 * @param d the direction enum of the image.
	 */
	public Picture(int x, int y, int w, int h, int ow, int oh, Direction d) {
		xloc = x;
		yloc = y;
    	width = w;
    	height = h;
    	widthOrig = ow;
    	heightOrig = oh;
    	direction = d;
    	
    }
	/**
	 * Constructor with information about the image's location, size. No direction is give.
	 * @param x the starting x position of the image.
	 * @param y the starting y position of the image.
	 * @param w the scaled width of the image.
	 * @param h the scaled height of the image.
	 * @param ow the original width of the image.
	 * @param oh the original height of the image.
	 */
	public Picture(int x, int y, int w, int h, int ow, int oh) {
		xloc = x;
		yloc = y;
    	width = w;
    	height = h;
    	widthOrig = ow;
    	heightOrig = oh;
    	
    }
	/**
	 * Constructor used for the fish tagging minigame. Adds a parameter boolean t which determines if the fish,
	 * which is what the Picture objects that use this constructor represent, is tagged or not. 
	 * @param x the starting x position of the image.
	 * @param y the starting y position of the image.
	 * @param w the scaled width of the image.
	 * @param h the scaled height of the image.
	 * @param n the name of the fish.
	 * @param d the direction of the image.
	 * @param t boolean to determine if the fish is tagged.
	 */
	public Picture(int x, int y, int w, int h, String n, Direction d, boolean t) {
		xloc = x;
		yloc = y;
    	width = w;
    	height = h;
    	name = n;
    	direction = d;
    	tagged = t;
	}
	
	/**
	 * Getter method
	 * @return the x location of the image
	 */
	public int getXloc() { return xloc; }
	/**
	 * Getter method
	 * @return the y location of the image
	 */
	public int getYloc() { return yloc; }
	/**
	 * Getter method
	 * @return the height of the image.
	 */
	public int getHeight() { return height; }
	/**
	 * Getter method
	 * @return the width of the image.
	 */
	public int getWidth() { return width; }
	/**
	 * Getter method
	 * @return the original width of the image
	 */
	public int getOrigWidth() { return widthOrig; }
	/**
	 * Getter method
	 * @return the original height of the image.
	 */
	public int getOrigHeight() { return heightOrig; }
	/**
	 * Getter method
	 * @return the Direction of the image
	 */
	public Direction getDirection() { return direction; }
	/**
	 * Getter method
	 * @return the boolean that determines if a fish has been tagged.
	 */
	public boolean getTagged() { return tagged; }
	/**
	 * Getter method
	 * @return the name of the image
	 */
	public String getName() { return name; }
	
	/**
	 * Setter method
	 * @param x value used to set the new value of xloc
	 */
	public void setXloc(int x) { xloc = x; }
	/**
	 * Setter method
	 * @param y value used to set the new value of yloc
	 */
	public void setYloc(int y) { yloc = y; }
	/**
	 * Setter method
	 * @param d the direction enum used to set the new value of direction
	 */
	public void setDirection(Direction d) { direction = d; }
	/**
	 * Setter method
	 * @param b the boolean used to set the new value of tagged
	 */
	public void setTagged(boolean b) { tagged = b; }
	/**
	 * Setter method
	 * @param n the String used to set the new value of name
	 */
	public void setName(String n) { name = n;}

}
