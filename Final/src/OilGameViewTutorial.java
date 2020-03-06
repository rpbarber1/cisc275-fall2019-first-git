

/*
 * Ryan Barber
 */

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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;


public class OilGameViewTutorial {
	
	// value of the height and width of screen
	int canvasWidth = 1280;
	int canvasHeight = 720;
	
	//see picture class
	//parameters are specific to how the image should look, and starting position
	Picture boat = new Picture(1000, 470, 50, 100, 592, 1257, Direction.STOP);
	Picture skimmer = new Picture(700, 400,50, 25, 462, 296, Direction.STOP);
	
	//constant animal images. They will have the same position every time.
	//these have no direction;
	Picture blackduck = new Picture(500, 300, 100,60, 249, 132);
	
	//boolean to determine if player is done the first part (encircling the oil)
	boolean donePart1 = false;
	
	// direction used for boat and skimmer
	Direction dir = Direction.STOP;
	
	//Nodes of Buoy images to draw
    ArrayList<Picture> buoys = new ArrayList<Picture>();
	
    Picture oilSpot = new Picture(690, 270, 50, 50, 512, 512);
	
	// map of png images
    HashMap<String, Image> pictures = new HashMap<String,Image>();
    
    //Game enum
    Minigame gameEnum = Minigame.OILTUT;
    
    //button for going home
    Button nextTut;
    
    //alert object makes the popup window.
    Alert alert = new Alert(AlertType.INFORMATION);
    
    //scene for oil view
    Scene oilScene;
    
    //graphic context for drawing images
    GraphicsContext gc;
    
    //flags for popups
    boolean boatInst = true;
    boolean skimmerInst = true;
    //prevent popup from showing before game screen.
    int count = 0;
	
	//View constructor initialize the starting position for the image
	//Called in controller
	public OilGameViewTutorial() {

        Group root = new Group();
        oilScene = new Scene(root);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        
        importImages();
        createAnimals();
        
        alert.setTitle("Inctructions");
		alert.setHeaderText(null);
		alert.setResizable(true);
		
        nextTut = new Button();
        nextTut.setLayoutX(0);
        nextTut.setLayoutY(0);
        nextTut.setText("next tutorial");
        
        

        nextTut.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	@Override
        	public void handle(MouseEvent e) {
        		if (e.getSource() == nextTut) {
        			
        			gameEnum = Minigame.BARRIERTUT;
        		}
        	}
        });
        
        root.getChildren().add(nextTut);
		
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
					
					if(donePart1 == false) { boat.setDirection(dir); }
					else { skimmer.setDirection(dir); }
					
					break;
				case DOWN:
					if(dir.equals(Direction.SOUTHEAST)|| dir.equals(Direction.SOUTHWEST)
							|| dir.equals(Direction.NORTH) ||dir.equals(Direction.STOP))dir = Direction.SOUTH;
					else if(dir.equals(Direction.WEST)|| dir.equals(Direction.NORTHWEST)) dir = Direction.SOUTHWEST;
					else if(dir.equals(Direction.NORTHEAST)|| dir.equals(Direction.EAST)) dir = Direction.SOUTHEAST;
					
					if(donePart1 == false) { boat.setDirection(dir); }
					else { skimmer.setDirection(dir); }
					
					break;
				case RIGHT:
					if(dir.equals(Direction.NORTHEAST)|| dir.equals(Direction.SOUTHEAST)
							|| dir.equals(Direction.WEST) ||dir.equals(Direction.STOP)) dir = Direction.EAST;
					else if(dir.equals(Direction.NORTH)|| dir.equals(Direction.NORTHWEST)) dir = Direction.NORTHEAST;
					else if(dir.equals(Direction.SOUTH)|| dir.equals(Direction.SOUTHWEST)) dir = Direction.SOUTHEAST;
					
					if(donePart1 == false) { boat.setDirection(dir); }
					else { skimmer.setDirection(dir); }
					
					break;
				case LEFT:
					if(dir.equals(Direction.NORTHWEST)|| dir.equals(Direction.SOUTHWEST)
							|| dir.equals(Direction.EAST) ||dir.equals(Direction.STOP)) dir = Direction.WEST;
					else if(dir.equals(Direction.NORTHEAST)|| dir.equals(Direction.NORTH)) dir = Direction.NORTHWEST;
					else if(dir.equals(Direction.SOUTHEAST)|| dir.equals(Direction.SOUTH)) dir = Direction.SOUTHWEST;
					
					if(donePart1 == false) { boat.setDirection(dir); }
					else { skimmer.setDirection(dir); }
					
					break;
				case SPACE: 
					if(!dir.equals(Direction.STOP)) dir = Direction.STOP;
					if(donePart1 == false) { 
						boat.setDirection(dir);
						donePart1 = true; 
					}
					else { skimmer.setDirection(dir); }
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
	
	private void createAnimals() {
		boat = new Picture(1000, 470, 50, 100, 592, 1257, Direction.STOP);
		skimmer = new Picture(700, 400,50, 25, 462, 296, Direction.STOP);
		
		//constant animal images. They will have the same position every time.
		//these have no direction;
		blackduck = new Picture(500, 300, 100,60, 249, 132);
		
	}
	
	//Method used to import the images into the image hash map
	private void importImages() {
     	//adding images to hashmap of images
        pictures.put("boat", createImage("boat"));
        pictures.put("skimmer", createImage("skimmer"));
        pictures.put("background", createImage("background"));
        pictures.put("boom", createImage("boom"));
        pictures.put("oil", createImage("oil"));
        pictures.put("blackduck", createImage("blackduck"));
	}
	
    //Read image from file and return
    private Image createImage(String image_file) {
        Image img = new Image("images/"+image_file+".png");
        return img;   	
    }

    
	//method used to repaint on the image and called in controller
	public void updatePart1() {
		
        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        // draw background
        gc.drawImage(pictures.get("background"), 0, 0, canvasWidth, canvasHeight);
        //draw reset button

        //draw animals
        gc.drawImage(pictures.get("blackduck"), blackduck.getXloc(), blackduck.getYloc(), blackduck.getWidth(), blackduck.getHeight());
        //draw oil spots
        gc.drawImage(pictures.get("oil"), oilSpot.getXloc(), oilSpot.getYloc(), oilSpot.getWidth(), oilSpot.getHeight());
       
        // draw the boat
        transformAndDraw(gc, pictures.get("boat"), boat);
        // draw the buoys
        for(Picture p : buoys) {
        	transformAndDraw(gc, pictures.get("boom"), p);
        }
        if(boatInst && count >2) {
        	boatInst = false;
        	
        	alert.getDialogPane().setPrefHeight(200.0);
        	alert.setContentText("Move boat with ctrl+arrow keys.\nEncircle oil and avoid hitting or trapping "
        			+ "animas in the booms.\nPress ctrl+space to stop driving boat and control oil skimmer.");
    		
        	alert.show();
        } else { count ++; }
	}
	
	public void updatePart2(Picture o) {

    	
    	
		oilSpot = o;
        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        // draw background
        gc.drawImage(pictures.get("background"), 0, 0, canvasWidth, canvasHeight);
        //draw animals
        gc.drawImage(pictures.get("blackduck"), blackduck.getXloc(), blackduck.getYloc(), blackduck.getWidth(), blackduck.getHeight());
        //draw oil spots
        if(o != null) {
            gc.drawImage(pictures.get("oil"), oilSpot.getXloc(), oilSpot.getYloc(), oilSpot.getWidth(), oilSpot.getHeight());
        }
        // draw the skimmer 
        transformAndDraw(gc, pictures.get("skimmer"), skimmer);
         //draw the buoys
        for(Picture p : buoys) {
        	transformAndDraw(gc, pictures.get("boom"), p);
        }
        if(skimmerInst) {
        	skimmerInst = false;
        	alert.getDialogPane().setPrefHeight(300.0);
        	alert.setContentText("Move skimmer with arrow ctrl+arrow keys.\nMove over oil spots to clean them "
        			+ "up.\nWhen all oil spots are gone the game is complete.\n Click button for next tutorial.");
        	
        	alert.show();
        	
        }
        
	}
	
	public void checkAnimals(boolean x) {
		if(x == true) {warnAnimalLoc();}
	}
	
	
	public void updateBouys(ArrayList<Node> nodes) {
		Node n = nodes.get(nodes.size()-1);
		buoys.add(new Picture(n.xloc, n.yloc, 20, 50, 220, 1090, n.direction));
	}
	
	//getter methods to get the frame dimensions
	public int getWidth() { return canvasWidth; }
	public int getHeight() { return canvasHeight; }

	
    // Then draw to gc
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

        
        // 
        // drawImage(sourceImage, sX, sY, sWidth, sHeight, dX, dY, dWidth, dHeight)
        //
        // This means that a rectangle of size (sWidth, sHeight) will be CROPPED from sourceImage
        // at location (sX, sY), RESIZED to (dWidth, dHeight), and drawn to the 
        // parent of the GraphicsContext at location (dX, dY).
        gc.drawImage(image, 0, 0, imgWidthOrig, imgHeightOrig,
                            x, y, imgWidth, imgHeight);
                        
        gc.restore(); // back to original state (before rotation)
    }
    
    public void warnAnimalLoc() {
		JOptionPane.showMessageDialog(null, "You have trapped animals inside the booms!");
		
	}
    
    //getter for boolean
    public boolean doneFirstPart() { return donePart1; }

	public Scene getScene() { return oilScene;}
	
	public Picture getOilSpot() { return oilSpot; }
	
	public Minigame getMinigame() {
		Minigame m = gameEnum;
		gameEnum = Minigame.OILTUT;
		return m;
	}


}
