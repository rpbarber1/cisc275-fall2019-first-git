/**
 * This package contains the necessary classes to get the standard screen size (mainly the height and width)
 * of the computer screen the game is currently being played on.
 */


import java.io.Serializable;

import javafx.stage.Screen;


/**
 * FishGameModel class is a part of the MVC structure of the whole Fish Game. This model contains various methods 
 * that control movements and other game play actions showed in the view. The needed information are recorded here 
 * this Model Class and are pass onto the controller which in turn passes the necessary information to the View class
 * 
 * @author   Ryan Barber, Katarina Pfeifer and Humpher Owusu
 * 
 */

public class FishGameModel extends Models implements Serializable{
	
	
	/**
	   * The x coordinate's increment variable
	   */
	 
	public int xIncr = 10;
	
	/**
	   *The  standard canvas width and canvas height of the computer the game is currenly being played on
	   */
    private static int canvasWidth = (int)Screen.getPrimary().getBounds().getWidth();
    private static int canvasHeight = (int)Screen.getPrimary().getBounds().getHeight() - 50;
	
    /**
	   * The array of fish pictures from the Picture class.
	   */
	Picture[] fishPics = new Picture[6];
	
	/**
	   * The Node of the mouse location for drag and tag.
	   */
	Node mouseLoc;
	
	/**
	   * The chosen fish variable from clicking action during the fishing game
	   */
	Picture chosenFish;
	
	
	/**
	   * Constructor calls the createFish method which creates an array of fishes.
	   */
	public FishGameModel() {
		createFish();
	}
	
	
	/**
	   * Updates the position and direction of a fish based on the initial direction.
	   */
	public void updateFish(){
		
		for (int i = 0; i <fishPics.length; i++) {
			
			if(fishPics[i].getDirection() == Direction.EAST) {
				fishPics[i].setXloc(fishPics[i].getXloc() +  xIncr );
			}
			else { 
				fishPics[i].setXloc(fishPics[i].getXloc() -  xIncr );
			}
		
			if((fishPics[i].getXloc() <= 0 && fishPics[i].getDirection().equals(Direction.WEST)) ||
					(fishPics[i].getXloc() >= canvasWidth - 50 && 
					fishPics[i].getDirection().equals(Direction.EAST))) {
				
				if(fishPics[i].getDirection().equals( Direction.EAST)) {
					
					fishPics[i].setDirection(Direction.WEST);
					
				} else { fishPics[i].setDirection(Direction.EAST);}
				
			}
			
		}
		
	}
	
	/**
	   * Checks if the mouse has been clicked and gets the information of the coordinates. Set tagged
	   * is set to true if the right coordinates are recorded.
	   *
	   * @param mouse   This is the mouse node
	   *                Should the x location,
	   *                y location and direction
	   *           
	   */
	public void checkClicked(Node mouse) {
		mouseLoc = mouse;
		chosenFish = null;
		for(Picture p : fishPics) {
			if(mouseLoc != null && mouseLoc.xloc >= p.getXloc() && mouseLoc.xloc <= p.getXloc()+p.getWidth()
					&& mouseLoc.yloc >= p.getYloc() && mouseLoc.yloc <= p.getYloc()+p.getHeight()) {
				chosenFish =  p;
				p.setTagged(true);
			} 
			
		}
	}
	
	/**
	   * Checks the  location of the fish tag.
	   * 
	   * @param r 	r is the node tag location
	   * 
	   * @return boolean returns true if r is not null and there is a recorded x location
	   * 				 and y location. Returns false if otherwise
	   *                
	   */
	public boolean checkTagLoc(Node r) {
		if( r != null && (r.xloc <= (canvasWidth/2)+50 && r.xloc >= (canvasWidth/2)-100) &&
				(r.yloc) <= (canvasHeight/2)+20 && r.yloc >= (canvasHeight/2)-20) {
			
			return true;
		}
		else {return false;}
	}
	
	
	/**
	   * Checks the name and the tag type based on the strings passed in 
	   * 
	   * @param n tagName
	   * @param n2 fishToTag
	   * 
	   * @return boolean returns true if the name matches the tag, returns
	   * 		 false if otherwise.
	   * 
	   */
	public boolean checkTagType(String n, String n2) {
		if(n != null && n2 != null) {
			if(n.equals(n2)) {
				return true;
			} else return false;
		}
		else return false;
	}
	
	
	/**
	   * Creates an array of fishes with sizes, initial coordinates and direction
	   */
	public void createFish() {
    	fishPics[0] = new Picture(100,100,150,75, "blueFish", Direction.EAST,false) ;
    	fishPics[1] = new Picture(150,170,100,60, "bassFish", Direction.EAST,false) ;
    	fishPics[2] = new Picture(200,250,100,60, "drumFish", Direction.EAST,false) ;
    	fishPics[3] = new Picture(300,320,100,60, "troutFish", Direction.EAST,false) ;
    	fishPics[4] = new Picture(600,380,100,60, "pickerelFish", Direction.EAST,false) ;
    	fishPics[5] = new Picture(500,450,100,60, "weakFish", Direction.EAST,false) ;
    }	
	
	/**
	   * Gets the array of fishes
	   * @return fishPics array of fishes
	   */
	public Picture[] getFish() { return fishPics; }
	
	/**
	   * Gets the chosen fish
	   * @return chosenFish 
	   */
	public Picture getChosen() { return chosenFish; }
	
	/**
	   * Gets the canvas height
	   * @return canvasHeight
	   */
	public int getHeight() { return canvasHeight; }
	
	/**
	   * Gets the canvas width;
	   * @return canvasWidth
	   */
	public int getWidth() { return canvasWidth; }

}

