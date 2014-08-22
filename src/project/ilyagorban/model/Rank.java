package project.ilyagorban.model;

public enum Rank {
	WHITE_KING(100, Owner.WHITE, "\u2654"),
	WHITE_QUEEN(4, Owner.WHITE, "\u2655"), 
	WHITE_ROOK(3, Owner.WHITE, "\u2656"),
	WHITE_BISHOP(2, Owner.WHITE, "\u2657"),
	WHITE_KNIGHT(2, Owner.WHITE, "\u2658"),
	WHITE_PAWN(1, Owner.WHITE, "\u2659"),
	BLACK_KING(100, Owner.WHITE, "\u265A"),
	BLACK_QUEEN(4, Owner.WHITE, "\u265B"), 
	BLACK_ROOK(3, Owner.WHITE, "\u265C"),
	BLACK_BISHOP(2, Owner.WHITE, "\u265D"),
	BLACK_KNIGHT(2, Owner.WHITE, "\u265E"),
	BLACK_PAWN(1, Owner.BLACK , "\u265F");
	
	private Rank(int i, Owner o, String c){
		this.importance = i;
		this.owner = o;
		this.picture = c;
	}
	
	
	private int importance;
	private String picture;
	private Owner owner;

	public int getImportance() {
		return importance;
	}

	public String getPicture(){
		return picture;
	}
	
	public Owner getOwner(){
		return owner;
	}
}
