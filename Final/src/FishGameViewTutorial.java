
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;


/**
 * FishGameViewTutorial contains some of the methods from the main fishgameView class for display of tutorials
 * 
 * 
 * @author   Ryan Barber, Katarina Pfeifer and Humpher Owusu
 * 
 */


public class FishGameViewTutorial{
	
		boolean fishingInst = true;
		boolean tagInst = true;
		boolean guideInst = true;
		int count = 0;

		HashMap<String, Image> pics = new HashMap<String, Image>();
		//trout picture object
		Picture fishPic = new Picture(100,100,150,75, "troutFish", Direction.EAST,false);
	
		Button reset;
		

	// value of the height and width of screen
	int canvasWidth = 1280;
	int canvasHeight = 720;
	// value of the size of the image
	static final int imgWidthOrig = 100;
	static final int imgHeightOrig = 100;

	int imgWidth = 300;
	int imgHeight = 300;
    
    GraphicsContext gc;
  
    
    Minigame gameEnum = Minigame.FISHTUT;
	
    boolean clicked = false;
   boolean fished = false;


   //Node for mouse location
   Node mouseLoc = new Node(0,0,null);
   Node tagLoc = new Node(0,0, null);
   String tagName;
   
   
	//String for fish to tag name
   	Picture fishToTag = null;
   
   
	
	//variables to determine the location of image
	int x = 0;
	int y = 0;
	
	//rectangels
	Rectangle rect1, rect2;
	
	Text t1,t2;
	
	Rectangle currentTag;
	

	Alert alert = new Alert(AlertType.INFORMATION);
	
	//variables to drag  rectangle object
	
	double orgX, orgY;
	double transX, transY;
	
	Scene fishScene;

	
	//View constructor initialize the starting position for the image
	//Called in controller
	public FishGameViewTutorial() {
        
        Group root = new Group();
        fishScene = new Scene(root);
        
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().addAll(canvas);
       
        gc = canvas.getGraphicsContext2D();
        
       importImages();
       
       alert.setTitle("Instructions");
       alert.setHeaderText(null);
       alert.setResizable(true);
       alert.getDialogPane().setPrefHeight(200.0);
       
       reset = new Button();
       reset.setLayoutX(0);
       reset.setLayoutY(0);
       reset.setText("home");
       
       

       EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
    	   public void handle(ActionEvent e1) {
    		 gameEnum = Minigame.HOME;
    	   }
       };
       
       
       
       
       //Image image1 = new Image(getClass().getResourceAsStream("continue1.png"));
      
   //Button to GuideBook
       Button btn = new Button("GuideBook");
       btn.setLayoutX(700);
        btn.setLayoutY(600);
       
       Button fishingButton = new Button("Fishing");
       fishingButton.setLayoutX(500);
       fishingButton.setLayoutY(600);
       
       
       //Image img1 = new Image("images/continue1.png");
        rect1 = new Rectangle(50, 10, 100, 45);
        rect1.setFill(Color.GREEN);
       
        t1 = new Text(rect1.getX(), rect1.getY()+20, "BASS");
      
        rect2 = new Rectangle(200, 10, 100, 45);
        rect2.setFill(Color.BLUE);
      
        t2 = new Text(rect2.getX(), rect2.getY()+20, "TROUT");
      
       
       EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
    	   public void handle(ActionEvent e) {
    		   clicked = !clicked;
    		   if(clicked) {
    			   root.getChildren().removeAll(rect1,rect2, fishingButton,t1,t2);
    			   btn.setText("back");
    		   }
    		   else {
    			   btn.setText("GuideBook");
    			   root.getChildren().addAll(rect1,rect2,fishingButton,t1,t2);
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
					 root.getChildren().removeAll(rect1,rect2,
							 btn, t1, t2);
					fishingButton.setText("Tag Fish");
				}
				else {
					fishingButton.setText("Fishing");
					root.getChildren().addAll(rect1,rect2,
							btn, t1, t2);
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
      		  
      		 tagLoc.setX((int)(currentTag.getX()+currentTag.getTranslateX()));
      		 tagLoc.setY((int)(currentTag.getY()+currentTag.getTranslateY()));
      	   }
      	   
         };
        
        
        rect1.setOnMousePressed(rectpressed);
        rect1.setOnMouseDragged(rectdragged);
        
        rect2.setOnMousePressed(rectpressed);
        rect2.setOnMouseDragged(rectdragged);
     
	}
		

	//Method used to import the images into the 2D image array
	private void importImages() {
		pics.put("underwater", createImage("underwater"));
		pics.put("underwater2", createImage("underwater2"));
		pics.put("guidebook", createImage("fishGUIDE"));
		pics.put("troutFish", createImage("trout"));
	}
	
    //Read image from file and return
    private Image createImage(String image_file) {
        Image img = new Image("images/"+image_file+".png");
        return img;   	
    }
    

	public void update() {
		gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // draw background
        gc.drawImage(pics.get("underwater"), 0, 0, canvasWidth, canvasHeight);
        
        if(fishToTag != null) {
        	gc.drawImage(pics.get(fishToTag.getName()), 250, 100, canvasWidth/2, canvasHeight/2);
        }
        
       
		if(guideInst && count > 2) {
			count = 0;
			guideInst = false;
			alert.getDialogPane().setPrefHeight(300.0);
			alert.setContentText("Click FishingButton to choose fish to tag.\n"
        			+"Click GuideBook button to see fish.");
        	alert.show();
        } else {count ++; }
	}
	
    
    public void update(boolean b, boolean b2) {
    	
    	gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // draw background
        gc.drawImage(pics.get("underwater"), 0, 0, canvasWidth, canvasHeight);
        
        
        gc.drawImage(pics.get(fishToTag.getName()), 250, 100, canvasWidth/2, canvasHeight/2);
        
        if(b && b2) {
        	resetRect();
        	fishToTag = null;
        }
        
        if(tagInst && count > 5) {
        	count = 0;
        	tagInst = false;
        	alert.getDialogPane().setPrefHeight(200.0);
        	alert.setContentText("Choose tag based on the name of the fish.\n"
        			+ "Drag tag to center of the fish.\nClick GuideBook button to see fish."
        			+ "If you tag a fish correctly it will disappear.\n" 
        			+ "Once all fish are tagged, the game is complete.\nReturn to home to play games.");
        	alert.show();
        } else{ count++;}
    }

    
    public void updateGuideBookImage() {
    	
    gc.clearRect(0, 0, canvasWidth, canvasHeight);
    gc.drawImage(pics.get("guidebook"), 0, 0, canvasWidth, canvasHeight);
    	
    }
    
	
	  public void updateFishingGame(Picture picture) { 
		  gc.clearRect(0, 0, canvasWidth,canvasHeight); 
		  gc.drawImage(pics.get("underwater2"), 0,0, canvasWidth, canvasHeight);
		  
		 
			  if(!picture.getTagged()) {
				  	transformAndDraw(gc,pics.get(picture.getName()),picture);
			  }
		  
		  if(fishingInst) {
	        	fishingInst = false;
	        	alert.setContentText("Click fish to tag.\n"
	        			+"Then click \"Tag Fish\" button.\n");
	        	alert.show();
	        }
		  
	  }
	  
	  public void setFish(Picture p) {
		  fishPic = p;
	  }
	 
	
 private void transformAndDraw(GraphicsContext gc, Image image,  Picture p) {
	 	int imgHeight = p.getHeight();
	 	int imgWidth = p.getWidth();
	 	int x = p.getXloc();
	 	int y = p.getYloc();
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

	
   
       
	public int getWidth() {
		return canvasWidth;
	}
	
	public int getHeight() {
		return canvasHeight;
	}
	
	public int getImageWidth() {
		return imgWidth;
	}
	
	public int getImageHeight() {
		return imgHeight;
	}
	
	public boolean getClicked() {
		return clicked;
	}
	
	public boolean getFishing() {
		return fished;
	}

	public Scene getScene() {
		return fishScene;
	}
	
	public Picture getFish() {
		return fishPic;
	}

	public Node getMouseLoc() {
		return mouseLoc;
	}
	
	public void setFishToTag(Picture s)	{
		fishToTag = s;
	}
	
	
	public Minigame getMinigame() {
	   	 Minigame m = gameEnum;
	   	 gameEnum = Minigame.FISHTUT;
	   	 return m;
	    }
	
    public void resetRect() {
    	rect1.setTranslateX(0); rect1.setTranslateY(0);
    	rect2.setTranslateX(0); rect2.setTranslateY(0);
    
    	t1.setTranslateX(0); t1.setTranslateY(0);
    	t2.setTranslateX(0); t2.setTranslateY(0);
    	
    	
    }

}



