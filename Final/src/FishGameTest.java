 import static org.junit.Assert.*;

import org.junit.Test;
 
public class FishGameTest {

	@Test
	public void checkTagLoctest() {
		FishGameModelToTest fModel = new FishGameModelToTest();
		assertFalse(fModel.checkTagLoc(null));
		
		Node r = new Node(0,0,null);
		assertFalse(fModel.checkTagLoc(r));
		
		r.setX(0);
		r.setY(0);
		assertFalse(fModel.checkTagLoc(r));
		
		r.setX(fModel.canvasWidth/2);
		r.setY(fModel.canvasHeight/2);
		assertTrue(fModel.checkTagLoc(r));
		
		r.setX(fModel.canvasWidth/2);
		r.setY(0);
		assertFalse(fModel.checkTagLoc(r));
		
		r.setX(0);
		r.setY(fModel.canvasHeight/2);
		assertFalse(fModel.checkTagLoc(r));
		
	}
	
	@Test
	public void checkClickedNodeTest() {
		
		FishGameModelToTest fmodel = new FishGameModelToTest();
		Node r = new Node(110,110,null); //Node is on the fish
		fmodel.checkClicked(r);
		assertTrue(fmodel.fishPics[0].getTagged());
	}
	
	
	@Test
	public void checkTagTypeTest() {
		
		FishGameModelToTest fmodel = new FishGameModelToTest();
		
		assertFalse(fmodel.checkTagType(null, null));
		assertTrue(fmodel.checkTagType("weakFish", "weakFish"));
		assertFalse(fmodel.checkTagType("weakFish", "bassFish"));
	}
	
	@Test
	public void updateFishTest() {
		
		FishGameModelToTest fxmodel = new FishGameModelToTest();
		
		int tmpx = fxmodel.fishPics[0].getXloc();
		fxmodel.updateFish();
		assertEquals(fxmodel.fishPics[0].getXloc(), tmpx + fxmodel.xIncr);
		
	    fxmodel.fishPics[0].setDirection(Direction.WEST);
	    tmpx = fxmodel.fishPics[0].getXloc();
	    fxmodel.updateFish();
	    assertEquals(fxmodel.fishPics[0].getXloc(), tmpx - fxmodel.xIncr);
	    
	    fxmodel.fishPics[0].setXloc(0);
	    fxmodel.fishPics[0].setDirection(Direction.WEST);
	    fxmodel.updateFish();
	    assertEquals(fxmodel.fishPics[0].getDirection(), Direction.EAST);
	    
	    fxmodel.fishPics[0].setXloc(20);
	    fxmodel.fishPics[0].setDirection(Direction.WEST);
	    fxmodel.updateFish();
	    assertEquals(fxmodel.fishPics[0].getDirection(), Direction.WEST);
	    
	    fxmodel.fishPics[0].setXloc(0);
	    fxmodel.fishPics[0].setDirection(Direction.EAST);
	    fxmodel.updateFish();
	    assertEquals(fxmodel.fishPics[0].getDirection(), Direction.EAST);
	    
	    fxmodel.fishPics[0].setXloc(20);
	    fxmodel.fishPics[0].setDirection(Direction.EAST);
	    fxmodel.updateFish();
	    assertEquals(fxmodel.fishPics[0].getDirection(), Direction.EAST);
	    
	    fxmodel.fishPics[0].setXloc(fxmodel.canvasWidth);
	    fxmodel.fishPics[0].setDirection(Direction.EAST);
	    fxmodel.updateFish();
	    assertEquals(fxmodel.fishPics[0].getDirection(), Direction.WEST);
	    
	    
	    
	}
	
	@Test
	public void createFishTest() {
		
		FishGameModelToTest fmodel = new FishGameModelToTest();
		for(int i = 0; i< fmodel.fishPics.length; i++) {
			
			assertNotNull(fmodel.fishPics[i]);
		}
	}
	
	@Test
	public void getfishTest() {
		FishGameModelToTest fmodel = new FishGameModelToTest();
		for(int i= 0; i<fmodel.fishPics.length; i++) {
			assertEquals(fmodel.fishPics[i], fmodel.getFish()[i]);
		}
		
	}
	
	@Test
	public void getHeightTest() {
		FishGameModelToTest fmodel = new FishGameModelToTest();
		assertEquals(fmodel.canvasHeight, fmodel.getHeight());
		
	}
	
	@Test
	public void getWidthTest() {
		FishGameModelToTest fmodel = new FishGameModelToTest();
		assertEquals(fmodel.canvasWidth, fmodel.getWidth());
		
	}
	
	@Test
	public void getChosenTest() {
		FishGameModelToTest fmodel = new FishGameModelToTest();
		
		assertNull(fmodel.getChosen());
	}

}
