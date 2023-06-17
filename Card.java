import java.util.Random;
/**
 * Classe que contém informações das cartas
 */
public class Card {
	private String name;
	private Color color; //TODO: perguntar se deveria ser final?
	private Position[] positions;
	
	public void copyCard(String name, Color color, Position[] positions) {
		this.name = name;
		this.color = color;
		this.positions = positions;
	}
	
    /**
     * Construtor que define os principais atributos de uma cara
     * @param name Nome da carta
     * @param color Cor da carta
     * @param positions Todas as posições relativas de movimento
     */
    public Card(String name, Color color, Position[] positions) {
    	this.name = name;
		this.color = color;
		this.positions = positions;
    }

    /**
     * Método que devolve o nome da carta
     * @return String que contém o nome da carta
     */
    public String getName() {
        return name;
    }

    /**
     * Método que devolve a cor da carta
     * @return Enum Color que contém a cor da carta
     */
    public Color getColor() {
        return color;
    }

    /**
     * Método que devolve todas as possíveis posições relativas de movimento.
     * A posição atual da peça é o ponto de origem (0,0). Uma carta possui as possíveis posições de movimento em relação ao ponto de origem.
     * @return Um array de Position contendo todas as possíveis posições de movimento em relação ao ponto de origem
     */
    public Position[] getPositions() {
        return positions;
    }

    /**
     * Método que cria todas as cartas do jogo, embaralha-as e devolve as 5 que serão utilizadas na partida.
     * @return Vetor de cartas com todas as cartas do jogo
     */
    public static Card[] createCards() {
    	Card[] allCards = {
    			new Card("Tiger", Color.BLUE, Movement.TIGER.getMovement()), 
    			new Card("Dragon", Color.RED, Movement.DRAGON.getMovement()), 
    			new Card("Frog", Color.RED, Movement.FROG.getMovement()), 
    			new Card("Rabbit", Color.BLUE, Movement.RABBIT.getMovement()),
    			new Card("Crab", Color.BLUE, Movement.CRAB.getMovement()),
    			new Card("Elephant", Color.RED, Movement.ELEPHANT.getMovement()),
    			new Card("Goose", Color.BLUE, Movement.GOOSE.getMovement()),
    			new Card("Rooster", Color.RED, Movement.ROOSTER.getMovement())
    	};
    	Random rand = new Random();
		Card[] gameCards = new Card[5];
		
		/*for(int i = 0; i < 5; i++) {
			int randomIndexToSwap = rand.nextInt(8);
			gameCards[i] = allCards[randomIndexToSwap];
		}*/
		
		for (int i = 0; i < 8; i++) {
			int randomIndexToSwap = rand.nextInt(8);
			Card temp = allCards[randomIndexToSwap];
			allCards[randomIndexToSwap] = allCards[i];
			allCards[i] = temp;
		}

		for(int i = 0; i < 5; i++) {
			gameCards[i] = allCards[i];
		}
		
		return gameCards;
    }
    
    // Recebe um deck e embaralha ele e retorna um vetor com 5 cartas para o jogo
    public static Card[] suffleDeck(Card[] deck) {
    	Random rand = new Random();
		Card[] gameCards = new Card[5];
		
		for (int i = 0; i < deck.length; i++) {
			int randomIndexToSwap = rand.nextInt(8);
			Card temp = deck[randomIndexToSwap];
			deck[randomIndexToSwap] = deck[i];
			deck[i] = temp;
		}

		for(int i = 0; i < 5; i++) {
			gameCards[i] = deck[i];
		}
		
		return gameCards;

    }
}
