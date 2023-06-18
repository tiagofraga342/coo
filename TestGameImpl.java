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
	}

	@Test
	public void testGetPlayers() {
		// Para g1
		assertEquals("Jogador Azul", g1.getBluePlayer().getName());
		assertEquals("Jogador Vermelho", g1.getRedPlayer().getName());
		assertEquals(Color.BLUE, g1.getBluePlayer().getPieceColor());
		assertEquals(Color.RED, g1.getRedPlayer().getPieceColor());
		// pega a table card
		// confere qual jogador deveria ser o primeiro e se ele é o currentPlayer
		Card t1 = g1.getTableCard();
		assertEquals(t1.getColor(), g1.getCurrentPlayer().getPieceColor());
	
		// Para g2
		assertEquals("Tiago Azul", g2.getBluePlayer().getName());
		assertEquals("Tiago Vermelho", g2.getRedPlayer().getName());
		assertEquals(Color.BLUE, g2.getBluePlayer().getPieceColor());
		assertEquals(Color.RED, g2.getRedPlayer().getPieceColor());
		// pega a table card
		// confere qual jogador deveria ser o primeiro e se ele é o currentPlayer
		Card t2 = g2.getTableCard();
		assertEquals(t2.getColor(), g2.getCurrentPlayer().getPieceColor());
	}

	// Teste do makeMove

	// Teste que tenta fazer um movimento para fora do tabuleiro
	@Test(expected = IllegalMovementException.class)
	public void testMakeMoveOutsideTheBoard() {
		Position[] p1 = { new Position(5, 5) };
		Card c1 = new Card("Carta que pula para fora do tabuleiro", g1.getCurrentPlayer().getPieceColor(), p1);
		if(g1.getCurrentPlayer().getPieceColor() == Color.RED)
			g1.makeMove(c1, p1[0], new Position(4, 0));
		else
			g1.makeMove(c1, p1[0], new Position(0, 0));
	}

	// Teste que tenta fazer um movimento sendo o player azul no turno do player vermelho
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

	// Teste que tenta fazer um movimento que incide numa peça da mesma cor
	@Test(expected = IllegalMovementException.class)
	public void testMakeMoveSameColorPiece() {
		Position[] p1 = { new Position(0, 1) };
		Card c1 = new Card("Carta que pula para cima de uma peça da mesma cor", g1.getCurrentPlayer().getPieceColor(), p1);
		if(g1.getCurrentPlayer().getPieceColor() == Color.RED)
			g1.makeMove(c1, p1[0], new Position(4, 0));
		else
			g1.makeMove(c1, p1[0], new Position(0, 0));
		
	}

	// Teste que tenta usar uma peça que não exista
	@Test(expected = InvalidPieceException.class)
	public void testeMakeMoveInvalidPiece() {
		Position[] p1 = { new Position(0, 1) };
		Card c1 = new Card("Carta que pula para cima de uma peça da mesma cor", g1.getCurrentPlayer().getPieceColor(), p1);
		g1.makeMove(c1, p1[0], new Position(2, 2));
	}

	// Teste que tenta usar uma carta que não esteja na mão do jogador
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

	// Teste que verifica se a carta da mesa foi trocada com a carta da mão

	// Teste se o currentPlayer foi alterado após um movimento
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

	// Teste do checkVictory
	@Test
	public void testCheckVictoryByDefeat() {
		// Retira o mestre azul
		g1.getBoard()[0][2].releaseSpot();
		assertTrue(g1.checkVictory(Color.RED));

		// Retira o mestre vermelho
		g1.getBoard()[4][2].releaseSpot();
		assertTrue(g1.checkVictory(Color.BLUE));
	}

	@Test
	public void testCheckVictoryByTemple() {
		Position[] p1 = { new Position(1, 1) };
		g1.setCurrentPlayer(Color.BLUE);
		Card c1 = g1.getCurrentPlayer().getCards()[0];
		
		// Move o mestre azul para fora do templo
		g1.makeMove(c1, p1[0], new Position(0, 2));

		Position[] p2 = { new Position(-4, 0) };
		Card c2 = g1.getCurrentPlayer().getCards()[0];
		// Move o mestre vermelho para dentro do templo azul
		g1.makeMove(c2, p2[0], new Position(4, 2));

		assertTrue(g1.checkVictory(Color.RED));
	}
}