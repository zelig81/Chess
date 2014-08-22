package project.ilyagorban.model;

public enum Owner {
	WHITE, BLACK;
	public static Owner changeOwner(Owner o){
		return Owner.values()[(o.ordinal()+1)% 2];
	}
}
