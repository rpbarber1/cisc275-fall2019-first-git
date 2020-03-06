
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;



//import java.awt.Component;
//import java.awt.event.MouseAdapter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.ArrayList;
import java.util.HashMap;




/**
 * FishGameView class is also part of the MVC structure of the whole Fish Game. This view contains various methods 
 * that shows the actual game play on the screen using the information gained from the controller class which also gets
 * the needed data from the model class.
 * 
 * 
 * @author   Ryan Barber, Katarina Pfeifer and Humpher Owusu
 * 
 */
public class FishGameView{

	/**
	   * The hashmap of a string as key and value as images
	   */
	HashMap<String, Image> pics = new HashMap<String, Image>();
	
	/**
	   * The reset button to reset the game
	   */
	Button reset;
	
	/**
	   * The canvas width 
	   */
	int canvasWidth;
	
	/**
	   * The canvas height
	   */
	int canvasHeight;

	/**
	   * The graphics context instance
	   */
    GraphicsContext gc;
  
    /**
     * The minigame enum variable initialized to fish game
     */
    Minigame gameEnum = Minigame.FISHGAME;
	
    /**
     * The boolean clicked set to false
     */
    boolean clicked = false;
    
    /**
     * The boolean fished set to false;
     */
    boolean fished = false;

    //Node for mouse location
    
    /**
     * The node for mouse location
     * @param 0, 0, null for initial location
     */
    Node mouseLoc = new Node(0,0,null);
    
    /**
     * The node for tag location
     * @param 0, 0, null for initial location
     */
    Node tagLoc = new Node(0,0, null);
    
    /**
     * The name of the fish tag
     */
    String tagName;
   
	
    
    /**
     * String for fish to tag name
     */
   	Picture fishToTag = null;
	
    /**
     * The rectangle instances
     */
	Rectangle rect1, rect2, rect3, rect4, rect5, rect6;
	
	
	 /**
	   * The text instances
	   */
	Text t1,t2,t3,t4,t5,t6;
	
	 /**
	   * The current rectangle tag
	   */
	Rectangle currentTag;
	
	
	
	 /**
	   * variables to drag  rectangle object
	   */
	double orgX, orgY;
	double transX, transY;
	
	
	/**
	   * Scene instance for fish scene
	   */
	Scene fishScene;

	
	
	/**
	   * View constructor initialize the starting position for the image
	   * Called in controller
	   * @param w  canvas width
	   * @param h canvas height 
	   *                
	   */
	public FishGameView(int w, int h) {
        
        Group root = new Group();
        fishScene = new Scene(root);
        
        canvasWidth = w;
        canvasHeight = h;
        
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().addAll(canvas);
       
        gc = canvas.getGraphicsContext2D();
        
        
        importImages();
       
        reset = new Button();
        reset.setLayoutX(0);
        reset.setLayoutY(0);
        reset.setText("home");
       
       

        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e1) {
        		gameEnum = Minigame.HOME;
    	    }
        };
      
        //Button to GuideBook
        Button btn = new Button("GuideBook");
        btn.setLayoutX(canvasWidth-100);
        btn.setLayoutY(0);
       
        Button fishingButton = new Button("Fishing");
        fishingButton.setLayoutX(700);
        fishingButton.setLayoutY(600);
       
        rect1 = new Rectangle(100, 10, 100, 45);
        rect1.setFill(Color.GREEN);
       
        t1 = new Text(rect1.getX(), rect1.getY()+20, "BASS");
      
        rect2 = new Rectangle(300, 10, 100, 45);
        rect2.setFill(Color.BLUE);
      
        t2 = new Text(rect2.getX(), rect2.getY()+20, "TROUT");
      
       
        rect3 = new Rectangle(500, 10, 100, 45);
        rect3.setFill(Color.RED);
        t3 = new Text(rect3.getX(), rect3.getY()+20, "PICKEREL");
       
        rect4 = new Rectangle(100, canvasHeight-110, 100, 45);
        rect4.setFill(Color.ORANGE);
       
        t4 = new Text(rect4.getX(), rect4.getY()+20, "DRUMFISH");
       
        rect5 = new Rectangle(300, canvasHeight-110, 100, 45);
        rect5.setFill(Color.YELLOW);
       
        t5 = new Text(rect5.getX(), rect5.getY()+20, "WEAKFISH");
       
        rect6 = new Rectangle(500, canvasHeight-110, 100, 45);
        rect6.setFill(Color.AQUAMARINE);
       
        t6 = new Text(rect6.getX(), rect6.getY()+20, "BLUEFISH");
       
       
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		clicked = !clicked;
    		    if(clicked) {
    		    	//btn.setGraphic(new ImageView(img1));
    		    	root.getChildren().removeAll(rect1,rect2,rect3,rect4,rect5,rect6, fishingButton,
    		    			t1,t2,t3,t4,t5,t6);
    			    btn.setText("back");
    		    }
    		    else {
    		    	btn.setText("GuideBook");
    			    root.getChildren().addAll(rect1,rect2,rect3,rect4,rect5,rect6,fishingButton,
    					    t1,t2,t3,t4,t5,t6);
    		    }
    	    }
        };
  
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {

        	@Override
        	public void handle(MouseEvent event) {
        		if(fished) {
        			mouseLoc.setX((int)event.getSceneX());
        			mouseLoc.setY((int)event.getSceneY());
        		}
        	}
        });
       
        root.setOnMouseReleased( new EventHandler<MouseEvent>() {
        	@Override
        	public void handle(MouseEvent e) {
        		if(!fished) {
        			tagLoc.setX((int)e.getSceneX());
        			tagLoc.setY((int)e.getSceneY());
        		}
        	}
        });
       
       	EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {

       		@Override
			public void handle(ActionEvent event) {
				fished = !fished;
				
				if(fished) {
					fishToTag = null;
					resetRect();
					root.getChildren().removeAll(rect1,rect2,rect3,rect4,rect5,rect6,
							btn, t1, t2, t3, t4, t5, t6);
					fishingButton.setText("Tag Fish");
				}
				else {
					fishingButton.setText("Fishing");
					root.getChildren().addAll(rect1,rect2,rect3,rect4,rect5,rect6,
							btn, t1, t2, t3, t4, t5, t6);
				}
				
			}
    	   
       	};
       
       	btn.setOnAction(event);
       	fishingButton.setOnAction(event2);
       	reset.setOnAction(event3);
       	root.getChildren().add(btn);
       	root.getChildren().add(fishingButton);
       	root.getChildren().add(reset); 
       
       	root.getChildren().addAll(rect1, t1);
       	root.getChildren().addAll(rect2, t2);
       	root.getChildren().addAll(rect3, t3);
       	root.getChildren().addAll(rect4, t4);
       	root.getChildren().addAll(rect5, t5);
       	root.getChildren().addAll(rect6, t6);
       
       	EventHandler<MouseEvent> rectpressed = new EventHandler<MouseEvent>() {
       		@Override
       		public void handle(MouseEvent t) {
       			if(t.getSource().equals(rect1)) {
       				currentTag = rect1;
       				tagName = "bassFish";
       			}
       			else if(t.getSource().equals(rect2)) {
       				currentTag = rect2;
       				tagName = "troutFish";
       			}
       			else if(t.getSource().equals(rect3)) {
       				currentTag = rect3;
       				tagName = "pickerelFish";
       			}
       			else if(t.getSource().equals(rect4)) {
       				currentTag = rect4;
       				tagName = "drumFish";
       			}
       			else if(t.getSource().equals(rect5)) {
       				currentTag = rect5;
       				tagName = "weakFish";
       			}
       			else if(t.getSource().equals(rect6)) {
       				currentTag = rect6;
       				tagName = "blueFish"; 
       			}
     		 
       			orgX = t.getSceneX();
       			orgY = t.getSceneY();
       			transX = ((Rectangle)(t.getSource())).getTranslateX();
       			transY = ((Rectangle)(t.getSource())).getTranslateY();
       		}
       	};
        
       	EventHandler<MouseEvent> rectdragged = new EventHandler<MouseEvent>() {
       		@Override
       		public void handle(MouseEvent t) {   
       			double offX = t.getSceneX() - orgX;
       			double offY = t.getSceneY() - orgY;
       			double newTranX = transX + offX;
       			double newTranY = transY + offY;
       			
       			((Rectangle)(t.getSource())).setTranslateX(newTranX);
       			((Rectangle)(t.getSource())).setTranslateY(newTranY);
      		   
       			if(t.getSource().equals(rect1)) {
       				t1.setTranslateX(newTranX);
       				t1.setTranslateY(newTranY);
       			}
       			else if(t.getSource().equals(rect2)) {
       				t2.setTranslateX(newTranX);
       				t2.setTranslateY(newTranY);
       			}
       			else if(t.getSource().equals(rect3)) {
       				t3.setTranslateX(newTranX);
       				t3.setTranslateY(newTranY);
       			}
       			else if(t.getSource().equals(rect4)) {
       				t4.setTranslateX(newTranX);
       				t4.setTranslateY(newTranY);
       			}
       			else if(t.getSource().equals(rect5)) {
       				t5.setTranslateX(newTranX);
       				t5.setTranslateY(newTranY);
       			}
       			else {
       				t6.setTranslateX(newTranX);
       				t6.setTranslateY(newTranY);
       			}
       			
       			tagLoc.setX((int)(currentTag.getX()+currentTag.getTranslateX()));
       			tagLoc.setY((int)(currentTag.getY()+currentTag.getTranslateY()));
       		}
       	};
        
        
       	rect1.setOnMousePressed(rectpressed);
        rect1.setOnMouseDragged(rectdragged);
        
        rect2.setOnMousePressed(rectpressed);
        rect2.setOnMouseDragged(rectdragged);
        
        rect3.setOnMousePressed(rectpressed);
        rect3.setOnMouseDragged(rectdragged);
        
        rect4.setOnMousePressed(rectpressed);
        rect4.setOnMouseDragged(rectdragged);
        
        rect5.setOnMousePressed(rectpressed);
        rect5.setOnMouseDragged(rectdragged);
        
        rect6.setOnMousePressed(rectpressed);
        rect6.setOnMouseDragged(rectdragged);
        
	} //end of constructor
		
	
	
	
	 /**
	   * Method used to import the images into the 2D image array
	   */
	private void importImages() {
		pics.put("underwater", createImage("underwater"));
		pics.put("underwater2", createImage("underwater2"));
		pics.put("guidebook", createImage("fishGUIDE"));
		pics.put("blueFish", createImage("bluefish"));
		pics.put("bassFish", createImage("bass"));
		pics.put("pickerelFish", createImage("pickerel"));
		pics.put("drumFish", createImage("drumfish"));
		pics.put("troutFish", createImage("trout"));
		pics.put("weakFish", createImage("weakfish"));
	}
	
   
	
	 /**
	   * Read image from file and return
	   * @param image_file image file
	   * @return img returns created image
	   *                
	   */
    private Image createImage(String image_file) {
    	Image img = new Image("images/"+image_file+".png");
        return img;   	
    }
    
    
    /**
     * Updates the background images and fish to tag
     * 
     */
	public void update() {
		//clear background
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
        // draw background
        gc.drawImage(pics.get("underwater"), 0, 0, canvasWidth, canvasHeight);
        
        if(fishToTag != null) {
        	gc.drawImage(pics.get(fishToTag.getName()), 250, 100, canvasWidth/2, canvasHeight/2);
        }
	}
	
	
	 /**
	   * updates background image based on the booleans passed in for
	   * reseting the rectangle positions;
	   * @param b checkTagLoc
	   * @param b2 checkTagType 
	   *                
	   */
    public void update(boolean b, boolean b2) {
    	//clear background
    	gc.clearRect(0, 0, canvasWidth, canvasHeight);
        // draw background
        gc.drawImage(pics.get("underwater"), 0, 0, canvasWidth, canvasHeight);
        //draw fish to tag
        gc.drawImage(pics.get(fishToTag.getName()), 250, 100, canvasWidth/2, canvasHeight/2);
        
        if(b && b2) {
        	resetRect();
        	fishToTag = null;
        }
    }
    
    
    /**
     * Updates guide book background image
     */
    public void updateGuideBookImage() {
    	//clear background
    	gc.clearRect(0, 0, canvasWidth, canvasHeight);
    	//draw guidebook
    	gc.drawImage(pics.get("guidebook"), 0, 0, canvasWidth, canvasHeight-50);
    }
    
    
    
    /**
     * Updates the fishing game with the array of fishes
     * @param pArr Array of fishes
     */
    public void updateFishingGame(Picture[] pArr) { 
    	gc.clearRect(0, 0, canvasWidth,canvasHeight); 
    	gc.drawImage(pics.get("underwater2"), 0,0, canvasWidth, canvasHeight);
    	
    	for(Picture p : pArr) {
    		if(!p.getTagged()) {
    			transformAndDraw(gc,pics.get(p.getName()),p);
			}
    	}
    }

    
    /**
     * Updates the position of the passed in image.
     * @param gc GraphicsContext variable
     * @param image Created image
     * @param p picture array at a specific index
     */
    private void transformAndDraw(GraphicsContext gc, Image image,  Picture p) {
    	int imgHeight = p.getHeight();
	 	int imgWidth = p.getWidth();
	 	int x = p.getXloc();
	 	int y = p.getYloc();
//	 	int imgWidthOrig = p.getOrigWidth();
//	 	int imgHeightOrig = p.getOrigHeight();
	 	Direction d = p.getDirection();
        // clockwise rotation angle
        // Why clockwise? I don't know. It should probably be counter-clockwise
        double theta = 0.0;
        boolean isFlipped = false;
        // Switch statemement is good to use for enums
        switch(d) {
        	case EAST:{
        		theta = 0.0;
        		break;
        	}
        	case WEST:{
        		isFlipped = true;
        		break;
        	}
        	default:{
        		theta = 0.0;
        		break;
        	}
        }
        if (isFlipped) {
        	ImageView iv = new ImageView(image);
            iv.setScaleX(-1.0);   
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            image = iv.snapshot(params, null);
        }


        // Rotate the CANVAS and NOT the Image, because rotating the image
        // will crop part of the bass's tail for certain angles

        gc.save(); // saves the current state on stack, including the current transform

        // set canvas rotation about the point (x+imageWidth/2, y+imageWidth/2) by angle theta
        Rotate r = new Rotate(theta, x+imgWidth/2, y+imgWidth/2);
        // Transform gc by the rotation
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

        // draw the subimage from the original png to animate the bass's motion
        // The arguments for drawImage are:
        // 
        // drawImage(sourceImage, sX, sY, sWidth, sHeight, dX, dY, dWidth, dHeight)
        //
        // This means that a rectangle of size (sWidth, sHeight) will be CROPPED from sourceImage
        // at location (sX, sY), RESIZED to (dWidth, dHeight), and drawn to the 
        // parent of the GraphicsContext at location (dX, dY).
        
        gc.drawImage(image,  x,  y, imgWidth, imgHeight);
                      
        gc.restore(); // back to original state (before rotation)

 	}
    
    /**
	   * Gets the canvas width;
	   * @return canvasWidth
	   */
	public int getWidth() { return canvasWidth; }
	
	/**
	   * Gets the canvas height
	   * @return canvasHeight
	   */
	public int getHeight() { return canvasHeight; }
	
	/**
	   * Gets the boolean clicked
	   * @return clicked
	   */
	public boolean getClicked() { return clicked; }
	
	/**
	   * Gets the boolean fished
	   * @return fished
	   */
	public boolean getFishing() { return fished;}
	
	/**
	   * Gets the scene variable
	   * @return fishScene
	   */
	public Scene getScene() { return fishScene; }
	
	/**
	   * Gets the node mouse location
	   * @return mouseLoc
	   */
	public Node getMouseLoc() { return mouseLoc; }
	
	
	
	/**
	   * Gets the enum minigame
	   * @return gameEnum
	   */
	public Minigame getMinigame() {
	   	 Minigame m = gameEnum;
	   	 gameEnum = Minigame.FISHGAME;
	   	 return m;
	    }
	
	 /**
	   * Sets the fish to tag
	   * 
	   * @param s The specific picture index passed in
	   */
	public void setFishToTag(Picture s)	{ fishToTag = s; }
	
	
	 /**
	   * Resets the position of all the rectangle objects
	   * 
	   */
    public void resetRect() {
    	rect1.setTranslateX(0); rect1.setTranslateY(0);
    	rect2.setTranslateX(0); rect2.setTranslateY(0);
    	rect3.setTranslateX(0); rect3.setTranslateY(0);
    	rect4.setTranslateX(0); rect4.setTranslateY(0);
    	rect5.setTranslateX(0); rect5.setTranslateY(0);
    	rect6.setTranslateX(0); rect6.setTranslateY(0);
    	t1.setTranslateX(0); t1.setTranslateY(0);
    	t2.setTranslateX(0); t2.setTranslateY(0);
    	t3.setTranslateX(0); t3.setTranslateY(0);
    	t4.setTranslateX(0); t4.setTranslateY(0);
    	t5.setTranslateX(0); t5.setTranslateY(0);
    	t6.setTranslateX(0); t6.setTranslateY(0);
    }
    
    
    
    /**
	   * Checks if all the fishes have been tagged
	   *
	   * @param fish   Array of fishes
	   */
    public void checkAllTagged(Picture[] fish) {
    	boolean fishLeft = false;
    	for(Picture p : fish) {
    		if(!p.getTagged()) {
    			fishLeft= true;
    		}
    	}
    	if(!fishLeft) {
    		gameEnum = Minigame.HOME;
    	}
    }
    
}// end of fish view class



