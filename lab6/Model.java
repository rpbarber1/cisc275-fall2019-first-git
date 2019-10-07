package lab6;



/*
 * Ryan Barber
 * Lab4 
 * cisc275
 */



public class Model {
	int xloc = 0;
    int yloc = 0;
    double xIncr = 0;
    double yIncr = 0;
    
    static int canvasWidth;
    static int canvasHeight;
    static int imgWidth;
    static int imgHeight;
    
    BassMode bm;
    
    Direction direction = Direction.EAST;

	public Model(int width, int height, int imageWidth, int imageHeight) {
		canvasWidth = width;
		canvasHeight = height;
		imgWidth = imageWidth;
		imgHeight = imageHeight;
		
	}

	public void updateLocationandDirection() {
		//check mood
		if(bm == BassMode.ATTAC) {
			xIncr = 32;
			yIncr = 20;
		}
		else if(bm == BassMode.CONFUSE) {
			xIncr = 8;
			yIncr = 5;
			
		}
		else if(bm == BassMode.DEFAULT) {
			xIncr = 16;
			yIncr = 10;
		}
		//get direction xIncr, yIncr correct
		switch(direction) {
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
		}
		xloc += xIncr;
		yloc += yIncr;
		
		if(xloc >= canvasWidth-200) {
    		xIncr = -(xIncr);
    	}
    	if(xloc <=0) {
    		xIncr = -(xIncr);
    	}
    	if(yloc >= canvasHeight-500) {
    		yIncr = -(yIncr);
    	} 
    	if(yloc <=0) {
    		yIncr = -(yIncr);
    	}
		
		getDirection();
		
	}
	
	public void updateBassModeAndDirection(BassMode b, Direction d) {
		bm = b;
		direction = d;
	}
	
	public Direction getDirection() {
		
		return direction;
	}

	
	

	

	public int getX() {
		return xloc;
	}
	
	public int getY() {
		return yloc;
	}
	
	public int getMood() {
		if (bm == BassMode.CONFUSE)return 1;
		if (bm == BassMode.DEFAULT)return 0;
		else return 2;
	}

}

