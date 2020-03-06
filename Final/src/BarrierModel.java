import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.stage.Screen;

/**
 * This class includes the information needed to draw the barrier view tutorial
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 *
 */
public class BarrierModel extends Models implements Serializable{
	
	// value of the height and width of screen
	int canvasWidth = (int)Screen.getPrimary().getBounds().getWidth();
	int canvasHeight = (int)Screen.getPrimary().getBounds().getHeight()-50;
	HashMap<String, Plant> plants = new HashMap<String, Plant>();
	Plant[] plantSet1=new Plant[3];
	Plant[] plantSet2=new Plant[3];
	Plant[] plantSet3=new Plant[3];
	Plant[] plantSet4=new Plant[4];
	boolean choiceIsInvasive=false;
	
	ArrayList<PlantSpot> plantSpots=new ArrayList<PlantSpot>();
	ArrayList<WaterPollution> waterSpots=new ArrayList<WaterPollution>();
	ArrayList<RunOff> runOffSpots= new ArrayList<RunOff>();
	
	int round=1;
	
	boolean complete=false;
/**A constructor for the model class which takes in canvasWidth, canvasHeight, imageWidth, and imageHeight to give an instance of constructor
 *
 */
	public BarrierModel() {
		
		plantSet1[0]=(new Plant("Autumn Olive", true));
		plantSet1[1]=(new Plant("Purple Milkweed", false));
		plantSet1[2]=(new Plant("Lowbush Blueberry", false));
		plantSet2[0]=(new Plant("Japanese Stiltgrass", true));
		plantSet2[1]=(new Plant("Yellowroot", false ));
		plantSet2[2]=(new Plant("Twisted Trillium", false));
		plantSet3[0]=(new Plant("Arrowwood", false));
		plantSet3[1]=(new Plant("Fiveleaf Akebia", true));
		plantSet4[0]=(new Plant("Hairy Bittercress", true));
		plantSet3[2]=(new Plant("Toadshade", false));
		plantSet4[1]=(new Plant("Foamflower", false));
		plantSet4[2]=(new Plant("Bluebird Smooth Aster", false));
		plants.put("Default", new Plant("Default", false));
		plants.put("Autumn Olive", plantSet1[0]);
		plants.put("Purple Milkweed", plantSet1[1]);
		plants.put("Lowbush Blueberry", plantSet1[2]);
		plants.put("Japanese Stiltgrass", plantSet2[0]);
		plants.put("Yellowroot", plantSet2[1]);
		plants.put("Twisted Trillium", plantSet2[2]);
		plants.put("Arrowwood", plantSet3[0]);
		plants.put("Fiveleaf Akebia", plantSet3[1]);
		plants.put("Hairy Bittercress", plantSet4[0]);
		plants.put("Toadshade", plantSet3[2]);
		plants.put("Foamflower", plantSet4[1]);
		plants.put("Bluebird Smooth Aster", plantSet4[2]);
		
		plantSpots.add(new PlantSpot(canvasWidth/3, (int)(canvasHeight/2.4), 1, canvasHeight/7, canvasWidth/10));
		plantSpots.add(new PlantSpot((int) (canvasWidth/2.25), (int)(canvasHeight/2.4), 2, canvasHeight/7, canvasWidth/10));
		plantSpots.add(new PlantSpot((int)(canvasWidth/1.75), (int)(canvasHeight/2.4), 3, canvasHeight/7, canvasWidth/10));
		plantSpots.add(new PlantSpot((int)(canvasWidth/1.4), (int)(canvasHeight/2.4), 4, canvasHeight/7, canvasWidth/10));
		
		round=1;
		
		waterSpots.add(new WaterPollution(canvasWidth/3, (int)(canvasHeight/1.6), 1, canvasWidth/6, canvasHeight/6));
		waterSpots.add(new WaterPollution((int) (canvasWidth/2.25), (int)(canvasHeight/1.6), 2, canvasWidth/6, canvasHeight/6));
		waterSpots.add(new WaterPollution((int)(canvasWidth/1.75), (int)(canvasHeight/1.6), 3, canvasWidth/6, canvasHeight/6));
		waterSpots.add(new WaterPollution((int)(canvasWidth/1.4), (int)(canvasHeight/1.6), 4, canvasWidth/6, canvasHeight/6));
		
		runOffSpots.add(new RunOff((int)(canvasWidth/2.8), canvasHeight/4, 1, canvasWidth/6, canvasHeight/6));
		runOffSpots.add(new RunOff((int) (canvasWidth/2.1), canvasHeight/4, 2, canvasWidth/6, canvasHeight/6));
		runOffSpots.add(new RunOff((int)(canvasWidth/1.65), canvasHeight/4, 3, canvasWidth/6, canvasHeight/6));
		runOffSpots.add(new RunOff((int)(canvasWidth/1.35), canvasHeight/4, 4, canvasWidth/6, canvasHeight/6));
	}
	
	/**
	 * takes in the chosen plant and calls methods that set plant spot objects, etc
	 * 
	 * @param chosen the string name of the chosen plant
	 */
	public void update(String chosen) {
		System.out.println(chosen);
		handlePlantChoice(chosen);
		updateRound();
		complete=checkComplete();
	}
	
	/**
	 * takes in the chosen plant and sets the plant spot values and invasive boolean as needed
	 * 
	 * @param chosen the string name of the plant
	 */
	public void handlePlantChoice(String chosen) {
		System.out.println(chosen);
		if (chosen.equals("Default")) {
			choiceIsInvasive=false; //DO NOT REMOVE THIS LINE, WILL GET INFINITE POPUP
			return;
		}
		Plant picked=plants.get(chosen);
		if (picked.getInvasive()) {
			choiceIsInvasive=true;
		}
		else {
			PlantSpot spot=getPlantSpot(round);
			spot.setFilled(true);
			spot.setPlantName(chosen);
			chosen="Default";
			WaterPollution water = getWaterSpot(round);
			water.setExists(false);
		}
		System.out.println(choiceIsInvasive);
	}
	
	/**
	 * takes in a number and returns the plant spot that corresponds to that number
	 * 
	 * @param i the round number
	 * @return PlantSpot the plant spot corresponding to the round number
	 */
	public PlantSpot getPlantSpot(int i) {
		for (PlantSpot s : plantSpots) {
			if (s.getSpotNumber()==i) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * takes in a number and returns the water pollution spot that corresponds to that number
	 * 
	 * @param i the round number
	 * @return WaterPollution the water pollution object corresponding to the round number
	 */
	public WaterPollution getWaterSpot(int i) {
		for (WaterPollution w: waterSpots) {
			if (w.getSpotNumber()==i) {
				return w;
			}
		}
		return null;
	}
	
	/**
	 * getter for the arraylist of plant spots
	 * 
	 * @return An arraylist of plant spots
	 */
	public ArrayList<PlantSpot> getPlantSpots() {
		return plantSpots;
	}
	
	/**
	 * getter for the array of water pollution objects
	 * 
	 * @return an arraylist of water pollution objects
	 */
	public ArrayList<WaterPollution> getWaterSpots(){
		return waterSpots;
	}
	
	/**
	 * a getter for if the chosen plant is invasive
	 * 
	 * @return boolean if the chosen plant is invasive
	 */
	public boolean getChoiceIsInvasive() {
		return choiceIsInvasive;
	}
	
	/**
	 * getter for the plant array used for drawing the buttons
	 * 
	 * @return an array of plant objects
	 */
	public Plant[] getPlantArray() {
		switch(round) {
		case 1:
			System.out.println("returning plant set 1");
			return plantSet1;
		case 2:
			System.out.println("returning plant set 2");
			return plantSet2;
		case 3:
			System.out.println("returning plant set 3");
			return plantSet3;
		case 4:
			System.out.println("returning plant set 4");
			return plantSet4;
		}
		return plantSet1;
	}
	
	/**
	 *updates the number of the round based on the number of plant spots filled 
	 */
	public void updateRound() {
		int count=0;
		for (PlantSpot p: plantSpots) {
			if (p.getFilled()) {
				count++;
			}
		}
		round=count+1;
		if (round>4) {
			round=4;
		}
	}
	
	/**
	 * returns true when all of the plant spots are filled
	 * 
	 * @return boolean telling if all the plant spots are filled
	 */
	public boolean checkComplete() {
		complete=true;
		for (PlantSpot s: plantSpots) {
			if (!s.getFilled()) {
				complete=false;
			}
		}
		return complete;
	}
	
	/**
	 * returns the arraylist of runoff used to draw the pollution
	 * 
	 * @return Arraylist of runoff objects
	 */
	public ArrayList<RunOff> getRunOffSpots(){
		return runOffSpots;
	}
}
