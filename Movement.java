/**
 * Enum contendo todos os movimentos das cartas
 */

//TODO: Documentar esse enum
enum Movement {
	TIGER,
	DRAGON,
	FROG,
	RABBIT,
	CRAB,
	ELEPHANT,
	GOOSE,
	ROOSTER;
	
	// [row (linha)][col (coluna)]
	
	public Position[] getMovement() {
		switch(this) {
			case TIGER:
				Position[] cardMovementsTiger = {new Position(-2, 0), new Position(1, 0)};
				return cardMovementsTiger;
			case DRAGON:
				Position[] cardMovementsDragon = {new Position(-1, 2), new Position(1, 1), new Position(1, 1), new Position(-2, -1)};
				return cardMovementsDragon;
			case FROG:
				Position[] cardMovementsFrog = {new Position(-1, 1), new Position(0, 2), new Position(1, -1)};
				return cardMovementsFrog;
			case RABBIT:
				Position[] cardMovementsRabbit = {new Position(1, 1), new Position(-1, -1), new Position(0, -2)};
				return cardMovementsRabbit;
			case CRAB:
				Position[] cardMovementsCrab = {new Position(0, 2), new Position(-1, 0), new Position(0, -2)};
				return cardMovementsCrab;
			case ELEPHANT:
				Position[] cardMovementsElephant = {new Position(0, 1), new Position(-1, 1), new Position(0, -1), new Position(-1, -1)};
				return cardMovementsElephant;
			case GOOSE:
				Position[] cardMovementsGoose = {new Position(0, 1), new Position(-1, 1), new Position(0, -1), new Position(1, -1)};
				return cardMovementsGoose;
			case ROOSTER:
				Position[] cardMovementsRooster = {new Position(0, 1), new Position(1, 1), new Position(-1, 0), new Position(-1, -1)};
				return cardMovementsRooster;
			default:
				return null;
		}
	}
}