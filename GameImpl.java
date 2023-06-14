public class GameImpl implements Game {
	private Spot[][] board = new Spot[BOARD_SIZE][BOARD_SIZE];
	private Card[] gameDeck;
	private Card tableCard;
	private Player redPlayer;
	private Player bluePlayer;
	private Player currentPlayer;
	private final static int BOARD_SIZE = 5;
	
	//TODO: Método para validar movimento
	// Método para atualizar a vez do jogador
	private void updatePlayer() {
		if(currentPlayer == bluePlayer) currentPlayer = redPlayer;
		else currentPlayer = bluePlayer;
	}
	// Método para determinar a tableCard e o primeiro a jogar
	private Player setFirstPlayer() {
		if(tableCard.getColor() == Color.BLUE) return bluePlayer;
		return redPlayer;
	}
	// Método que verifica se tenta usar uma carta que não esteja na mão do jogador da vez
	private boolean verifyIfPlayerDeckContainsCard(Card card) {
		Card[] playerDeck = currentPlayer.getCards();
		for(int i = 0; i < 2; i++) {
			if(playerDeck[i] == card);
		}
		return false;
	}
	// Método que verifica se é o turno correto do jogador a fazer a jogada
	private boolean verifyPlayerTurn(Player player) {
		if(player == currentPlayer) return true;
		return false;
	}
	//TODO: Método que verifica se uma peça que não está no tabuleiro tente ser usada
	public GameImpl() {
		board = buildBoard();
		gameDeck = Card.createCards();
		redPlayer = new Player("Jogador Vermelho", Color.RED, gameDeck[0], gameDeck[1]);
		bluePlayer = new Player("Jogador Azul", Color.BLUE, gameDeck[2], gameDeck[3]);
		tableCard = gameDeck[4];
		currentPlayer = setFirstPlayer();
	}
	public GameImpl(String redPlayerName, String bluePlayerName) {
		board = buildBoard();
		gameDeck = Card.createCards();
		redPlayer = new Player(redPlayerName, Color.RED, gameDeck[0], gameDeck[1]);
		bluePlayer = new Player(bluePlayerName, Color.BLUE, gameDeck[2], gameDeck[3]);
		tableCard = gameDeck[4];
		currentPlayer = setFirstPlayer();
	}
	public GameImpl(String redPlayerName, String bluePlayerName, Card[] deck) {
		board = buildBoard();
		// Embaralha o deck
		redPlayer = new Player(redPlayerName, Color.RED, deck[0], deck[1]);
		bluePlayer = new Player(bluePlayerName, Color.BLUE, deck[2], deck[3]);
		tableCard = gameDeck[4];
		setFirstPlayer();
	}
	
	private Spot[][] buildBoard() {
		// Meio do tabuleiro
		for(int i = 1; i < BOARD_SIZE - 1; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = new Spot(new Position(i, j));
			}
		}
		// Parte de cima (azul)
		for(int i = 0; i < BOARD_SIZE; i++)
			board[0][i] = new Spot(new Piece(Color.BLUE, false), new Position(0, i), Color.BLUE);
		
		// Parte de baixo (vermelho)
		for(int i = 0; i < BOARD_SIZE; i++)
			board[4][i] = new Spot(new Piece(Color.RED, false), new Position(4, i), Color.RED);
		
		return board;
	}
	
	/**
     * Método que devolve a cor da posição do tabuleiro. Se possui uma cor, significa que é um templo. Caso contrário, é um espaço normal
     * @param position Posição do tabuleiro
     * @return O enum Color que representa a cor da posição
     */
    public Color getSpotColor(Position position) {
    	int row = position.getRow();
    	int col = position.getCol();
    	return board[row][col].getColor();
    }

    /**
     * Método que devolve a peça que está na posição do tabuleiro
     * @param position Posição do tabuleiro
     * @return Um objeto Piece que representa a peça na posição indicada. Se não tiver peça, devolve null
     */
    public Piece getPiece(Position position) {
    	int row = position.getRow();
    	int col = position.getCol();
    	return board[row][col].getPiece();
    }

    /**
     * Método que devolve a carta que está na mesa, que será substituída após a próxima jogada
     * @return Um objeto Card que representa a carta na mesa
     */
    public Card getTableCard() {
    	return tableCard;
    }

    /**
     * Método que devolve as informações sobre o jogador com as peças vermelhas
     * @return Um objeto Player que representa o jogador vermelho
     */
    public Player getRedPlayer() {
    	return redPlayer;
    }

    /**
     * Método que devolve as informações sobre o jogador com as peças azuis
     * @return Um objeto Player que representa o jogador azul
     */
    public Player getBluePlayer() {
    	return bluePlayer;
    }

    /**
     * Método que move uma peça
     * @param card A carta de movimento que será usada
     * @param cardMove A posição da carta para onde a peça irá se mover
     * @param currentPos A posição da peça que irá se mover
     * @exception IncorrectTurnOrderException Caso não seja a vez de um jogador fazer um movimento
     * @exception IllegalMovementException Caso uma peça seja movida para fora do tabuleiro ou para uma posição onde já tem uma peça da mesma cor
     * @exception InvalidCardException Caso uma carta que não está na mão do jogador seja usada
     * @exception InvalidPieceException Caso uma peça que não está no tabuleiro seja usada
     */
    public void makeMove(Card card, Position cardMove, Position currentPos) throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException {
    	
    }

    /**
     * Método que confere se um jogador de uma determinada cor venceu o jogo. Critérios de vitória:
     * — Derrotou a peça de mestre adversária
     * — Posicionou o seu mestre na posição da base adversária
     * @param color Cor das peças do jogador que confere a condição de vitória
     * @return Um booleano true para caso esteja em condições de vencer e false caso contrário
     */
    public boolean checkVictory(Color color) {
    	return false;
    }

    /**
     * Método que imprime o tabuleiro no seu estado atual
     * OBS: Esse método é opcional não será utilizado na correção, mas serve para acompanhar os resultados parciais do jogo
     */
    public void printBoard() {
    	for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				System.out.printf("%s ", board[i][j].getColor());
			}
			System.out.printf("%n");
		}
    }
}
