

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class acts as the View for the oil clean up minigame. This class handles things drawn to the screen
 * and JavaFX objects Button, Alert, and event handlers, key handlers.
 * @author Ryan Barber, Katarina Pfieifer, Humpher Owusu
 */
public class OilGameView {

	// value of the height and width of screen
	static int canvasWidth = (int)Screen.getPrimary().getBounds().getWidth();
    static int canvasHeight = (int)Screen.getPrimary().getBounds().getHeight() - 50;
	//see picture class
	//parameters are specific to how the image should look, and starting position
	Picture boat;
	Picture skimmer;
	
	//constant animal images. They will have the same position every time.
	Picture blackduck;
	Picture mallard ;
	Picture woodduck;
	
	//boolean to determine if player is done the first part (encircling the oil)
	boolean donePart1 = false;
	
	// direction used for boat and skimmer
	Direction dir = Direction.STOP;
	
	//Nodes of Buoy images to draw
    ArrayList<Picture> buoys = new ArrayList<Picture>();
	
    //array of all oil spot images. Oil will be the same every time.
    Picture[] oilSpots;
	
	// map of png images
    HashMap<String, Image> pictures = new HashMap<String,Image>();
    
    //Game enum
    Minigame gameEnum = Minigame.OILGAME;
    
    //button for going home
    Button reset;
    
    //alert object makes popups
    Alert alert = new Alert(AlertType.INFORMATION);
    
    //scene for oil view
    Scene oilScene;
    
    //graphic context for drawing images
    GraphicsContext gc;
	
    /**
     * Constructor takes the boat, skimmer, and oil spot Picture objects from the Model and initializes them
     * in the View. Constructor creates a JavaFX Group, Scene, GraphicsContext, Buttons, Alert, imports the 
     * images into a HashMap, and creates the duck Picture objects.
     */
	public OilGameView() {

        Group root = new Group();
        oilScene = new Scene(root);
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        
        importImages();
		createAnimals();
		
		
		
		alert.setTitle("WARNING");
		alert.setHeaderText("You have made a mistake");
		alert.setResizable(true);
	    alert.getDialogPane().setPrefHeight(200.0);
		
        reset= new Button();
        reset.setLayoutX(0);
        reset.setLayoutY(0);
        reset.setText("home");
        
        

		reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	@Override
        	public void handle(MouseEvent e) {
        		if (e.getSource() == reset) {
        			
        			gameEnum = Minigame.HOME;
        		}
        	}
        });
        
        root.getChildren().add(reset);
		
		// Check key pressed (arrow keys or spacebar).
		// Depending on key pressed, change direction of boat.
		oilScene.setOnKeyPressed( new EventHandler<KeyEvent>() {
			@Override 
			public void handle(KeyEvent e) {
				switch(e.getCode()) {
				case UP: 
					if(dir.equals(Direction.NORTHEAST)||dir.equals(Direction.NORTHWEST)
							||dir.equals(Direction.SOUTH)|| dir.equals(Direction.STOP)) dir = Direction.NORTH;
					else if(dir.equals(Direction.EAST)|| dir.equals(Direction.SOUTHEAST)) dir = Direction.NORTHEAST;
					else if(dir.equals(Direction.WEST)|| dir.equals(Direction.SOUTHWEST)) dir = Direction.NORTHWEST;
					
					break;
				case DOWN:
					if(dir.equals(Direction.SOUTHEAST)|| dir.equals(Direction.SOUTHWEST)
							|| dir.equals(Direction.NORTH) ||dir.equals(Direction.STOP))dir = Direction.SOUTH;
					else if(dir.equals(Direction.WEST)|| dir.equals(Direction.NORTHWEST)) dir = Direction.SOUTHWEST;
					else if(dir.equals(Direction.NORTHEAST)|| dir.equals(Direction.EAST)) dir = Direction.SOUTHEAST;

					
					break;
				case RIGHT:
					if(dir.equals(Direction.NORTHEAST)|| dir.equals(Direction.SOUTHEAST)
							|| dir.equals(Direction.WEST) ||dir.equals(Direction.STOP)) dir = Direction.EAST;
					else if(dir.equals(Direction.NORTH)|| dir.equals(Direction.NORTHWEST)) dir = Direction.NORTHEAST;
					else if(dir.equals(Direction.SOUTH)|| dir.equals(Direction.SOUTHWEST)) dir = Direction.SOUTHEAST;
					
					break;
				case LEFT:
					if(dir.equals(Direction.NORTHWEST)|| dir.equals(Direction.SOUTHWEST)
							|| dir.equals(Direction.EAST) ||dir.equals(Direction.STOP)) dir = Direction.WEST;
					else if(dir.equals(Direction.NORTHEAST)|| dir.equals(Direction.NORTH)) dir = Direction.NORTHWEST;
					else if(dir.equals(Direction.SOUTHEAST)|| dir.equals(Direction.SOUTH)) dir = Direction.SOUTHWEST;
				
					break;
				case SPACE: 
					if(!dir.equals(Direction.STOP)) dir = Direction.STOP;
					if(donePart1 == false) { 
						donePart1 = true; 
					}
					break;
				case W:
					donePart1 = false;
					break;
				
				default:
					break;
				}
			}
		});
		
	
		
	}
	/**
	 * This method creates the duck picture objects. This is done in the view instead of the Model
	 * because the state of the ducks never changes and it never moves. 
	 */
	private void createAnimals() {
		//constant animal images. They will have the same position every time.
		//these have no direction;
		blackduck = new Picture(500, 300, 100,60, 249, 132);
		mallard = new Picture(1000,250,100,60,256,166);
		woodduck = new Picture(650,550,100,55,253,95);
		
	}

	/**
	 * This method puts all of the images used into a HashMap.
	 */
	private void importImages() {
     	//adding images to hashmap of images
        pictures.put("boat", createImage("boat"));
        pictures.put("skimmer", createImage("skimmer"));
        pictures.put("background", createImage("background"));
        pictures.put("boom", createImage("boom"));
        pictures.put("oil", createImage("oil"));
        pictures.put("bass", createImage("bass"));
        pictures.put("trout", createImage("trout"));
        pictures.put("mallard", createImage("mallard"));
        pictures.put("blackduck", createImage("blackduck"));
        pictures.put("woodduck", createImage("woodduck"));
	}
	
    /**
     * This method reads the images from file.
     * @param image_file the name of the png file to be read minus the extension ".png" and file path 
     * \"images/\". 
     * @return The JavaFX Image object. 
     */
    private Image createImage(String image_file) {
        Image img = new Image("images/"+image_file+".png");
        return img;   	
    }

    /**
     * This method draws the background, ducks, oil spots, boat, and buoys. 
     * @param o Picture array of oil spots
     * @param b Picture array list of buoys
     * @param boat Picture object for boat
     */
	public void updatePart1(Picture[] o, ArrayList<Picture> b, Picture boat) {
		
        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        // draw background
        gc.drawImage(pictures.get("background"), 0, 0, canvasWidth, canvasHeight);
        //draw reset button

        //draw animals
        gc.drawImage(pictures.get("blackduck"), blackduck.getXloc(), blackduck.getYloc(), blackduck.getWidth(), blackduck.getHeight());
        gc.drawImage(pictures.get("mallard"), mallard.getXloc(), mallard.getYloc(), mallard.getWidth(), mallard.getHeight());
        gc.drawImage(pictures.get("woodduck"), woodduck.getXloc(), woodduck.getYloc(), woodduck.getWidth(), woodduck.getHeight());
        //draw oil spots
        for(Picture p : o) {
        	if(p != null) {
        		gc.drawImage(pictures.get("oil"), p.getXloc(), p.getYloc(), p.getWidth(), p.getHeight());
        	}
        }
        // draw the boat
        transformAndDraw(gc, pictures.get("boat"), boat);
        // draw the buoys
        for(Picture p : b) {
        	transformAndDraw(gc, pictures.get("boom"), p);
        }
	}
	
	/**
	 * This method draws the background, ducks, oil spots, skimmer, and buoys. 
	 * @param o Picture array of oil spots
     * @param b Picture array list of buoys
     * @param skimmer Picture object for skimmer
	 */
	public void updatePart2(Picture[] o, ArrayList<Picture> b, Picture skimmer) {
		
        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        // draw background
        gc.drawImage(pictures.get("background"), 0, 0, canvasWidth, canvasHeight);
        //draw animals
        gc.drawImage(pictures.get("blackduck"), blackduck.getXloc(), blackduck.getYloc(), blackduck.getWidth(), blackduck.getHeight());
        gc.drawImage(pictures.get("mallard"), mallard.getXloc(), mallard.getYloc(), mallard.getWidth(), mallard.getHeight());
        gc.drawImage(pictures.get("woodduck"), woodduck.getXloc(), woodduck.getYloc(), woodduck.getWidth(), woodduck.getHeight());
        //draw oil spots
        for(Picture p : o) {
        	if(p != null) {
        	 gc.drawImage(pictures.get("oil"), p.getXloc(), p.getYloc(), p.getWidth(), p.getHeight());
        	}
        }
        // draw the skimmer 
        transformAndDraw(gc, pictures.get("skimmer"), skimmer);
         //draw the buoys
        for(Picture p : b) {
        	transformAndDraw(gc, pictures.get("boom"), p);
        }
	}
	
	/**
	 * This method calls the warnAnimalLoc() method of the input parameter is true. 
	 * @param x The boolean which if true, means an animal is trapped in the booms.
	 */
	public void checkAnimals(boolean x) {
		if(x == true) {warnAnimalLoc();}
	}
	

    /**
     * This method uses the Direction enum to determine at which angle an image should be rotated.
     * @param gc The GraphicsContext
     * @param image The image to be rotated
     * @param p The Picture object representing the thing being drawn.
     */
    private void transformAndDraw(GraphicsContext gc, Image image, Picture p) {
    	int imgHeight = p.getHeight();
    	int imgWidth = p.getWidth();
    	int x = p.getXloc();
    	int y = p.getYloc();
    	int imgWidthOrig = p.getOrigWidth();
    	int imgHeightOrig = p.getOrigHeight();
    	Direction d = p.getDirection();
    	
        // clockwise rotation angle
        // Why clockwise? I don't know. It should probably be counter-clockwise
        double theta = 0.0;
        // Switch statemement is good to use for enums
        switch (d) {
        case NORTH: {
            theta = 0.0;
            break;
        } case NORTHEAST: {
            theta = 30.0;
            break;
        } case EAST: {
            theta = 90.0;
            break;
        } case SOUTHEAST: {
            theta = 150.0;
            break;
        } case SOUTH: {
        	theta = 180.0;
            break;
        } case SOUTHWEST: {
            theta = -150.0;
            break;
        } case WEST: {
        	theta = -90.0;
            break;
        } case NORTHWEST: {
            theta = -30.0;
            break;
        }
		default:
			break;
        }

        // Rotate the CANVAS and NOT the Image, because rotating the image
        // will crop part of the bass's tail for certain angles
        gc.save(); // saves the current state on stack, including the current transform
        // set canvas rotation about the point (x+imageWidth/2, y+imageWidth/2) by angle theta
        Rotate r = new Rotate(theta, x+imgWidth/2, y+imgWidth/2);
        // Transform gc by the rotation
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

        // drawImage(sourceImage, sX, sY, sWidth, sHeight, dX, dY, dWidth, dHeight)
        // This means that a rectangle of size (sWidth, sHeight) will be CROPPED from sourceImage
        // at location (sX, sY), RESIZED to (dWidth, dHeight), and drawn to the 
        // parent of the GraphicsContext at location (dX, dY).
        gc.drawImage(image, 0, 0, imgWidthOrig, imgHeightOrig,
                            x, y, imgWidth, imgHeight);
                        
        gc.restore(); // back to original state (before rotation)
    }
    
    /**
     * This method sets the text in the Alert object, called \"alert\" in this class, 
     * to \"You have trapped animals inside the booms!\". It then calls the Alert.show() method.
     */
    public void warnAnimalLoc() {
		alert.setContentText("You have trapped animals inside the booms!");
		alert.show();
	}
    
    /**
     * Getter method
     * @return The boolean donePart1 which determines if the boat is being driven or the skimmer.
     */
    public boolean doneFirstPart() { return donePart1; }
    /**
     * Getter method
     * @return The Scene object \"oilScene\".
     */
	public Scene getScene() { return oilScene;}
	/**
	 * Getter method
	 * @return The Minigame enum \"gameEnum\"
	 */
	public Minigame getMinigame() {
		Minigame m = gameEnum;
		gameEnum = Minigame.OILGAME;
		return m;
	}
	
	/**
	 * Getter method
	 * @return the Direction enum based on key presses
	 */
	public Direction getDirection() { return dir; }

}
