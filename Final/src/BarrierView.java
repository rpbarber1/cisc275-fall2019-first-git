import javafx.event.EventHandler;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class includes the methods and attributes needed for the View of the Barrier game
 * 
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 *
 */
public class BarrierView {

	// value of the height and width of screen
	int canvasWidth = (int)Screen.getPrimary().getBounds().getWidth();
	int canvasHeight = (int)Screen.getPrimary().getBounds().getHeight()-50;
	
	boolean go=true;
	
    GraphicsContext gc;
 
    Image background;
	
	String chosen="Default"; 
	int pollutionNumber=1;
	
	Button b1;
	Button b2;
	Button b3;
	Button goBack;
	
	
	Scene barrierScene;
	
	Minigame minigame=Minigame.BARRIERGAME;
	Image waterPollution;
	
    HashMap<String, Image> pics = new HashMap<String, Image>();
	
    boolean showFinalMessage=true;
    
    Alert alert = new Alert(AlertType.INFORMATION);
    
    double prefHeight=200.0; 
	
	//View constructor initialize the starting position for the image
	//Called in controller
/**
 * Constructor for View
 */
	public BarrierView() {
		Group root = new Group();
		root = new Group();
        barrierScene = new Scene(root);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        b1= new Button();
        b2= new Button();
        b3= new Button();
        goBack=new Button();
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		if (e.getSource()==b1) {
        			chosen=b1.getText();
        		}
        		if (e.getSource()==b2) {
        			chosen=b2.getText();
        		}
        		if (e.getSource()==b3) {
        			chosen=b3.getText();
        		}
        		if (e.getSource()==goBack) {
        			minigame=Minigame.HOME;
        		}
        	}
        };
       
        b1.setLayoutX(100);
        b1.setLayoutY(150);
        b1.setOnAction(event);
        
        b2.setLayoutX(100);
        b2.setLayoutY(400);
        b2.setOnAction(event);
        
        b3.setLayoutX(100);
        b3.setLayoutY(650);
        b3.setOnAction(event);
        
        goBack.setLayoutX(0);
        goBack.setLayoutY(0);
        goBack.setOnAction(event);
        goBack.setText("Return to Map");
        
        root.getChildren().add(b1);
        root.getChildren().add(b2);
        root.getChildren().add(b3);
        root.getChildren().add(goBack);
		importImages();
		
		alert.setTitle("Invasive Species!");
		alert.setHeaderText(null);
		alert.setResizable(true);
		
		
	}
	

	/**
	 * imports the images needed by the view into a hashmap called pics
	 * 
	 */
	//Method used to import the images into the 2D image array
	private void importImages() {
        // Eclipse will look for <path/to/project>/bin/<relative path specified>
		pics.put("waterpollution1", createImage("images/waterpollution1.png"));
        pics.put("arrowwoodbutton", createImage("images/arrowwoodbutton.png"));
        pics.put("arrowwoodplant", createImage("images/arrowwoodplant.png"));
        pics.put("autumnolivebutton", createImage("images/autumnolivebutton.png"));
        pics.put("bluebirdsmoothasterbutton", createImage("images/bluebirdsmoothasterbutton.png"));
        pics.put("bluebirdsmoothasterplant", createImage("images/bluebirdsmoothasterplant.png"));
        pics.put("fiveleafakebiabutton", createImage("images/fiveleafakebiabutton.png"));
        pics.put("foamflowerbutton", createImage("images/foamflowerbutton.png"));
        pics.put("foamflowerplant", createImage("images/foamflowerplant.png"));
        pics.put("hairybittercressbutton", createImage("images/hairybittercressbutton.png"));
        pics.put("japanesestiltgrassbutton", createImage("images/japanesestiltgrassbutton.png"));
        pics.put("lowbushblueberrybutton", createImage("images/lowbushblueberrybutton.png"));
        pics.put("lowbushblueberryplant", createImage("images/lowbushblueberryplant.png"));
        pics.put("pollution1", createImage("images/pollution1.png"));
        pics.put("pollution2", createImage("images/pollution2.png"));
        pics.put("purplemilkweedbutton", createImage("images/purplemilkweedbutton.png"));
        pics.put("purplemilkweedplant", createImage("images/purplemilkweedplant.png"));
        pics.put("toadshadebutton", createImage("images/toadshadebutton.png"));
        pics.put("toadshadeplant", createImage("images/toadshadeplant.png"));
        pics.put("twistedtrilliumbutton", createImage("images/twistedtrilliumbutton.png"));
        pics.put("twistedtrilliumplant", createImage("images/twistedtrilliumplant.png"));
        pics.put("waterpollution1", createImage("images/waterpollution1.png"));
        pics.put("yellowrootbutton", createImage("images/yellowrootbutton.png"));
        pics.put("yellowrootplant", createImage("images/yellowrootplant.png"));
        pics.put("background", createImage("images/barrierBackground2.png"));
        System.out.println(pics.size());
	}
	
    //Read image from file and return
	/**
	 * reads the image from the file name
	 * @param image_file a string that is the name of the file
	 * @return Image the image created based on the string input
	 * 
	 */
    private Image createImage(String image_file) {
        Image img = new Image(image_file);
        return img; 
    }

    /**
     * Draws the buttons, shows warning messages, and draws the pollution as needed by the state of the game
     * 
     * @param plants an array of plant objects used in creating the buttons 
     * @param isInvasive a boolean that tells if the chosen plant is invasive
     * @param plantSpots an arraylist of plant spots 
     * @param waterSpots an arraylist of water pollution objects used in drawing the pollution
     * @param runOffSpots an arraylist of runoff objects used in drawing the runoff
     */
//method used to repaint on the image and called in controller
	public void update(Plant[] plants, boolean isInvasive, ArrayList<PlantSpot> plantSpots, ArrayList<WaterPollution> waterSpots, ArrayList<RunOff> runOffSpots) {
		
		String plant1name=plants[0].getName();
		String plant1fixedname=plant1name.toLowerCase().replace(" ", "");
		ImageView plant1Button= new ImageView(pics.get(plant1fixedname+"button"));
		plant1Button.setFitHeight(150);
		plant1Button.setFitWidth(300);
		b1.setText(plant1name);
		b1.setGraphic(plant1Button);
		
		String plant2name=plants[1].getName();
		String plant2fixedname=plant2name.toLowerCase().replace(" ", "");
		ImageView plant2Button = new ImageView(pics.get(plant2fixedname+"button"));
		plant2Button.setFitHeight(150);
		plant2Button.setFitWidth(300);
		b2.setText(plant2name);
		b2.setGraphic(plant2Button);
		
		String plant3name=plants[2].getName();
		String plant3fixedname=plant3name.toLowerCase().replace(" ", "");
		ImageView plant3Button = new ImageView(pics.get(plant3fixedname+"button"));
		plant3Button.setFitHeight(150);
		plant3Button.setFitWidth(300);
		b3.setText(plant3name);
		b3.setGraphic(plant3Button);
		
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // draw background
        gc.drawImage(pics.get("background"), 0, 0, canvasWidth, canvasHeight);
        drawPollution(gc, pollutionNumber, waterSpots, runOffSpots);
        drawPlants(gc, plantSpots);
        if (pollutionNumber==1) {
        	pollutionNumber=2;
        }
        else if (pollutionNumber==2) {
        	pollutionNumber=1;
        }
        if (isInvasive) {
        	warnInvasive();
        	chosen="Default";
        }
        if (showFinalMessage && plantSpots.get(3).getFilled()==true) {
        	showFinalMessage();
        }
	}
	
	//getter methods to get the frame dimensions and image dimensions	
	/**
	 * getter for the name of the chosen plant
	 * 
	 * @return String the name of the plant
	 */
	public String getChosen() {
		String choice=chosen;
		chosen="Default";
		return choice;
	}
	
	/**
	 * Shows the final message asking the player to go back to the home screen 
	 */
	public void showFinalMessage() {
		showFinalMessage=false;
		alert.getDialogPane().setPrefHeight(prefHeight);
		alert.setContentText("You have completed your barrier! Please return to the home screen");
		alert.show();
	}

	/**
	 * used to show a warning message when the chosen plant is invasive 
	 */
	public void warnInvasive() {
		alert.getDialogPane().setPrefHeight(prefHeight);
		alert.setContentText("You chose an invasive species! Please pick again");
		alert.show();
		chosen="Default";
	}
	
	/**
	 * draws the pollution images including the water pollution and the runoff
	 * 
	 * @param gc a GrphicsContext object that is used for drawing 
	 * @param pollutionNumber int tells which pollution image to use
	 * @param waterSpots an arraylist of water pollution objects that is used to draw the water pollution if it exists
	 * @param runOffSpots an arraylist of runoffspots objects that is used in drawing the runoff
	 */
	private void drawPollution(GraphicsContext gc, int pollutionNumber, ArrayList<WaterPollution> waterSpots, ArrayList<RunOff> runOffSpots) {
		gc.save();
		for (RunOff r: runOffSpots) {
			gc.drawImage(pics.get("pollution"+pollutionNumber), r.getXloc(), r.getYloc(), r.getWidth(), r.getHeight());
		}
		for (WaterPollution w: waterSpots) {
			if (w.getExists()) {
				gc.drawImage(pics.get("waterpollution1"), w.getXloc(), w.getYloc(), w.getWidth(), w.getHeight());
			}
		}
	}
	
	/**
	 * draws the plants if present
	 * 
	 * @param gc GraphicsContext object that is used for drawing
	 * @param plantSpots an arraylist of plantspot objects that is used in drawing the plants if the spots are filled
	 */
	private void drawPlants(GraphicsContext gc, ArrayList<PlantSpot> plantSpots){
		for (PlantSpot p : plantSpots) {
			if (p.getFilled()) {
				String name=p.getPlantName().replace(" ", "");
				name=name.toLowerCase();
				gc.drawImage(pics.get(name+"plant"), p.getXloc(), p.getYloc(), p.getWidth(), p.getHeight());
			}
		}
	}
	
	/**
	 * returns the minigame enum 
	 * 
	 * @return Minigame an enum that is used in switching scenes
	 */
	public Minigame getMinigame() {
		Minigame m = minigame;
		minigame = Minigame.BARRIERGAME;
		return m;
	}
	/**
	 * Getter method
	 * @return the scene for barrier game
	 */
	public Scene getScene() { return barrierScene; }
}