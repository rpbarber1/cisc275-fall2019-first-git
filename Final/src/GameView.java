import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Screen;

/**
 * This class acts as the View for the home screen. It contains Button objects for each minigame, the tutorial, and 
 * the save and load buttons which write the state of the games to file.
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu.
 *
 */

public class GameView {
	
	//size of screen
    private static int canvasWidth = (int)Screen.getPrimary().getBounds().getWidth();
    private static int canvasHeight = (int)Screen.getPrimary().getBounds().getHeight() - 50;
	
	Button barrierGame;
	Button oilGame;
	Button fishGame;
	Button tutorial;
	Button save;
	Button load;
	
	boolean saveGame = false;
	boolean loadGame = false;
	
	GraphicsContext gc;
	
	Image background;
	
	Minigame minigame = Minigame.HOME;
	
	Scene homeScene;
	
	/**
	 * Consructor creates JavaFX Group, Canvas, Scene, Buttons, and implements EventHandlers.
	 */
	public GameView() {
        
		Group root = new Group();
        homeScene = new Scene(root);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        
        createBackground();

        barrierGame= new Button();
        oilGame= new Button();
        fishGame= new Button();
        tutorial = new Button(); 
        save = new Button();
        load = new Button();
        
        
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		if (e.getSource()==barrierGame) {
        			minigame=Minigame.BARRIERGAME;
        		}
        		if (e.getSource()==oilGame) {
        			minigame=Minigame.OILGAME;
        		}
        		if (e.getSource()==fishGame) {
        			minigame=Minigame.FISHGAME;
        		}
        		if (e.getSource() == tutorial) {
        			minigame = Minigame.OILTUT;
        		}
        		if (e.getSource() == save) {
        			saveGame = true;
        		}
        		if (e.getSource() == load) {
        			loadGame = true;
        		}
        	}
        };

        barrierGame.setLayoutX(canvasWidth/4);
        barrierGame.setLayoutY(canvasHeight/4);
        barrierGame.setOnAction(event);
        barrierGame.setText("Barrier Game");
       
        oilGame.setLayoutX(canvasWidth/4);
        oilGame.setLayoutY(canvasHeight/2);
        oilGame.setOnAction(event);
        oilGame.setText("Oil Cleanup Game");
        
        fishGame.setLayoutX(canvasWidth-canvasWidth/3);
        fishGame.setLayoutY(canvasHeight/1.75);
        fishGame.setOnAction(event);
        fishGame.setText("Fish Labeling Game");
        
        tutorial.setLayoutX(canvasWidth-100);
        tutorial.setLayoutY(0);
        tutorial.setOnAction(event);
        tutorial.setText("Tutorial");
        
        save.setLayoutX(0);
        save.setLayoutY(0);
        save.setOnAction(event);
        save.setText("save");
        
        load.setLayoutX(0);
        load.setLayoutY(50);
        load.setOnAction(event);
        load.setText("load");
        
        root.getChildren().add(barrierGame);
        root.getChildren().add(oilGame);
        root.getChildren().add(fishGame);
        root.getChildren().add(tutorial);
        root.getChildren().add(save);
        root.getChildren().add(load);
	}
	
	/**
	 * This method draws the background for the home screen.
	 */
	public void update() {
		gc.drawImage(background, 0,0, canvasWidth, canvasHeight);			
	}
	/**
	 * This method initializes the background Image object.
	 */
	public void createBackground() {
		background = createImage("images/homebackground2.png");
	}
	/**
	 * This method reads .png images from files.
	 * @param image_file the String "filePath/fileName.png"
	 * @return Image object.
	 */
	private Image createImage(String image_file) {
        Image img = new Image(image_file);
        return img; 
    }
	/**
	 * This method is used to determine the value of the Minigame enum \"minigame\" which 
	 * chages based on the button pressed.
	 * @return the value of \"minigame\"
	 */
	public Minigame getMinigame() {
		Minigame m = minigame;
		minigame = Minigame.HOME;
		return m;
	}
	/**
	 * Getter method
	 * @return the Scene for this View
	 */
	public Scene getScene() {
		return homeScene;
	}
	/**
	 * Getter method
	 * @return the boolean \"saveGame\"
	 */
	public boolean getSaveGame() { return saveGame; }
	/**
	 * Getter method
	 * @return the boolean \"loadGame\"
	 */
	public boolean getLoadGame() { return loadGame; }
	/**
	 * Setter method
	 * @param b the boolean used to set the new value of \"saveGame\"
	 */
	public void setSaveGame(boolean b) { saveGame = b; }
	/**
	 * Setter method
	 * @param b the boolean used to set the new value of \"LoadGame\"
	 */
	public void setLoadGame(boolean b) { loadGame = b; }
}

