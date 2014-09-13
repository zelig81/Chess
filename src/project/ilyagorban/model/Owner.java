package project.ilyagorban.model;

public enum Owner {
	WHITE(1), BLACK(-1);
	private int direction;
	
	private Owner(int direction) {
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public Owner oppositeOwner() {
		return Owner.values()[(this.ordinal() + 1) % 2];
	}
}
