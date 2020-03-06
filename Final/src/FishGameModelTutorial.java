


/**
 * FishGameModel tutorial contains some of the methods from the main fishgame model class for show of game tutorials
 * 
 * @author   Ryan Barber, Katarina Pfeifer and Humpher Owusu
 * 
 */

public class FishGameModelTutorial {
	
	public int boxHeight;
	public int boxWidth;
	public int xloc = 0;
	public int yloc = 0;
	public int xIncr = 10;
	int canvasWidth;
	int canvasHeight;
	int imgWidth;
	int imgHeight;
	Picture fish;
	Node mouseLoc;
	Picture chosenFish;
	
	public FishGameModelTutorial(int width, int height, int imageWidth, int imageHeight,Picture p, Node n) {
		
		this.canvasWidth = width;
		this.canvasHeight = height;
		this.imgWidth = imageWidth;
		this.imgHeight = imageHeight;
		fish = p;
		mouseLoc = n;
		
	}
	
	public void updateFish(){
			
			if(fish.getDirection() == Direction.EAST) {
				fish.setXloc(fish.getXloc() +  xIncr );
			}
			else { 
				fish.setXloc(fish.getXloc() -  xIncr );
			}
		
			if((fish.getXloc() <= 0 && fish.getDirection().equals(Direction.WEST)) ||
					(fish.getXloc() >= canvasWidth - 50 && 
					fish.getDirection().equals(Direction.EAST))) {
				
				if(fish.getDirection().equals( Direction.EAST)) {
					
					fish.setDirection(Direction.WEST);
					
				} else { fish.setDirection(Direction.EAST);}
				
			}
	}
	
	public void checkClicked() {
		chosenFish = null;
			if(mouseLoc.xloc >= fish.getXloc() && mouseLoc.xloc <= fish.getXloc()+fish.getWidth()
					&& mouseLoc.yloc >= fish.getYloc() && mouseLoc.yloc <= fish.getYloc()+fish.getHeight()) {
				chosenFish =  fish;
				fish.setTagged(true);
			} 
	}
	
	public boolean checkTagLoc(Node r) {
		if( r != null && (r.xloc <= (canvasWidth/2)+50 && r.xloc >= (canvasWidth/2)-50) &&
				(r.yloc) <= (canvasHeight/2)+50 && r.yloc >= (canvasHeight/2)-50) {
			
			return true;
		}
		else {return false;}
	}
	
	public boolean checkTagType(String n, String n2) {
		if(n != null && n2 != null) {
			if(n.equals(n2)) {
				return true;
			} else return false;
		}
		else return false;
	}

	public Picture getFish() {
		return fish;
	}

	public Picture getChosen() {
		return chosenFish;
	}

}

