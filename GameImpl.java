public class GameImpl implements Game {
	private Spot[][] board = new Spot[BOARD_SIZE][BOARD_SIZE];
	private Card[] gameDeck;
	private Card tableCard;
	private Player redPlayer;
	private Player bluePlayer;
	private Player currentPlayer;
	public final static int BOARD_SIZE = 5;
	
	/**
	 * Método que retorna o tabuleiro atual do jogo
	 * @return Matriz de objetos Spot 
	 */
	public Spot[][] getBoard() {
		return board;
	}

	/**
	 * Método que determina o jogador atual
	 * @param color Cor do jogador atual
	 */
	public void setCurrentPlayer(Color color) {
		if(color == Color.RED)
			currentPlayer = redPlayer;
		else
			currentPlayer = bluePlayer;
	}

	/**
	 * Método que verifica se um movimento é inválido (ultrapassa os limites do tabuleiro)
	 * @param row Inteiro com a coordenada da linha do movimento
	 * @param col Inteiro com coordenada com coluna do movimento
	 * @return Booleano true caso o movimento seja inválido e false caso o movimento seja válido
	 */
	private boolean isNotValidPosition(int row, int col) {
		return col > 4 || col < 0 || row > 4 || row < 0;
    }
	
	/**
	 * Método que atualiza o jogador atual
	 */
	private void updatePlayer() {
		if(currentPlayer == bluePlayer) currentPlayer = redPlayer;
		else currentPlayer = bluePlayer;
	}

	/**
	 * Método que devolve o primeiro jogador a jogar baseado na cor da carta da mesa
	 * @return Um Objeto do tipo Player que será o jogador que fará a primeira jogada
	 */
	private Player setFirstPlayer() {
		if(tableCard.getColor() == Color.BLUE) return bluePlayer;
		return redPlayer;
	}
	
	/**
	 * Construtor que define as informações básicas para iniciar uma partida
	 */
	public GameImpl() {
		board = buildBoard();
		gameDeck = Card.createCards();
		redPlayer = new Player("Jogador Vermelho", Color.RED, gameDeck[0], gameDeck[1]);
		bluePlayer = new Player("Jogador Azul", Color.BLUE, gameDeck[2], gameDeck[3]);
		tableCard = gameDeck[4];
		currentPlayer = setFirstPlayer();
	}

	/**
	 * Construtor que define as informações básicas para iniciar uma partida
	 * @param redPlayerName String com o nome do jogador vermelho
	 * @param bluePlayerName String com o nome do jogador azul
	 */
	public GameImpl(String redPlayerName, String bluePlayerName) {
		board = buildBoard();
		gameDeck = Card.createCards();
		redPlayer = new Player(redPlayerName, Color.RED, gameDeck[0], gameDeck[1]);
		bluePlayer = new Player(bluePlayerName, Color.BLUE, gameDeck[2], gameDeck[3]);
		tableCard = gameDeck[4];
		currentPlayer = setFirstPlayer();
	}

	/**
	 * Construtor que define as informações básicas para iniciar uma partida
	 * @param redPlayerName String com o nome do jogador vermelho
	 * @param bluePlayerName String com o nome do jogador azul
	 * @param deck Vetor de cartas
	 */
	public GameImpl(String redPlayerName, String bluePlayerName, Card[] deck) {
		board = buildBoard();
		Card.shuffleDeck(deck);
		redPlayer = new Player(redPlayerName, Color.RED, deck[0], deck[1]);
		bluePlayer = new Player(bluePlayerName, Color.BLUE, deck[2], deck[3]);
		tableCard = gameDeck[4];
		currentPlayer = setFirstPlayer();
	}
	
	/**
	 * Método que constrói o tabuleiro da partida
	 * @return Matriz de spot que representa o tabuleiro
	 */
	private Spot[][] buildBoard() {
		for(int i = 1; i < BOARD_SIZE - 1; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = new Spot(new Position(i, j));
			}
		}

		for(int i = 0; i < BOARD_SIZE; i++) {
			if(i == 2) {
				board[0][i] = new Spot(new Piece(Color.BLUE, true), new Position(0, i), Color.BLUE);
				board[4][i] = new Spot(new Piece(Color.RED, true), new Position(4, i), Color.RED);
			}
			else {
				board[0][i] = new Spot(new Piece(Color.BLUE, false), new Position(0, i));
				board[4][i] = new Spot(new Piece(Color.RED, false), new Position(4, i));
			}
		}
		
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
	 * Método que devolve as informações sobre o jogador atual
	 * @return Um objeto Player que representa o jogador atual
	 */
    public Player getCurrentPlayer() {
    	return currentPlayer;
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
    	int cardMoveCol = cardMove.getCol();
    	int cardMoveRow = cardMove.getRow();
    	int currentPosCol = currentPos.getCol();
    	int currentPosRow = currentPos.getRow();
    	
    	// Verifica se o movimento da peça não ultrapassa o tabuleiro
    	if(isNotValidPosition(currentPosRow + cardMoveRow, currentPosCol + cardMoveCol))
    		throw new IllegalMovementException("Movimento inválido que ultrapassa os limites do tabuleiro");
    	if(isNotValidPosition(currentPosRow, currentPosCol)) 
    		throw new InvalidPieceException("Essa peça não está no tabuleiro");
		
		// Vê se na posição atual existe uma peça e vê se a posição atual está dentro do tabuleiro
    	if(board[currentPosRow][currentPosCol].getPiece() == null)
    		throw new InvalidPieceException("Não existe uma peça nessa posição");

    	// Verifica se é o jogador correto no turno
    	if(board[currentPosRow][currentPosCol].getPiece().getColor() != currentPlayer.getPieceColor())
    		throw new IncorrectTurnOrderException("Este não é o turno correto para esse jogador");

    	// Verifica se o movimento da peça não incide em outra peça da mesma cor
    	if(board[currentPosRow + cardMoveRow][currentPosCol + cardMoveCol].getPiece() != null)
    		if(board[currentPosRow + cardMoveRow][currentPosCol + cardMoveCol].getPiece().getColor() == board[currentPosRow][currentPosCol].getPiece().getColor())
    			throw new IllegalMovementException("Movimento inválido que incide numa peça de mesma cor");
		
    	// Verifica se a carta usada está na mão do jogador atual
    	if(!currentPlayer.cardIsOnPlayerHand(card))
    		throw new InvalidCardException("Esta carta não está na mão do jogador atual");
    	
    	board[currentPosRow + cardMoveRow][currentPosCol + cardMoveCol].occupySpot(board[currentPosRow][currentPosCol].getPiece());
    	board[currentPosRow][currentPosCol].releaseSpot(); 
    	currentPlayer.swapCard(card, tableCard);
    	updatePlayer();
    }

    /**
     * Método que confere se um jogador de uma determinada cor venceu o jogo. Critérios de vitória:
     * — Derrotou a peça de mestre adversária
     * — Posicionou o seu mestre na posição da base adversária
     * @param color Cor das peças do jogador que confere a condição de vitória
     * @return Um booleano true para caso esteja em condições de vencer e false caso contrário
     */
    public boolean checkVictory(Color color) {
    	boolean thereIsRedMaster = false;
		boolean thereIsBlueMaster = false;
    	int redMasterRow = -1;
    	int redMasterCol = -1;
    	int blueMasterRow = -1;
    	int blueMasterCol = -1;

    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++) {
    			if(board[i][j].getPiece() != null && board[i][j].getPiece().isMaster()) {
    				if(board[i][j].getColor() == Color.RED) {
    					thereIsRedMaster = true;
    					redMasterRow = i;
    					redMasterCol = j;
    				}
    				else if(board[i][j].getColor() == Color.BLUE) {
    					thereIsBlueMaster = true;
    					blueMasterRow = i;
    					blueMasterCol = j;
    				}
    			}
    		}
    	}

    	// Se não existe o mestre da cor no tabuleiro
    	if(!thereIsBlueMaster && color == Color.RED) return true;
    	if(!thereIsRedMaster && color == Color.BLUE) return true;
    	
    	// Se a posição do mestre dessa cor está no templo da cor adversária
    	if(blueMasterRow == 4 && blueMasterCol == 2 && color == Color.BLUE)
    		return true;
    	else if(redMasterRow == 0 && redMasterCol == 2 && color == Color.RED)
    		return true;

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
