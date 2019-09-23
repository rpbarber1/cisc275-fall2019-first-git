package lab4;

import java.util.HashMap;

/*
 * Ryan Barber
 * Lab4 
 * cisc275
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class View {
	final int canvasCount = 10;
    int picInd = 0;
	final static int canvasWidth = 500;
    final static int canvasHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    
    HashMap<String,Image> imageMap = new HashMap<String, Image>();
    Image orc_img;
    
    
    GraphicsContext gc;

	public View(Stage theStage) {
		theStage.setTitle("Orc");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        
        readImages(imageMap);
        orc_img = getImage("southeast");
		
	}

	public int getImageWidth() {
		return imgWidth;
	}

	public int getImageHeight() {
		return imgHeight;
	}

	public int getHeight() {
		return canvasHeight;
	}

	public int getWidth() {
		return canvasWidth;
	}

	public void update(double x, double y, String dir) {
		orc_img = getImage(dir);
		
		// Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // draw the subimage from the original png to animate the orc's motion
        gc.drawImage(orc_img, imgWidth*picInd, 0, imgWidth, imgHeight,
                            x, y, imgWidth, imgHeight);
        picInd = (picInd + 1) % canvasCount;
		
	}
	
	//get correct image from map of images.
    private Image createImage(String pngName) {
    	
    	Image img = new Image("images/orc/" + pngName);
        return img;
           	
    	// TODO: Change this method so you can load other orc animation bitmaps
        
        //Created String parameter that string which is a key for image map.
    }
	
    // Read in all images to map.
    public void readImages(HashMap<String,Image> i) {
    	imageMap.put("southwest", createImage("orc_forward_southwest.png"));
    	imageMap.put("northwest", createImage("orc_forward_northwest.png"));
    	imageMap.put("northeast", createImage("orc_forward_northeast.png"));
    	imageMap.put("southeast", createImage("orc_forward_southeast.png"));
    	
    	
    }
    
    public Image getImage(String s) {
    	return imageMap.get(s);
    }

}

