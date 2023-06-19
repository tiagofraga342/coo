import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestCard {
	Card[] gameDeck;
	Card c1;
	Card c2;

	@Before
	public void setupCards() {
		gameDeck = Card.createCards();
		if(gameDeck == null)
			fail("Falha na criação do objeto!");
		c1 = new Card("Carta 1", Color.RED, Movement.TIGER.getMovement()); // Movimento aleatório para teste
		if(c1 == null)
			fail("Falha na criação do objeto!");
		c2 = new Card("Carta 2", Color.BLUE, Movement.TIGER.getMovement());
		if(c2 == null)
			fail("Falha na criação do objeto!");
	}

	@Test
	public void testGetName() {
		assertEquals("Carta 1", c1.getName());
		assertEquals("Carta 2", c2.getName());
	}

	@Test
	public void testGetColor() {
		assertEquals(Color.RED, c1.getColor());
		assertEquals(Color.BLUE, c2.getColor());
	}

	@Test
	public void testGetPositions() {
		// i < 2 pois o tigre possui apenas 2 movimentos
		for(int i = 0; i < 2; i++) {
			assertEquals(Movement.TIGER.getMovement()[i].getCol(), c1.getPositions()[i].getCol());
			assertEquals(Movement.TIGER.getMovement()[i].getRow(), c1.getPositions()[i].getRow());
		}
	}

	// Teste createCards
	@Test
	public void testCreateCards() {
		Card[] cards = Card.createCards();
		assertNotNull(cards);
	}
}