import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestSpot {
	private static final int BOARD_SIZE = 5;
	Spot[][] board = new Spot[BOARD_SIZE][BOARD_SIZE];

	@Before
	public void buildBoardSetup() {
		// Meio do tabuleiro
		for(int i = 1; i < BOARD_SIZE - 1; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = new Spot(new Position(i, j));
				
				if(board[i][j] == null)
					fail("Falha na criação do objeto!");
			}
		}
		// Parte de colorida
		for(int i = 0; i < BOARD_SIZE; i++) {
			// Templo
			if(i == 2){
				board[0][i] = new Spot(new Piece(Color.BLUE, true), new Position(0, i), Color.BLUE);
				board[4][i] = new Spot(new Piece(Color.RED, true), new Position(4, i), Color.RED);
			}
			else {
				board[0][i] = new Spot(new Piece(Color.BLUE, false), new Position(0, i));
				board[4][i] = new Spot(new Piece(Color.RED, false), new Position(4, i));
			}
			
			if(board[0][i] == null && board[4][i] == null)
				fail("Falha na criação do objeto!");
		}
	}

	// Teste dos métodos getters
	@Test
	/*public void testGetPiece() {
		for(int i = 0; i < BOARD_SIZE; i++) {
			assertTrue(board[4][i].getPiece().getColor().equals(Color.RED));
			assertTrue(board[0][i].getPiece().getColor().equals(Color.BLUE));
		}
	}*/
	public void testGetPiece() {
		for(int i = 0; i < BOARD_SIZE; i++) {
			assertEquals(board[0][i].getPiece().getColor(), Color.BLUE);
			assertEquals(board[4][i].getPiece().getColor(), Color.RED);
		}
	}

	@Test
	public void testGetPosition() {
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				assertTrue(board[i][j].getPosition().getRow() == i && board[i][j].getPosition().getCol() == j);
			}
		}
	}

	@Test
	public void testTempleSpot() {
		// Teste se o templo está 
		// na posição correta e com a cor correta e com o mestre
		assertTrue(board[0][2].getColor().equals(Color.BLUE) && board[0][2].getPiece().isMaster());
		assertTrue(board[4][2].getColor().equals(Color.RED) && board[4][2].getPiece().isMaster());
 	}
	
	// Teste do occupySpot
 	@Test
 	public void testOccupySpot() {
 		// Tenta ocupar um Spot válido
 		Piece p = board[4][0].getPiece();
 		board[2][2].occupySpot(board[4][0].getPiece());
 		
 		assertEquals(p, board[2][2].getPiece()); 
 	}
 	@Test(expected = IllegalMovementException.class)
 	public void testOccupySpotException() {
 		board[0][1].occupySpot(board[0][0].getPiece());
 	}

	// Teste do releaseSpot
	@Test
	public void testReleaseSpot() {
		// Verifica se o Spot liberado realmente foi liberado
		board[1][1].occupySpot(board[0][0].getPiece());
		board[1][1].releaseSpot();
		assertNull(board[1][1].getPiece());
	}
}