import java.io.Serializable;

/**
 * This class contains information on the sites that will be occupied by plant
 * objects over the course of the barrier game. It also contains information for drawing 
 * the plants
 * 
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 *
 */
public class PlantSpot implements Serializable{
	int Xloc;
	int Yloc;
	boolean filled;
	String plantName;
	int spotNumber;
	int width;
	int height;
	
	public PlantSpot(int Xloc, int Yloc, int spotNumber, int width, int height) {
		this.Xloc=Xloc;
		this.Yloc=Yloc;
		this.spotNumber=spotNumber;
		filled=false;
		plantName="";
		this.width=width;
		this.height=height;
	}
	
	/**
	 * getter for the X location of the spot
	 * 
	 * @return int the x coordinate of the spot
	 */
	public int getXloc() {
		return Xloc; 
	}
	
	/**
	 * getter for the Y location of the spot
	 * 
	 * @return int the Y location of the spot
	 */
	public int getYloc() {
		return Yloc;
	}
	
	/**
	 * getter for the spot number
	 * 
	 * @return int that gives the spot number
	 */
	public int getSpotNumber() {
		return spotNumber;
	}

	/**
	 * sets whether the spot is filled or not. The spot is filled when a native plant is chosen for it
	 * 
	 * @param fill a boolean that sets the variable filled
	 */
	public void setFilled(boolean fill) {
		filled=fill;
	}
	
	/**
	 * getter for the boolean filled value
	 * 
	 * @return boolean that tells if the spot is filled or not
	 */
	public boolean getFilled() {
		return filled;
	}
	
	/**
	 * sets the plant name that is in the spot
	 * 
	 * @param name String that corresponds to the name of the plant filling the spot
	 */
	public void setPlantName(String name) {
		plantName=name;
	}
	
	/**
	 * getter for the plant name at the spot
	 * 
	 * @return String the plant name at the spot
	 */
	public String getPlantName() {
		return plantName;
	}
	
	/**
	 * getter for the width of the image for the spot
	 * 
	 * @return int corresponds to the width of the image
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * getter for the height of the image for the spot
	 * 
	 * @return int corresponds to the height of the image at the spot
	 */
	public int getHeight() {
		return height;
	}
}