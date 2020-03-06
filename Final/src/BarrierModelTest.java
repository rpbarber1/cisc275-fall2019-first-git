import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This file contains the JUnit tests for the Barrier Game Model
 * @author Ryan Barber, Katarina Pfeifer, Humpher Owusu
 */
public class BarrierModelTest {

	@Test 
	public void updateTest() {
		
		BarrierModelToTest bModel= new BarrierModelToTest();
		bModel.update("Default");
		assertFalse(bModel.getChoiceIsInvasive());
	}
	
	@Test
	public void updateTest2() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		bModel.update("Hairy Bittercress");
		assertTrue(bModel.getChoiceIsInvasive());
	}
	
	@Test
	public void updateTest3() {
		BarrierModelToTest bModel=new BarrierModelToTest();
		bModel.update("Foamflower");
		assertFalse(bModel.getChoiceIsInvasive());
	}
	
	@Test
	public void handlePlantChoiceTest() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		bModel.handlePlantChoice("Default");
		assertFalse(bModel.getChoiceIsInvasive());
	}
	
	@Test
	public void handlePlantChoiceTest2() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		bModel.handlePlantChoice("Purple Milkweed");
		assertFalse(bModel.getChoiceIsInvasive());
	}
	
	@Test
	public void handlePlantChoiceTest3() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		bModel.handlePlantChoice("Japanese Stiltgrass");
		assertTrue(bModel.getChoiceIsInvasive());
	}
	
	@Test
	public void getWaterSpotTest() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		WaterPollution w= new WaterPollution(300,300,4,200,200);
		assertEquals(w.getSpotNumber(), bModel.getWaterSpot(4).getSpotNumber());
	}
	
	@Test
	public void getWaterSpotTest2() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		assertNull(bModel.getWaterSpot(6));
	}

	@Test
	public void getPlantSpotTest() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		PlantSpot p= new PlantSpot(200,200,3,500,500);
		assertEquals(p.getSpotNumber(), bModel.getPlantSpot(3).getSpotNumber());
	}
	
	@Test
	public void getPlantSpotTest2() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		assertNull(bModel.getPlantSpot(9));
	}
	
	@Test
	public void getPlantSpotsTest() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		bModel.update("Foamflower");
		assertEquals("Foamflower", bModel.getPlantSpots().get(0).getPlantName());
	}
	
	@Test
	public void getWaterSpotsTest() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		bModel.update("Purple Milkweed");
		bModel.update("Twisted Trillium");
		assertFalse(bModel.getWaterSpots().get(1).getExists());
	}
	
	@Test
	public void updateRoundTest() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		bModel.update("Purple Milkweed");
		bModel.update("Twisted Trillium");
		bModel.update("Foamflower");
		bModel.update("Purple Milkweed");
		bModel.update("Foamflower");
		String name= bModel.getPlantArray()[0].getName();
		assertEquals("Hairy Bittercress", name);
	}
	
	@Test
	public void getPlantArrayTest() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		String name = bModel.getPlantArray()[2].getName();
		assertEquals("Lowbush Blueberry", name);
	}
	
	@Test
	public void getPlantArrayTest2() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		bModel.update("Lowbush Blueberry");
		assertTrue(bModel.getPlantArray()[0].getInvasive());
	}
	
	@Test
	public void getPlantArrayTest3() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		bModel.update("Lowbush Blueberry");
		bModel.update("Purple Milkweed");
		assertFalse(bModel.getPlantArray()[2].getInvasive());
	}
	
	@Test
	public void getPlantArrayTest4() {
		BarrierModelToTest bModel = new BarrierModelToTest();
		bModel.update("Lowbush Blueberry");
		bModel.update("Foamflower");
		bModel.update("Purple Milkweed");
		assertEquals("Bluebird Smooth Aster", bModel.getPlantArray()[2].getName());
	}
	
	@Test
	public void getRunOffSpotsTest() {
		BarrierModelToTest bModel= new BarrierModelToTest();
		assertEquals(1050, bModel.getRunOffSpots().get(3).getXloc());
	}

}