

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Controller extends Application implements Serializable {

	
	//data fields hold Model and View
	private OilGameModel oilGameModel;
	private OilGameView oilGameView;
	private BarrierView barrierView;
	private BarrierModel barrierModel;
	private FishGameView fishView;
	private FishGameModel fishModel;
	private GameView gameView;
		
	//tutorial 
		
	private OilGameModelTutorial oilGameModelTutorial;
	private OilGameViewTutorial oilGameViewTutorial;
	private BarrierModelTutorial barrierModelTutorial;
	private BarrierViewTutorial barrierViewTutorial;
	private FishGameViewTutorial fishViewTutorial;
	private FishGameModelTutorial fishModelTutorial;
	
	Minigame minigame = Minigame.HOME;
	
	ArrayList<Models> minigames = new ArrayList<Models>();

		
	public static void main(String[] args) {
		launch(args);
	}
	    
	@Override
	public void start(Stage theStage) {
		theStage.setTitle("Estuary Exploration");
		
	    	
		oilGameModel = new OilGameModel();
		oilGameView = new OilGameView();
	        
		barrierView=new BarrierView();
		barrierModel = new BarrierModel();
		
		fishModel = new FishGameModel();
		fishView = new FishGameView(fishModel.getWidth(), fishModel.getHeight());
			
		//tutorial
			
		oilGameViewTutorial = new OilGameViewTutorial();
		oilGameModelTutorial = new OilGameModelTutorial(oilGameViewTutorial.getWidth(), oilGameViewTutorial.getHeight(), 
				oilGameViewTutorial.boat, oilGameViewTutorial.skimmer, oilGameViewTutorial.getOilSpot());
	        
		barrierViewTutorial=new BarrierViewTutorial();
		barrierModelTutorial = new BarrierModelTutorial();
	        
		fishViewTutorial = new FishGameViewTutorial();
		fishModelTutorial = new FishGameModelTutorial(fishViewTutorial.getWidth(), fishViewTutorial.getHeight()
				,fishViewTutorial.getImageWidth(), fishViewTutorial.getImageHeight(), 
				fishViewTutorial.getFish(), fishViewTutorial.getMouseLoc());    
	        
		gameView = new GameView();
		
		minigames.add(oilGameModel);
		minigames.add(barrierModel);
		minigames.add(fishModel);
	        
		new AnimationTimer() {
	        
			public void handle(long currentNanoTime)
			{

	                
				switch(minigame) {
	            	
				case OILGAME:
					theStage.setScene(oilGameView.getScene());
	            	
	            	if(oilGameView.doneFirstPart() == false) {
	                //increment the x and y coordinates, alter direction if necessary
	            		oilGameModel.updateLocationandDirection(false, oilGameView.getDirection());
	                
	                //input the x coordinates, y coordinates, and direction picture
	            		oilGameView.updatePart1(oilGameModel.getOilSpots(),
	            				oilGameModel.getBouyArrayList(), oilGameModel.getBoat());
	            		//checking if button was pressed
	            		minigame = oilGameView.getMinigame();
	            	} else {
	            		if(oilGameModel.getChecked() == false) { 
	            			oilGameModel.animalCheck(oilGameView.blackduck, oilGameView.mallard, oilGameView.woodduck);
	            			oilGameView.checkAnimals(oilGameModel.getChecked());
	            		}
	            		oilGameModel.updateLocationandDirection(true, oilGameView.getDirection());
	            	
	            		oilGameView.updatePart2(oilGameModel.getOilSpots(), 
	            				oilGameModel.getBouyArrayList(), oilGameModel.getSkimmer());
	            		
	            		minigame = oilGameModel.checkComplete();
	            		if(minigame == Minigame.OILGAME) {
		            	//CHECK IF BUTTON PRESS TO RETURN HOME.
		            	// ONLY CHECK IF 
	            			minigame = oilGameView.getMinigame();
		            	}
	            		
	            	}
	            		break;
	            	
	            	case BARRIERGAME:
	            		theStage.setScene(barrierView.getScene());
	            		barrierView.update(barrierModel.getPlantArray(), barrierModel.getChoiceIsInvasive(), 
	            				barrierModel.getPlantSpots(), barrierModel.getWaterSpots(), barrierModel.getRunOffSpots());
	            		barrierModel.update(barrierView.getChosen());
	            		minigame = barrierView.getMinigame();
	            		break;
	            	
	            	case FISHGAME:
            			theStage.setScene(fishView.getScene());
						//increment the x and y coordinates, alter direction if necessary 
						//input the x coordinates, y coordinates, and direction picture
							
						if(fishView.getFishing()) {
							fishView.updateFishingGame(fishModel.getFish());
							
							fishModel.updateFish();
							if(fishModel.chosenFish == null) {
								fishModel.checkClicked(fishView.getMouseLoc());
							}
							
						}
						
						else if( fishView.getClicked()) {
							fishView.updateGuideBookImage();
						}
						
						else {
							if(fishView.fishToTag == null) {
								fishView.setFishToTag(fishModel.getChosen());
							} 
							fishModel.chosenFish = null;
							
							if(fishView.tagName != null && fishView.tagLoc != null && fishView.fishToTag != null) {
								fishView.update(fishModel.checkTagLoc(fishView.tagLoc), 
										fishModel.checkTagType(fishView.tagName, fishView.fishToTag.getName()));
							}
							else { 
								fishView.update();
							}
							
						}
//						fishView.checkAllTagged(fishModel.getFish());
						minigame = fishView.getMinigame();
						
	            		break;
	            		
	            	case OILTUT:
	            		
	            		theStage.setScene(oilGameViewTutorial.getScene());
		            	
		            	
		            	if(oilGameViewTutorial.doneFirstPart() == false) {
		                //increment the x and y coordinates, alter direction if necessary
		            	oilGameModelTutorial.updateLocationandDirection(false);
		                
		                //input the x coordinates, y coordinates, and direction picture
		            	oilGameViewTutorial.updateBouys(oilGameModelTutorial.getBouyArrayList());
		                oilGameViewTutorial.updatePart1();
		                //checking if button was pressed
		                minigame = oilGameViewTutorial.getMinigame();
		            	}
		            	else {
		            		if(oilGameModelTutorial.getChecked() == false) { 
		            			oilGameModelTutorial.animalCheck(oilGameViewTutorial.blackduck);
		            			oilGameViewTutorial.checkAnimals(oilGameModelTutorial.getChecked());
		            		}
		            		oilGameModelTutorial.updateLocationandDirection(true);
		            	
		            		oilGameViewTutorial.updatePart2(oilGameModelTutorial.getOilSpots());
		            		
		            		minigame = oilGameViewTutorial.getMinigame();
		            	}
		            		break;
	            		
	            	case BARRIERTUT:
	            		theStage.setScene(barrierViewTutorial.getScene());
	            		barrierViewTutorial.update(barrierModelTutorial.getPlantArray(), barrierModelTutorial.getChoiceIsInvasive(),
	            				barrierModelTutorial.getPlantSpots(), barrierModelTutorial.getWaterSpots(), barrierModelTutorial.getRunOffSpots());
	            		
	            		barrierModelTutorial.update(barrierViewTutorial.getChosen());
	            		minigame = barrierViewTutorial.getMinigame();
	            		
	            		break;
	            	
	            	case FISHTUT:
	            		theStage.setScene(fishViewTutorial.getScene());
						//increment the x and y coordinates, alter direction if necessary 
						//input the x coordinates, y coordinates, and direction picture
							
						if(fishViewTutorial.getFishing()) {
							fishViewTutorial.updateFishingGame(fishModelTutorial.getFish());
							
							fishModelTutorial.updateFish();
							fishViewTutorial.setFish(fishModelTutorial.getFish());
							if(fishModelTutorial.chosenFish == null) {
								fishModelTutorial.checkClicked();
							}
							
						}
						
						else if( fishViewTutorial.getClicked()) {
							fishViewTutorial.updateGuideBookImage();
						}
						
						else {
							if(fishViewTutorial.fishToTag == null) {
								fishViewTutorial.setFishToTag(fishModelTutorial.getChosen());
							} 
							fishModelTutorial.chosenFish = null;
							
							if(fishViewTutorial.tagName != null && fishViewTutorial.tagLoc != null && fishViewTutorial.fishToTag != null) {
								fishViewTutorial.update(fishModelTutorial.checkTagLoc(fishViewTutorial.tagLoc), 
										fishModelTutorial.checkTagType(fishViewTutorial.tagName, fishViewTutorial.fishToTag.getName()));
							}
							else { 
								fishViewTutorial.update();
							}
						}
						minigame = fishViewTutorial.getMinigame();
	            		break;
	            	
	            	case HOME:
	            		theStage.setScene(gameView.getScene());
	            		gameView.update();
	            		minigame = gameView.getMinigame();
	            		if(gameView.getSaveGame()) {
	            			
	            			try {
            					FileOutputStream fos = new FileOutputStream("bin/tempdata.ser");
            					ObjectOutputStream oos = new ObjectOutputStream(fos);
            					oos.writeObject(minigames);
            					oos.close();
            					gameView.setSaveGame(false);
            				} catch(Exception e) {
            					System.err.println(e);
            				}
	            		}
	            		if(gameView.getLoadGame()) {
	            			try
            	        	{
            	            	FileInputStream fis = new FileInputStream("bin/tempdata.ser");
            	            	ObjectInputStream ois = new ObjectInputStream(fis);
            	            	minigames = (ArrayList<Models>)ois.readObject();
            	            	oilGameModel = (OilGameModel) minigames.get(0);
            	            	barrierModel = (BarrierModel) minigames.get(1);
            	            	fishModel = (FishGameModel) minigames.get(2);
            	            	ois.close();
            	            	gameView.setLoadGame(false);
            	            	// Clean up the file
            	            	new File("bin/tempdata.ser").delete();
            	        	}
            	        	catch (Exception e)
            	        	{
            	            	System.err.println(e);
            	        	}
	            		}
	            		break;
	            	
	            	default:
	            		theStage.setScene(gameView.getScene());
	            		gameView.update();
	            		break;
	            	}
	            
	                try {
	                    Thread.sleep(100);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	            
	        }.start();
	        theStage.show();
	    }
	
}