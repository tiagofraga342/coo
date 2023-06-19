import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestGameImpl {
	GameImpl g1;
	GameImpl g2;

	@Before
	public void setupGame() {
		g1 = new GameImpl();
		if(g1 == null) 
			fail("Falha na criação do objeto!");
		g2 = new GameImpl("Tiago Vermelho", "Tiago Azul");
		if(g2 == null) 
			fail("Falha na criação do objeto!");
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
	}

	@Test
	public void testGetPlayers() {
		assertEquals("Jogador Azul", g1.getBluePlayer().getName());
		assertEquals("Jogador Vermelho", g1.getRedPlayer().getName());
		assertEquals(Color.BLUE, g1.getBluePlayer().getPieceColor());
		assertEquals(Color.RED, g1.getRedPlayer().getPieceColor());

		Card t1 = g1.getTableCard();
		assertEquals(t1.getColor(), g1.getCurrentPlayer().getPieceColor());
	
		assertEquals("Tiago Azul", g2.getBluePlayer().getName());
		assertEquals("Tiago Vermelho", g2.getRedPlayer().getName());
		assertEquals(Color.BLUE, g2.getBluePlayer().getPieceColor());
		assertEquals(Color.RED, g2.getRedPlayer().getPieceColor());

		Card t2 = g2.getTableCard();
		assertEquals(t2.getColor(), g2.getCurrentPlayer().getPieceColor());
	}

	@Test(expected = IllegalMovementException.class)
	public void testMakeMoveOutsideTheBoard() {
		Position[] p1 = { new Position(5, 5) };
		Card c1 = new Card("Carta que pula para fora do tabuleiro", g1.getCurrentPlayer().getPieceColor(), p1);
		if(g1.getCurrentPlayer().getPieceColor() == Color.RED)
			g1.makeMove(c1, p1[0], new Position(4, 0));
		else
			g1.makeMove(c1, p1[0], new Position(0, 0));
	}

	@Test(expected = IncorrectTurnOrderException.class)
	public void testMakeMoveIncorrectTurn() {
		Color currentPlayerColor = g1.getCurrentPlayer().getPieceColor();
		Color currentPlayerWrongColor;
		if(currentPlayerColor == Color.BLUE){
			currentPlayerWrongColor = Color.RED;
			Position[] p1 = { new Position(-1, 1) };
			Card c1 = new Card("Carta comum", currentPlayerWrongColor, p1);
			g1.makeMove(c1, p1[0], new Position(4, 0));
		}
		else{
			currentPlayerWrongColor = Color.BLUE;
			Position[] p1 = { new Position(1, 1) };
			Card c1 = new Card("Carta comum", currentPlayerWrongColor, p1);
			g1.makeMove(c1, p1[0], new Position(0, 0));			
		}
	}	

	@Test(expected = IllegalMovementException.class)
	public void testMakeMoveSameColorPiece() {
		Position[] p1 = { new Position(0, 1) };
		Card c1 = new Card("Carta que pula para cima de uma peça da mesma cor", g1.getCurrentPlayer().getPieceColor(), p1);
		if(g1.getCurrentPlayer().getPieceColor() == Color.RED)
			g1.makeMove(c1, p1[0], new Position(4, 0));
		else
			g1.makeMove(c1, p1[0], new Position(0, 0));
		
	}

	@Test(expected = InvalidPieceException.class)
	public void testeMakeMoveInvalidPiece() {
		Position[] p1 = { new Position(0, 1) };
		Card c1 = new Card("Carta que pula para cima de uma peça da mesma cor", g1.getCurrentPlayer().getPieceColor(), p1);
		g1.makeMove(c1, p1[0], new Position(2, 2));
	}

	@Test(expected = InvalidCardException.class)
	public void testMakeMoveWrongCard() {
		Position[] p1 = { new Position(1, 1) };
		Position[] p2 = { new Position(-1, 1) };
		Card c1 = new Card("Carta que não está na mão do jogador", g1.getCurrentPlayer().getPieceColor(), p1);
		if(g1.getCurrentPlayer().getPieceColor() == Color.RED)
			g1.makeMove(c1, p2[0], new Position(4, 1));
		else
			g1.makeMove(c1, p1[0], new Position(0, 1));
	}

	@Test
	public void testMakeMoveChangeCurrentPlayer() {
		Position[] p1 = { new Position(1, 1) };
		Position[] p2 = { new Position(-1, 1) };
		Card c1 = g1.getCurrentPlayer().getCards()[0];
		if(g1.getCurrentPlayer().getPieceColor() == Color.RED){
			g1.makeMove(c1, p2[0], new Position(4, 0));
			assertEquals(g1.getCurrentPlayer().getPieceColor(), Color.BLUE);
		}
		else {
			g1.makeMove(c1, p1[0], new Position(0, 0));
			assertEquals(g1.getCurrentPlayer().getPieceColor(), Color.RED);
		}
	}

	@Test
	public void testCheckVictoryByDefeat() {
		g1.getBoard()[0][2].releaseSpot();
		assertTrue(g1.checkVictory(Color.RED));

		g1.getBoard()[4][2].releaseSpot();
		assertTrue(g1.checkVictory(Color.BLUE));
	}

	@Test
	public void testCheckVictoryByTempleTakeOver() {
		Position[] p1 = { new Position(1, 1) };
		g1.setCurrentPlayer(Color.BLUE);
		Card c1 = g1.getCurrentPlayer().getCards()[0];
		
		g1.makeMove(c1, p1[0], new Position(0, 2));

		Position[] p2 = { new Position(-4, 0) };
		Card c2 = g1.getCurrentPlayer().getCards()[0];
		g1.makeMove(c2, p2[0], new Position(4, 2));

		assertTrue(g1.checkVictory(Color.RED));
	}
}