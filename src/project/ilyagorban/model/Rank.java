package project.ilyagorban.model;

public enum Rank {
	PAWN(1), ROOK(3),KNIGHT(2),BISHOP(2),QUEEN(4), KING(100);
	
	private int importance;

	public int getImportance() {
		return importance;
	}

	private Rank(int importance) {
		this.importance = importance;
	}

}
