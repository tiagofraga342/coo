public class Main {
	public static void main(String[] args) {
		Card[] gameCards = Card.createCards();
		Player tiago = new Player("Tiago", Color.RED, gameCards[0], gameCards[1]);
		System.out.println("Old tiago cards: " + tiago.getCards()[0].getName() + " " + tiago.getCards()[1].getName());
		tiago.swapCard(tiago.getCards()[0], gameCards[2]);
		System.out.println("New tiago cards: " + tiago.getCards()[0].getName() + " " + tiago.getCards()[1].getName());
	}
}
