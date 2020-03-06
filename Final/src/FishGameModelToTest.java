
public class FishGameModelToTest {
	
public int xIncr = 10;
	

	public int canvasWidth = 720;
	public int canvasHeight = 1248;
	
	Picture[] fishPics = new Picture[6];
	Node mouseLoc;
	Picture chosenFish;
	
	public FishGameModelToTest() {
		createFish();
	}
	
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
		public boolean checkTagLoc(Node r) {
		if( r != null && (r.xloc <= (canvasWidth/2)+50 && r.xloc >= (canvasWidth/2)-100) &&
				(r.yloc) <= (canvasHeight/2)+20 && r.yloc >= (canvasHeight/2)-20) {
			
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

	public void createFish() {
    	fishPics[0] = new Picture(100,100,150,75, "blueFish", Direction.EAST,false) ;
    	fishPics[1] = new Picture(150,170,100,60, "bassFish", Direction.EAST,false) ;
    	fishPics[2] = new Picture(200,250,100,60, "drumFish", Direction.EAST,false) ;
    	fishPics[3] = new Picture(300,320,100,60, "troutFish", Direction.EAST,false) ;
    	fishPics[4] = new Picture(600,380,100,60, "pickerelFish", Direction.EAST,false) ;
    	fishPics[5] = new Picture(500,450,100,60, "weakFish", Direction.EAST,false) ;
    }	
	
	//getters
	public Picture[] getFish() { return fishPics; }
	public Picture getChosen() { return chosenFish; }
	public int getHeight() { return canvasHeight; }
	public int getWidth() { return canvasWidth; }

	

}
