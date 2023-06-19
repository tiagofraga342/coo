import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestPlayer {
	Player bluePlayer;
	Player redPlayer;

	@Before
	public void setupPlayers() {
		Card[] deck = Card.createCards();
		bluePlayer = new Player("Blue Player", Color.BLUE, deck[0], deck[1]);
		if(bluePlayer == null)
			fail("Falha na criação do objeto!");
		redPlayer = new Player("Red Player", Color.RED, deck[2], deck[3]);
		if(redPlayer == null)
			fail("Falha na criação do objeto!");
	}

	@Test
	public void testGetName() {
		assertEquals(bluePlayer.getName(), "Blue Player");
		assertEquals(redPlayer.getName(), "Red Player");
	}

	@Test
	public void testGetPieceColor() {
		assertEquals(bluePlayer.getPieceColor(), Color.BLUE);
		assertEquals(redPlayer.getPieceColor(), Color.RED);
	}

	@Test
	public void testGetCards() {
		assertNotNull(bluePlayer.getCards());
		assertNotNull(redPlayer.getCards());
	}

	@Test
	public void testSwapCard() {
		String bluePlayerOldCard = bluePlayer.getCards()[0].getName();
		String redPlayerOldCard = redPlayer.getCards()[0].getName();
				
		bluePlayer.swapCard(bluePlayer.getCards()[0], redPlayer.getCards()[0]);
				
		assertEquals(redPlayerOldCard, bluePlayer.getCards()[0].getName());
		assertEquals(bluePlayerOldCard, redPlayer.getCards()[0].getName());
	}

	@Test(expected = InvalidCardException.class)
	public void testSwapCardException() {
		Card outsideCard = new Card("Carta nova", Color.NONE, Movement.TIGER.getMovement()); // Movimento aleatório apenas para teste
		bluePlayer.swapCard(outsideCard, bluePlayer.getCards()[1]);
	}
}