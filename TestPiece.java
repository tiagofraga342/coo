import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestPiece {
	Piece pRed;
	Piece pBlue;
	Piece pRedMaster;
	Piece pBlueMaster;
	
	@Before
	public void setup() {
		pRed = new Piece(Color.RED, false);
		if(pRed == null)
			fail("Falha na criação do objeto!");
		pBlue = new Piece(Color.BLUE, false);
		if(pBlue == null) 
			fail("Falha na criação do objeto!");
		pBlueMaster = new Piece(Color.BLUE, true);
		if(pBlueMaster == null) 
			fail("Falha na criação do objeto!");
		pRedMaster = new Piece(Color.RED, true);
		if(pRedMaster == null) 
			fail("Falha na criação do objeto!");
	}
	
	@Test
	public void testPieceColor() {
		assertTrue(pRed.getColor().equals(Color.RED));
		assertTrue(pBlue.getColor().equals(Color.BLUE));
		assertTrue(pRedMaster.getColor().equals(Color.RED));
		assertTrue(pBlueMaster.getColor().equals(Color.BLUE));
	}

	@Test
	public void testPieceMaster() {
		assertFalse(pRed.isMaster());
		assertFalse(pBlue.isMaster());
		assertTrue(pRedMaster.isMaster());
		assertTrue(pBlueMaster.isMaster());
	}
}
