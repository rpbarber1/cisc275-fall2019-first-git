
import java.io.Serializable;

/**
 * This class contains information for drawing the RunOff that is blocked by the plants
 * in the barrier game
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 *
 */
public class RunOff implements Serializable{
	int Xloc;
	int Yloc;
	int height;
	int width;
	int spotNumber;
	public RunOff(int Xloc, int Yloc, int spotNumber, int height, int width) {
		this.Xloc=Xloc;
		this.Yloc=Yloc;
		this.spotNumber=spotNumber;
		this.height=height;
		this.width=width;
	}
	
	/**
	 * getter for the X location of the Runoff image
	 * 
	 * @return int the x coordinate of the runoff image
	 */
	public int getXloc() {
		return Xloc;
	}
	
	/**
	 * getter for the Y location of the runoff image
	 * 
	 * @return int the y coordinate of the runoff image
	 */
	public int getYloc() {
		return Yloc;
	}
	
	/**
	 * getter for the height of the runoff image
	 * 
	 * @return int the height of the runoff image
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * getter for the width of the runoff image
	 * 
	 * @return the width of the runoff image
	 */
	public int getWidth() {
		return width;
	}
}
