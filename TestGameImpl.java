import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestGameImpl {
	GameImpl g1;
	GameImpl g2;
	GameImpl g3;
	/*GameImpl jogo1 = new GameImpl("Tiago", "sla");
		jogo1.printBoard();
		System.out.println(jogo1.getBluePlayer().getName());
		System.out.println(jogo1.getRedPlayer().getName());
		System.out.println("Current player: " + jogo1.getCurrentPlayer().getName());
		System.out.println("Table card: " + jogo1.getTableCard().getName());
		Card[] tiagoCards = jogo1.getRedPlayer().getCards();
		System.out.println("Cartas tiago:");
		for(int i = 0; i < 2; i++) {
			System.out.println(tiagoCards[i].getName());
		}
		jogo1.makeMove(jogo1.getCurrentPlayer().getCards()[1], jogo1.getCurrentPlayer().getCards()[1].getPositions()[0], new Position(0, 2));
		jogo1.printBoard();
	*/

	// Setup
	@Before
	public void setupGame() {
		g1 = new GameImpl();
		if(g1 == null) 
			fail("Falha na criação do objeto!");
		g2 = new GameImpl("Tiago Vermelho", "Tiago Azul");
		if(g2 == null) 
			fail("Falha na criação do objeto!");
		//g3 = new GameImpl("Tiago Vermelho", "Tiago Azul"); // testar com deck aleatório
		//if(g3 == null) 
		//	fail("Falha na criação do objeto!");
	}

	// Teste dos construtores
	@Test
	public void testBuildBoard() {

	}

	@Test
	public void testGetSpotColor() {
		assertEquals(Color.BLUE, g1.getSpotColor(new Position(0, 2)));
		assertEquals(Color.RED, g1.getSpotColor(new Position(4, 2)));
	}

	@Test
	public void testGetPiece() {
		Piece p = new Piece(Color.NONE, false);
		g1.getBoard()[2][2].occupySpot(p);

		assertEquals(p, g1.getPiece(new Position(2, 2)));
	}

	@Test
	public void testGetTableCard() {
		assertNotNull(g1.getTableCard());
		assertNotNull(g2.getTableCard());
		assertNotNull(g3.getTableCard());
	}

	@Test
	public void testGetPlayers() {

	}

	// Test mudança no currentPlayer após uma jogada
	// Teste dos getters

	// Teste do makeMove

	// Teste do checkVictory
}