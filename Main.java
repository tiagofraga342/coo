public class Main {
	public static void main(String[] args) {
		GameImpl jogo1 = new GameImpl("Tiago", "sla");
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
		jogo1.makeMove(jogo1.getCurrentPlayer().getCards()[1], jogo1.getCurrentPlayer().getCards()[1].getPositions()[0], new Position(0, 1));
		jogo1.printBoard();
	}
}
