import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestSpot {
	Spot s1;
	Spot s2;
	Spot s3;
	Spot s4;

	@Before
	public void setupWithColor() {
		s1 = new Spot(new Piece(Color.RED, false), new Position(0, 0), Color.RED);
		if(s1 == null)
			fail("Falha na criação do objeto!");
		s2 = new Spot(new Piece(Color.BLUE, false),new Position(0, 1), Color.BLUE);
		if(s2 == null)
			fail("Falha na criação do objeto!");
	}
	@Before
	public void setupWithoutColor() {
		s3 = new Spot(new Piece(Color.RED, false), new Position(1, 0));
		if(s3 == null)
			fail("Falha na criação do objeto!");
		s4 = new Spot(new Piece(Color.BLUE, false), new Position(1, 1));
		if(s4 == null)
			fail("Falha na criação do objeto!");
	}
}