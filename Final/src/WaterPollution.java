import java.io.Serializable;

/**
 * 
 * This class contains information for drawing the polluted water in the river
 * seen in the barrier game. The spots are considered empty when a plant blocks
 * the flow of runoff to them
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 *
 */
public class WaterPollution implements Serializable{
	int Xloc;
	int Yloc;
	boolean exists;
	int spotNumber;
	int height;
	int width;
	
	public WaterPollution(int Xloc, int Yloc, int spotNumber, int height, int width) {
		this.Xloc=Xloc;
		this.Yloc=Yloc;
		this.spotNumber=spotNumber;
		exists=true;
		this.height=height;
		this.width=width;
	}
	
	/**
	 * getter for the X location of the water pollution image
	 * 
	 * @return int the x coordinate of the water pollution image
	 */
	public int getXloc() {
		return Xloc;
	}
	
	/**
	 * getter for the Y location of the water pollution image
	 * 
	 * @return int the y coordinate of the water pollution image
	 */
	public int getYloc() {
		return Yloc;
	}
	
	/**
	 * sets whether the water pollution exists
	 * 
	 * @param present a boolean that corresponds to if the water pollution is present or not
	 */
	public void setExists(boolean present) {
		exists=present;
	}
	
	/**
	 * getter for if the water pollution exists below each plant spot
	 * 
	 * @return a boolean as to whether the pollution exists
	 */
	public boolean getExists() {
		return exists;
	}
	
	/**
	 * getter for the spot number of the water pollution
	 * 
	 * @return int the spot number
	 */
	public int getSpotNumber() {
		return spotNumber;
	}
	
	/**
	 * getter for the height of the image
	 * 
	 * @return int the height of the image
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * getter for the width of the pollution image
	 * 
	 * @return int the width of the image
	 */
	public int getWidth() {
		return width;
	}
}