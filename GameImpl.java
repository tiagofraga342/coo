public class GameImpl implements Game {
	private Spot[][] board = new Spot[BOARD_SIZE][BOARD_SIZE];
	private Card[] gameDeck;
	private Card tableCard;
	private Player redPlayer;
	private Player bluePlayer;
	private Player currentPlayer;
	public final static int BOARD_SIZE = 5;
	
	public Spot[][] getBoard() {
		return board;
	}
	public void setCurrentPlayer(Color color) {
		if(color == Color.RED)
			currentPlayer = redPlayer;
		else
			currentPlayer = bluePlayer;
	}
	// Método para validar movimento
	public boolean isNotValidMovement(int row, int col) {
		return col > 4 || col < 0 || row > 4 || row < 0;
    }
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
		Card.suffleDeck(deck);
		redPlayer = new Player(redPlayerName, Color.RED, deck[0], deck[1]);
		bluePlayer = new Player(bluePlayerName, Color.BLUE, deck[2], deck[3]);
		tableCard = gameDeck[4];
		currentPlayer = setFirstPlayer();
	}
	
	private Spot[][] buildBoard() {
		// Meio do tabuleiro
		for(int i = 1; i < BOARD_SIZE - 1; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = new Spot(new Position(i, j));
			}
		}
		// Parte colorida
		for(int i = 0; i < BOARD_SIZE; i++) {
			// Templo
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
    	// Pega as colunas e linhas das posições
    	int cardMoveCol = cardMove.getCol();
    	int cardMoveRow = cardMove.getRow();
    	int currentPosCol = currentPos.getCol();
    	int currentPosRow = currentPos.getRow();
    	
    	// Verifica se o movimento da peça não ultrapassa o tabuleiro
    	if(isNotValidMovement(currentPosRow + cardMoveRow, currentPosCol + cardMoveCol))
    		throw new IllegalMovementException("ERRO: Movimento inválido que ultrapassa os limites do tabuleiro");
    	// Vê se na posição atual existe uma peça e vê se a posição atual está dentro do tabuleiro
    	if(board[currentPosRow][currentPosCol].getPiece() == null)
    		throw new InvalidPieceException("ERRO: Não existe uma peça nessa posição");
    	// Verifica se tenta usar uma peça que não está no tabuleiro
    	if(currentPosRow > 4 || currentPosCol > 4) 
    		throw new InvalidPieceException("ERRO: Essa peça não está no tabuleiro");
    	// Verifica se é o jogador correto no turno
    	if(board[currentPosRow][currentPosCol].getPiece().getColor() != currentPlayer.getPieceColor())
    		throw new IncorrectTurnOrderException("ERRO: este não é o turno correto para esse jogador");
    	// Verifica se o movimento da peça não incide em outra peça da mesma cor
    	if(board[currentPosRow + cardMoveRow][currentPosCol + cardMoveCol].getPiece() != null)
    		if(board[currentPosRow + cardMoveRow][currentPosCol + cardMoveCol].getPiece().getColor() == board[currentPosRow][currentPosCol].getPiece().getColor())
    			throw new IllegalMovementException("ERRO: Movimento inválido que incide numa peça de mesma cor");
    	// Verifica se a carta usada está na mão do jogador atual
    	if(!currentPlayer.isOnHand(card))
    		throw new InvalidCardException("ERRO: Esta carta não está na mão do jogador atual");
    	
    	// Coloca a peça que estava na posição antiga na posição nova e atualiza a cor
    	board[currentPosRow + cardMoveRow][currentPosCol + cardMoveCol].occupySpot(board[currentPosRow][currentPosCol].getPiece());
    	// Libera a posição antiga e atualiza a cor
    	board[currentPosRow][currentPosCol].releaseSpot(); 
    	// Troca a carta do jogador atual com a da mesa
    	currentPlayer.swapCard(card, tableCard);
    	// Atualiza o jogador atual
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
    	// Busca posição do mestre adversário e do mestre da cor atual
    	boolean thereIsRedMaster = false;
    	int redMasterRow = -1;
    	int redMasterCol = -1;
    	boolean thereIsBlueMaster = false;
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
