package lab4;

/*
 * Ryan Barber
 * Lab4 
 * cisc275
 */



public class Model {
	double xloc = 0;
    double yloc = 0;
    
    double xIncr = 8;
    double yIncr = 2;
    
    static int canvasWidth;
    static int canvasHeight;
    static int imgWidth;
    static int imgHeight;

	public Model(int width, int height, int imageWidth, int imageHeight) {
		canvasWidth = width;
		canvasHeight = height;
		imgWidth = imageWidth;
		imgHeight = imageHeight;
	}

	public void updateLocationandDirection() {
		xloc += xIncr;
		yloc += yIncr;
		
		if(xloc >= canvasWidth-140) {
    		xIncr = -(xIncr);
    	}
    	if(xloc <=0) {
    		xIncr = -(xIncr);
    	}
    	if(yloc >= canvasHeight-130) {
    		yIncr = -(yIncr);
    	} 
    	if(yloc <=0) {
    		yIncr = -(yIncr);
    	}
		
		getDirection();
		
    	
	}
	
	public String getDirection() {
		
		if(xIncr < 0 && yIncr < 0) return "northwest";
    	else if(xIncr > 0 && yIncr > 0 ) return  "southeast";
    	else if(xIncr < 0 && yIncr > 0) return "southwest";
    	else return "northeast";
		
	}
	

	public double getX() {
		return xloc;
	}
	
	public double getY() {
		return yloc;
	}

	


}
