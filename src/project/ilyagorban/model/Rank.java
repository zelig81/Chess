package project.ilyagorban.model;

public enum Rank {
	WHITE_KING(100, Owner.WHITE, "♔"),
	WHITE_QUEEN(4, Owner.WHITE, "♕"), 
	WHITE_ROOK(3, Owner.WHITE, "♖"),
	WHITE_BISHOP(2, Owner.WHITE, "♗"),
	WHITE_KNIGHT(2, Owner.WHITE, "♘"),
	WHITE_PAWN(1, Owner.WHITE, "♙"),
	BLACK_KING(100, Owner.BLACK, "♚"),
	BLACK_QUEEN(4, Owner.BLACK, "♛"), 
	BLACK_ROOK(3, Owner.BLACK, "♜"),
	BLACK_BISHOP(2, Owner.BLACK, "♝"),
	BLACK_KNIGHT(2, Owner.BLACK, "♞"),
	BLACK_PAWN(1, Owner.BLACK , "♟");
	//♔♕♖♗♘♙♚♛♜♝♞♟
	
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
	
	public String toString(){
		return picture;
	}
}
