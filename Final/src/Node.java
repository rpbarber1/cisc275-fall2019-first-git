import java.io.Serializable;

/**
 * This class contains two ints used to represent and x and y location. It also contains a Direction enum.
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 */

public class Node implements Serializable {
	
	int xloc;
	int yloc;
	
	Direction direction;
	/**
	 * Constructor intializes xloc, yloc, and direction d.
	 * @param x The x location of the node.
	 * @param y The y location of the node.
	 * @param d The direction of the node.
	 */
	public Node(int x,int y, Direction d) {
		xloc = x;
		yloc = y;
		direction = d;
		
	}
	/**
	 * Setter method
	 * @param x The value used to set the new value of xloc.
	 */
	public void setX(int x) { xloc = x; }
	/**
	 * Setter method
	 * @param y The value used to set the new vaue of yloc.
	 */
	public void setY(int y) { yloc = y; }
}