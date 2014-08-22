package project.ilyagorban.model;

public class Pawn extends Figure {
	
	public Pawn(int x, int y, Rank r) {
		super(x, y, r);
	}

	public void setRank(Rank rank){
		super.rank = rank;
		//TODO add check owner
	}


	@Override
	public void move() {
		// TODO Auto-generated method stub

	}


	@Override
	public void removeFigure() {
		// TODO Auto-generated method stub
		
	}

}
