public class Main {
	public static void main(String[] args) {
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
		jogo1.printBoard();*/
		Position[] pos1 = { new Position(1,0), new Position(0,1) };
		Position[] pos2 = { new Position(2,0), new Position(0,2) };
		Card c1 = new Card("Carta 1", Color.BLUE, pos1);
		Card c2 = new Card("Carta 2", Color.RED, pos2);
		Player p1 = new Player("p1", Color.BLUE, c1, c2);
		Player p2 = new Player("p2", Color.RED, c1, c2);
		System.out.println("Before cards: " + p1.getCards()[0].getName() + " " + p1.getCards()[1].getName());
		System.out.println(p1.getCards()[0].getColor() + " " + p1.getCards()[1].getColor());
		p1.swapCard(p1.getCards()[0], p1.getCards()[1]);
		System.out.println("After cards: " + p1.getCards()[0].getName() + " " + p1.getCards()[1].getName());
		System.out.println(p1.getCards()[0].getColor() + " " + p1.getCards()[1].getColor());
	}
}
