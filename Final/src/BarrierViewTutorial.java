import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class includes the methods and attributes needed for the View of the Barrier game
 * 
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 *
 */
public class BarrierViewTutorial { 

	// value of the height and width of screen
	int canvasWidth = 1380;
	int canvasHeight = 940;
	boolean go=true;
	
    GraphicsContext gc;

    Image background;
	// array of wide png images
    Image[] animationSequence;
	
	//variables to determine the location of image
	int x = 0;
	int y = 0;
	
	String chosen="Default";
	int pollutionNumber=1;
	
	Button b1;
	Button b2;
	Button b3;
	Button nextTut;
	
	
	Scene barrierScene;
	
	Minigame minigame=Minigame.BARRIERTUT;
	Image waterPollution;
	
    HashMap<String, Image> pics = new HashMap<String, Image>();
    
    boolean initialPopup=true;
    boolean secondPopup=false;
    boolean showFinalPopup=true;
    int count=0;
    
	Alert alert = new Alert(AlertType.INFORMATION);
	
	/**
	 * Constructor for View
	 */
	public BarrierViewTutorial() {
		Group root = new Group();
		root = new Group();
        barrierScene = new Scene(root);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        b1= new Button();
        b2= new Button();
        b3= new Button();
        nextTut = new Button();
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		if (e.getSource()==b1) {
        			chosen=b1.getText();
        			//finalPopup=true;
        		}
        		if (e.getSource()==b2) {
        			chosen=b2.getText();
        			//finalPopup=true;
        		}
        		if (e.getSource()==b3) {
        			chosen=b3.getText();
        			//finalPopup=true;
        		}
        		if (e.getSource()== nextTut) {
        			minigame = Minigame.FISHTUT;
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
        
        nextTut.setLayoutX(0);
        nextTut.setLayoutY(0);
        nextTut.setText("next tutorial");
        nextTut.setOnAction(event);
        
        root.getChildren().add(b1);
        root.getChildren().add(b2);
        root.getChildren().add(b3);
        root.getChildren().add(nextTut);
        
		importImages();
		alert.setTitle("Instructions");
		alert.setHeaderText(null);
		alert.setResizable(true);
	}

	/**
	 * imports the images needed by the view into a hashmap called pics
	 * 
	 */
	private void importImages() {
        // Eclipse will look for <path/to/project>/bin/<relative path specified>
        String img_file_base = "images/";
        String ext = ".png";
        // Load background
        pics.put("waterpollution1", createImage("images/waterpollution1.png"));
        pics.put("arrowwoodbutton", createImage("images/arrowwoodbutton.png"));
        pics.put("arrowwoodplant", createImage("images/arrowwoodplant.png"));
        pics.put("autumnolivebutton", createImage("images/autumnolivebutton.png"));
        pics.put("bluebirdsmoothasterbutton", createImage("images/bluebirdsmoothasterbutton.png"));
        pics.put("bluebirdsmoothasterplant", createImage("images/bluebirdsmoothasterplant.png"));
        pics.put("fiveleafakebiabutton", createImage("images/fiveleafakebiabutton.png"));
        pics.put("foamflowerbutton", createImage("images/foamflowerbutton.png"));
        pics.put("foamflowerplant", createImage("images/foamflowerplant.png"));
        pics.put("purplemilkweedbutton", createImage("images/purplemilkweedbutton.png"));
        pics.put("purplemilkweedplant", createImage("images/purplemilkweedplant.png"));
        
        //waterPollution=createImage("images/waterpollution1.png");
        background = createImage(img_file_base + "barrierBackground2" + ext);
	}

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
	  * Returns the name of the chosen plant
	  * 
	  * @return String the name of the plant
	  */
	 public String getChosen() {
			String choice=chosen;
			chosen="Default";
			return choice;
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
	 public void update(Plant[] plants, boolean isInvasive, ArrayList<PlantSpot> plantSpots, ArrayList<WaterPollution> waterSpots, ArrayList<RunOff> runOffSpots) {
			
			String plant1name=plants[0].getName();
			String plant1fixedname=plant1name.toLowerCase().replace(" ", "");
			Image plant1= new Image("images/"+plant1fixedname+"button"+".png");
			System.out.println(plant1fixedname);
			//ImageView plant1Button=new ImageView(plant1);
			ImageView plant1Button= new ImageView(pics.get(plant1fixedname+"button"));
			plant1Button.setFitHeight(150);
			plant1Button.setFitWidth(300);
			b1.setText(plant1name);
			b1.setGraphic(plant1Button);
			
			String plant2name=plants[1].getName();
			String plant2fixedname=plant2name.toLowerCase().replace(" ", "");
			Image plant2=new Image("images/" + plant2fixedname+"button.png");
			ImageView plant2Button= new ImageView(plant2);
			//ImageView plant2Button = new ImageView(pics.get(plant2fixedname)+"button");
			plant2Button.setFitHeight(150);
			plant2Button.setFitWidth(300);
			b2.setText(plant2name);
			b2.setGraphic(plant2Button);
			
			String plant3name=plants[2].getName();
			String plant3fixedname=plant3name.toLowerCase().replace(" ", "");
			Image plant3=new Image("images/"+ plant3fixedname+"button.png");
			ImageView plant3Button=new ImageView(plant3);
			//ImageView plant3Button = new ImageView(pics.get(plant3fixedname)+"button");
			plant3Button.setFitHeight(150);
			plant3Button.setFitWidth(300);
			b3.setText(plant3name);
			b3.setGraphic(plant3Button);
			
	        gc.clearRect(0, 0, canvasWidth, canvasHeight);

	        // draw background
	        gc.drawImage(background, 0, 0, canvasWidth, canvasHeight);
	        gc.drawImage(createImage("images/invasivetraits.png"), 900, 700, 400, 200);
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
	        
	        if (initialPopup && count > 2) {
	        	showInitialPopup();
	        } else { count ++; }
	        if (secondPopup) {
	        	showSecondPopup();
	        }
	   	    if (plantSpots.get(0).getFilled()&&showFinalPopup) {
	   	    	showFinalPopup=false;
	   	    	alert.getDialogPane().setPrefHeight(200.0);
	   	    	alert.setContentText("You have finished the tutorial, please click next tutorial");
	   	    	alert.show();
	   	    }
	        
		}
	 
	 /**
	 * used to show a warning message when the chosen plant is invasive 
	 */
	public void warnInvasive() {
		alert.getDialogPane().setPrefHeight(200.0);
		alert.setContentText("You chose an invasive species! Please pick again");
		alert.show();
		chosen="Default";
		secondPopup=true;
	}
	
	/**
	 * Shows the first popup for the tutorial
	 */
	public void showInitialPopup() {
		alert.getDialogPane().setPrefHeight(200.0);
		alert.setContentText("Read the descriptions of the plants and choose the non-invasive species to create a barrier. If you pick an invasive species, an alert will come up! Try this now by clicking on the Japanese Stiltgrass");
		alert.show();
		initialPopup=false;
	}
	
	/**
	 * Shows the second popup that instructs on how to build the barrier
	 */
	public void showSecondPopup() {
		alert.getDialogPane().setPrefHeight(200.0);
		alert.setContentText("Now choose another species to see your barrier grow!");
		alert.show();
		secondPopup=false;
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
		Image pollutionImage=createImage("images/pollution"+pollutionNumber+".png");
		for (RunOff r: runOffSpots) {
			gc.drawImage(pollutionImage, r.getXloc(), r.getYloc(), r.getWidth(), r.getHeight());
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
				Image plantImage=createImage("images/"+name+"plant.png");
				gc.drawImage(plantImage, p.getXloc(), p.getYloc(), p.getWidth(), p.getHeight());
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
		minigame = Minigame.BARRIERTUT;
		return m;
	}
	
	/**
	 * returns the scene object created, used by the controller to set the scene
	 * 
	 * @return Scene the scene for the view
	 */
	public Scene getScene() { return barrierScene; }
}