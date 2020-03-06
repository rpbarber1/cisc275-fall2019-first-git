

import java.util.ArrayList;
/**
 * This class is the tutorial version of the OilGameModel class. It most of the same attributes and methods
 * with some changes.
 * @author ryan
 *
 */

public class OilGameModelTutorial {
    //x,y increment to move boat location
    private int xIncr = 0;
    private int yIncr = 0;
    //size of screen
    private static int canvasWidth;
    private static int canvasHeight;
    //array list of boat locations 
    ArrayList<Node> boatLocation = new ArrayList<Node>();
    //array list of buoy locations (every third boat location)
    ArrayList<Node> buoyLocation = new ArrayList<Node>();
    //oil spots array
    Picture oilSpots;
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
     * Constructor takes the canvas height and width and the picture of the boat
     * @param width width of screen (canvas)
     * @param height height of screen (canvas)
     * @param b picture object of boat
     * @param s picture object of skimmer
     * @param o picture object of the oil spot
     */
	public OilGameModelTutorial(int width, int height, Picture b, Picture s, Picture o) {
		canvasWidth = width;
		canvasHeight = height;
		boat = b;
		previousXloc = boat.getXloc();
	    previousYloc = boat.getYloc();
		skimmer = s;
		oilSpots = o;
	}

	public void updateLocationandDirection(boolean part) {
		Direction direction;
		//change the x,y increase based on direction
		if(part == false) { 
			// set x and y movement increment
			xIncr = 10;
			yIncr = 10;
			direction = boat.getDirection(); 
		}
		else { 
			// set x and y movement increment
			xIncr = 6;
			yIncr = 6;
			direction = skimmer.getDirection();
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
	
	// records boat location if boat is not in the same location as last record
	// every third record is a buoy location
	public void recordLocation() {
		if(previousXloc != boat.getXloc() || previousYloc != boat.getYloc() 
				|| buoyLocation.size()==0) {
		boatLocation.add(new Node(boat.getXloc(),boat.getYloc(), boat.getDirection()));
		if (count%3 ==0){
			buoyLocation.add(new Node(boat.getXloc(),boat.getYloc(), boat.getDirection()));
		}
		count++;
		}
	}
	
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
	
	public void skimmerupdate() {
		skimmer.setXloc(skimmer.getXloc() + xIncr);
		skimmer.setYloc(skimmer.getYloc() + yIncr);
		
			if(oilSpots != null && skimmer.getXloc() > oilSpots.getXloc() && skimmer.getXloc() < oilSpots.getXloc()+30
					&& skimmer.getYloc() > oilSpots.getYloc() && skimmer.getYloc() < oilSpots.getYloc()+30) {
				oilSpots = null;
			}
		
	}
	
	//checks if animals are inside the buoys.
	public void animalCheck(Picture b) {
		//counter for ray tracing
		
		boolean out = false;
		Picture tmp = b;
		
			int rightcount = 0;
			int leftcount = 0;
			for(Node n : boatLocation) {
				
				//if you drive the boat through an animal, you have trapped an animal in the buoys.
				if( (n.xloc > tmp.getXloc() && n.xloc < tmp.getXloc()+(tmp.getWidth())) && 
						(n.yloc > tmp.getYloc() && n.yloc < tmp.getYloc()+tmp.getHeight()) ) {
					out = true;
				}
				//if the location of the animal intersects with the buoy wall an odd number of times,
				// the animal is inside the buoys.
//				Node tmpnode = new Node(p.getXloc(), p.getYloc(), null);
				if( n.xloc > tmp.getXloc() && tmp.getYloc() == n.yloc ){
					rightcount++;
				}
				if(n.xloc <  tmp.getXloc() && tmp.getYloc() == n.yloc) {
					leftcount++;
				}
				if( leftcount > 0 && rightcount%2 != 0) {out = true;}
			}
		
		checked = out;
	}
	
	public Minigame checkComplete() {
		Minigame m = Minigame.OILTUT;
		boolean b  = false;
		
		if(oilSpots != null) { b = true; }
		
		if(b == false) {
			m = Minigame.BARRIERTUT;
		}
		return m;
	}
	
	//getter for bouyLocation array
	public ArrayList<Node> getBouyArrayList() { return buoyLocation; }
	public Picture getOilSpots() { return oilSpots; }
	public boolean getChecked() { return checked;}
	
}



