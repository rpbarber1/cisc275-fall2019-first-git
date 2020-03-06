

import java.io.*;
import java.util.ArrayList;

import javafx.stage.Screen;

/**
 * This class acts as the Model for the oil clean up minigame. It handles the logic for the minigame.
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 */
public class OilGameModel extends Models implements Serializable{
    //x,y increment to move boat location
    int xIncr;
    int yIncr;
    //screen size
    static int canvasWidth = (int)Screen.getPrimary().getBounds().getWidth();
    static int canvasHeight = (int)Screen.getPrimary().getBounds().getHeight() - 50;
    //array list of boat locations 
    ArrayList<Node> boatLocation = new ArrayList<Node>();
    //array list of buoy locations (every third boat location)
    ArrayList<Picture> buoyLocation = new ArrayList<Picture>();
    //array of all oil spot images. Oil will be the same every time.
    // number of oil spots based on the method create oil spots
    static int numOilSpots = 52;
    Picture[] oilSpots = new Picture[numOilSpots];
    //boat picture object
    Picture boat;
    //skimmer picture object
    Picture skimmer;
    //previous x,y location of boat
    int previousXloc;
    int previousYloc;
    
    //counter for recordLocation function
    int count = 0;
    
    //boolean to determine if animals have been checked.
    boolean checked = false;
    
    
    /**
     * Constructor for the class creates the boat and skimmer objects, the oil spots, and 
     * sets the previousXloc previousYloc to be the current boat position so that they are not null to begin.
     */
	public OilGameModel() {
		createBoats();
		createOilSpots();
		previousXloc = boat.getXloc();
	    previousYloc = boat.getYloc();
	}
	
	/**
	 * This method increments the location of the boat or skimmer object depending on direction enum. 
	 * @param part determines if we are driving the boat or the skimmer. False = boat, True = skimmer.
	 * @param direction the direction used to set direction attribute of boat or skimmer. Gotten from View key handlers.
	 */
	public void updateLocationandDirection(boolean part, Direction direction) {
		
		//change the x,y increase based on direction
		if(part == false) { 
			// set x and y movement increment
			xIncr = 10;
			yIncr = 10;
			boat.setDirection(direction); 
		}
		else { 
			// set x and y movement increment
			xIncr = 6;
			yIncr = 6;
		    skimmer.setDirection(direction);
		}
		switch(direction) {
		case STOP:
			xIncr = 0;
			yIncr = 0;
			break;
		case NORTH:
			xIncr = 0;
			yIncr = -(yIncr);
			break;
		case SOUTH:
			xIncr = 0;
			break;
		case EAST:
			yIncr = 0;
			break;
		case WEST:
			yIncr = 0;
			xIncr = -(xIncr);
			break;
		case NORTHEAST:
			yIncr = -(yIncr);
		    break;
		case NORTHWEST:
			yIncr = -(yIncr);
			xIncr = -(xIncr);
			break;
		case SOUTHWEST:
			xIncr = -(xIncr);
			break;
		default:
			break;
		}
		
		if(part == false) { boatupdate(); }
		else {skimmerupdate(); }
	}
	
	/**
	 * This method records the location of the boat if it is not in the same location as the last record. 
	 * Every third location recorded is also recorded as a buoy location.
	 */
	public void recordLocation() {
		if(previousXloc != boat.getXloc() || previousYloc != boat.getYloc() 
				|| buoyLocation.size()==0) {
		boatLocation.add(new Node(boat.getXloc(),boat.getYloc(), boat.getDirection()));
		if (count%3 ==0){
			buoyLocation.add(new Picture(boat.getXloc(),boat.getYloc(), 20, 50, 220, 1090, boat.getDirection()));
		}
		count++;
		}
	}
	
	/**
	 * This method increments the location of the boat and set prevoiusXloc prevoiusYloc. It
	 * also calls recordLocation()
	 */
	public void boatupdate() {
		//if boat hits top or bottom border: stop moving in y direction
		if( (boat.getXloc() <= 0 && xIncr < 0) || (boat.getXloc() >= canvasWidth -100 && xIncr > 0)) {
			xIncr = 0;
		}
		if( (boat.getYloc() <= 0 && yIncr < 0) || (boat.getYloc() >= canvasHeight-100 && yIncr > 0)) {
			yIncr = 0;
		} 
		
		//set previous x,y location
		previousXloc = boat.getXloc();
	    previousYloc = boat.getYloc();
		//set new x,y location
		boat.setXloc(boat.getXloc() + xIncr);
		boat.setYloc(boat.getYloc() + yIncr);
		//record current location
		recordLocation();
	}
	
	/**
	 * Updates the location of the skimmer and checks if there is a collision between an oil spot and the skimmer.
	 */
	public void skimmerupdate() {
		skimmer.setXloc(skimmer.getXloc() + xIncr);
		skimmer.setYloc(skimmer.getYloc() + yIncr);
		
		for(int i = 0; i< oilSpots.length; i++) {
			if(oilSpots[i] != null && skimmer.getXloc() > oilSpots[i].getXloc() && skimmer.getXloc() < oilSpots[i].getXloc()+30
					&& skimmer.getYloc() > oilSpots[i].getYloc() && skimmer.getYloc() < oilSpots[i].getYloc()+30) {
				oilSpots[i] = null;
			}
		}
	}
	
	/**
	 * This method checks if the animals in the oil game are inside the oil booms or if the boat collided with one.
	 * @param b A picture object representing one of the animals to avoid. 
	 * @param m A picture object representing one of the animals to avoid.
	 * @param t A picture object representing one of the animals to avoid.
	 */
	public void animalCheck(Picture b, Picture m, Picture t) {
		//counter for ray tracing
		
		boolean out = false;
		Picture[] tmp = {b,m,t};
		for(Picture p : tmp) {
			int rightcount = 0;
			int leftcount = 0;
			for(Node n : boatLocation) {
				
				//if you drive the boat through an animal, you have trapped an animal in the buoys.
				if( (n.xloc > p.getXloc() && n.xloc < p.getXloc()+(p.getWidth())) && 
						(n.yloc > p.getYloc() && n.yloc < p.getYloc()+p.getHeight()) ) {
					out = true;
				}
				//if the location of the animal intersects with the buoy wall an odd number of times,
				// the animal is inside the buoys.
				if( n.xloc > p.getXloc() && p.getYloc() == n.yloc ){
					rightcount++;
				}
				if(n.xloc <  p.getXloc() && p.getYloc() == n.yloc) {
					leftcount++;
				}
				if( leftcount > 0 && rightcount%2 != 0) { out = true; }
			}
		}
		checked = out;
	}
	
	/**
	 * Check if all oil spots have been cleaned up. Once they have, return to the home screen.
	 * @return The HOME Minigame enum is returned which sets the enum in the Controller.
	 */
	public Minigame checkComplete() {
		Minigame m = Minigame.OILGAME;
		boolean b  = false;
		for(Picture p: oilSpots) {
			if(p != null) { b = true; }
		}
		if(b == false) { m = Minigame.HOME; }
		
		return m;
	}
	
	/**
	 * This method creates the picture object for the boat and the skimmer.
	 */
	private void createBoats() {
		boat = new Picture(1000, 470, 50, 100, 592, 1257, Direction.STOP);
		skimmer = new Picture(700, 400,50, 25, 462, 296, Direction.STOP);
	}

	/**
	 * This method creates oil spots Picture objects
	 * The method uses a 2 for loops to determine if a point is in the circle.
	 */
	public void createOilSpots() {
		double radius = 112.5;
		double dist;
		int i = 0;
		// for horizontal movement 
		for(int k = 690; k <= (2 * radius)+690; k += 25) { 
			// for vertical movement 
			for(int j = 270; j <= (2 * radius)+270; j += 25) { 
				dist = Math.sqrt((k-690 - radius) * (k-690 - radius) + (j-270 - radius) * (j-270 - radius)); 
		    
				if (dist <= radius-10 ) {
					oilSpots[i] = new Picture(k,j,50,50,512,512);
					i++;
				}  
			}
		}
	}
	
	
	//getters
	/**
	 * Getter method
	 * @return The fish ArrayList of Nodes which represent the buoy locations.
	 */
	public ArrayList<Picture> getBouyArrayList() { return buoyLocation; }
	/**
	 * Getter method
	 * @return The Picture object array that represents the oil spots.
	 */
	public Picture[] getOilSpots() { return oilSpots; }
	/**
	 * Getter method
	 * @return The boolean checked which indicates if an animal is trapped in the booms.
	 */
	public boolean getChecked() { return checked;}
	/**
	 * Getter method
	 * @return The Picture object which represents the boat.
	 */
	public Picture getBoat() { return boat; }
	/**
	 * Getter method
	 * @return The Picture object which represents the skimmer.
	 */
	public Picture getSkimmer() { return skimmer; }
	/**
	 * Getter method
	 * @return The canvas width.
	 */
	public int getWidth() { return canvasWidth; }
	/**
	 * Getter method
	 * @return The canvas height.
	 */
	public int getHeight() { return canvasHeight; }
	
	
}



