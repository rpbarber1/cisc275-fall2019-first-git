import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * JUnit test class for the OilGameModel class
 * @author ryan
 *
 */
public class OilGameModelJUnit {

	@Test
	public void updateLocationAndDirectiontest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		testModel.updateLocationandDirection(false, Direction.STOP);
		assertEquals(testModel.boat.getDirection(), Direction.STOP);
		assertEquals(testModel.xIncr, 0);
		assertEquals(testModel.yIncr, 0);
		testModel.updateLocationandDirection(true, Direction.STOP);
		assertEquals(testModel.skimmer.getDirection(), Direction.STOP);
		assertEquals(testModel.xIncr, 0);
		assertEquals(testModel.yIncr, 0);
		
		testModel.updateLocationandDirection(false, Direction.NORTH);
		assertEquals(testModel.boat.getDirection(), Direction.NORTH);
		assertEquals(testModel.xIncr, 0);
		assertEquals(testModel.yIncr, -10);
		testModel.updateLocationandDirection(true, Direction.NORTH);
		assertEquals(testModel.skimmer.getDirection(), Direction.NORTH);
		assertEquals(testModel.xIncr, 0);
		assertEquals(testModel.yIncr, -6);
		
		testModel.updateLocationandDirection(false, Direction.EAST);
		assertEquals(testModel.boat.getDirection(), Direction.EAST);
		assertEquals(testModel.xIncr, 10);
		assertEquals(testModel.yIncr, 0);
		testModel.updateLocationandDirection(true, Direction.EAST);
		assertEquals(testModel.skimmer.getDirection(), Direction.EAST);
		assertEquals(testModel.xIncr, 6);
		assertEquals(testModel.yIncr, 0);
		
		testModel.updateLocationandDirection(false, Direction.WEST);
		assertEquals(testModel.boat.getDirection(), Direction.WEST);
		assertEquals(testModel.xIncr, -10);
		assertEquals(testModel.yIncr, 0);
		testModel.updateLocationandDirection(true, Direction.WEST);
		assertEquals(testModel.skimmer.getDirection(), Direction.WEST);
		assertEquals(testModel.xIncr, -6);
		assertEquals(testModel.yIncr, 0);
		
		testModel.updateLocationandDirection(false, Direction.NORTHEAST);
		assertEquals(testModel.boat.getDirection(), Direction.NORTHEAST);
		assertEquals(testModel.xIncr, 10);
		assertEquals(testModel.yIncr, -10);
		testModel.updateLocationandDirection(true, Direction.NORTHEAST);
		assertEquals(testModel.skimmer.getDirection(), Direction.NORTHEAST);
		assertEquals(testModel.xIncr, 6);
		assertEquals(testModel.yIncr, -6);
		
		testModel.updateLocationandDirection(false, Direction.NORTHWEST);
		assertEquals(testModel.boat.getDirection(), Direction.NORTHWEST);
		assertEquals(testModel.xIncr, -10);
		assertEquals(testModel.yIncr, -10);
		testModel.updateLocationandDirection(true, Direction.NORTHWEST);
		assertEquals(testModel.skimmer.getDirection(), Direction.NORTHWEST);
		assertEquals(testModel.xIncr, -6);
		assertEquals(testModel.yIncr, -6);
		
		testModel.updateLocationandDirection(false, Direction.SOUTHEAST);
		assertEquals(testModel.boat.getDirection(), Direction.SOUTHEAST);
		assertEquals(testModel.xIncr, 10);
		assertEquals(testModel.yIncr, 10);
		testModel.updateLocationandDirection(true, Direction.SOUTHEAST);
		assertEquals(testModel.skimmer.getDirection(), Direction.SOUTHEAST);
		assertEquals(testModel.xIncr, 6);
		assertEquals(testModel.yIncr, 6);
		
		testModel.updateLocationandDirection(false, Direction.SOUTHWEST);
		assertEquals(testModel.boat.getDirection(), Direction.SOUTHWEST);
		assertEquals(testModel.xIncr, -10);
		assertEquals(testModel.yIncr, 10);
		testModel.updateLocationandDirection(true, Direction.SOUTHWEST);
		assertEquals(testModel.skimmer.getDirection(), Direction.SOUTHWEST);
		assertEquals(testModel.xIncr, -6);
		assertEquals(testModel.yIncr, 6);
		
		testModel.updateLocationandDirection(false, Direction.SOUTH);
		assertEquals(testModel.boat.getDirection(), Direction.SOUTH);
		assertEquals(testModel.xIncr, 0);
		assertEquals(testModel.yIncr, 10);
		testModel.updateLocationandDirection(true, Direction.SOUTH);
		assertEquals(testModel.skimmer.getDirection(), Direction.SOUTH);
		assertEquals(testModel.xIncr, 0);
		assertEquals(testModel.yIncr, 6);
	}
	
	@Test
	public void recordLocationTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		
		
		testModel.count = 3;
		testModel.boat.setXloc(0);
		testModel.boat.setYloc(0);
		testModel.recordLocation();
		// test the boat location is recorded correctly
		assertEquals(0, testModel.boatLocation.get(0).xloc);
		assertEquals(0, testModel.boatLocation.get(0).yloc);
		assertEquals(Direction.STOP, testModel.boatLocation.get(0).direction);
		// test buoy location is also recorded since count % 3 = 0
		assertEquals(0, testModel.buoyLocation.get(0).getXloc());
		assertEquals(0, testModel.buoyLocation.get(0).getYloc());
		assertEquals(Direction.STOP, testModel.buoyLocation.get(0).getDirection());
		
		
		
	}
	
	@Test
	public void boatUpdateTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		testModel.xIncr = -10;
		testModel.yIncr = -10;
		testModel.boat.setXloc(0);
		testModel.boat.setYloc(0);
		testModel.boatupdate();
		//test if previousXloc and previousYloc are set correctly
		assertEquals(0, testModel.previousXloc);
		assertEquals(0, testModel.previousYloc);
		// test if boat x and y are set correctly when xIncr and yIncr are changed by the for loops.
		assertEquals(0, testModel.boat.getXloc());
		assertEquals(0, testModel.boat.getYloc());
		
		//test if boat x, y are set correctly when not changed by for loop
		testModel.xIncr = -10;
		testModel.yIncr = -10;
		testModel.boat.setXloc(100);
		testModel.boat.setYloc(100);
		testModel.boatupdate();
		assertEquals(100, testModel.previousXloc);
		assertEquals(100, testModel.previousYloc);
		assertEquals(90, testModel.boat.getXloc());
		assertEquals(90, testModel.boat.getYloc());
	}
	
	@Test
	public void skimmerUpdateTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		testModel.xIncr = 6;
		testModel.yIncr = 6;
		int tmpX = testModel.skimmer.getXloc() + 6;
		int tmpY = testModel.skimmer.getYloc() + 6;
		testModel.skimmerupdate();
		//testing if skimmer x, y are set correctly
		assertEquals(tmpX, testModel.skimmer.getXloc());
		assertEquals(tmpY, testModel.skimmer.getYloc());
		//testing if collision is working 
		for(int i = 0; i< testModel.oilSpots.length; i++) {
			if(testModel.oilSpots[i] != null && testModel.skimmer.getXloc() > testModel.oilSpots[i].getXloc() 
					&& testModel.skimmer.getXloc() < testModel.oilSpots[i].getXloc()+30 && testModel.skimmer.getYloc() > testModel.oilSpots[i].getYloc() 
					&& testModel.skimmer.getYloc() < testModel.oilSpots[i].getYloc()+30) {
				assertNull(testModel.oilSpots[i]);
			}
		}
	}
	
	@Test
	public void animalCheckTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		Picture blackduck = new Picture(500, 300, 100,60, 249, 132);
		Picture mallard = new Picture(1000,250,100,60,256,166);
		Picture woodduck = new Picture(650,550,100,55,253,95);
		testModel.boatLocation.add(new Node(0,0,null));
		//test if checked is false if no animals are trapped.
		assertFalse(testModel.checked);
		//creating boat locations that would trap an animal.
		testModel.boatLocation.add(new Node(mallard.getXloc() - 100, mallard.getYloc(), null));
		testModel.boatLocation.add(new Node(mallard.getXloc() + 100, mallard.getYloc(), null));
		testModel.animalCheck(blackduck, mallard, woodduck);
		assertTrue(testModel.checked);
		//testing if boat location on an animal sets checked.
		OilGameModelToTest testModel2 = new OilGameModelToTest();
		// this node is on the mallard duck.
		testModel2.boatLocation.add(new Node(mallard.getXloc() + 10, mallard.getYloc()+10, null));
		testModel2.animalCheck(blackduck, mallard, woodduck);
		assertTrue(testModel2.checked);
	}
	
	@Test
	public void checkCompleteTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		//test if minigame remains on "OILGAME"
		Minigame testMinigame = testModel.checkComplete();
		assertEquals(Minigame.OILGAME, testMinigame);
		//set all oilSpots to null and test if minigame is changed to "HOME"
		for(int i = 0; i < testModel.numOilSpots; i++) {
			testModel.oilSpots[i] = null;
		}
		testMinigame = testModel.checkComplete();
		assertEquals(Minigame.HOME, testMinigame);
	}
	
	@Test
	public void createBoatsTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		//test not null for creation of boat and skimmer.
		assertNotNull(testModel.boat);
		assertNotNull(testModel.skimmer);
	}
	
	@Test
	public void creatOilSpotsTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		//test not null for all oil spots created.
		for(int i = 0; i < testModel.numOilSpots; i++) {
			assertNotNull(testModel.oilSpots[i]);
		}
	}
	
	@Test
	public void getBuoyArrayListTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		ArrayList<Picture> testList = testModel.getBouyArrayList();
		assertEquals(testModel.buoyLocation, testList);
	}
	
	@Test 
	public void getOilSpotsTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		for(int i = 0; i < testModel.numOilSpots; i++) {
			assertEquals(testModel.oilSpots[i], testModel.getOilSpots()[i]);
		}
	}
	
	@Test
	public void getCheckedTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		//checked boolean starts as false.
		assertFalse(testModel.getChecked());
	}
	
	@Test
	public void getBoatTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		assertEquals(testModel.boat, testModel.getBoat());
	}
	
	@Test
	public void getSkimmerTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		assertEquals(testModel.skimmer, testModel.getSkimmer());
	}
	
	@Test
	public void getWidthTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		assertEquals(testModel.canvasWidth, testModel.getWidth());
	}
	
	@Test
	public void getHeightTest() {
		OilGameModelToTest testModel = new OilGameModelToTest();
		assertEquals(testModel.canvasHeight, testModel.getHeight());
	}

}
