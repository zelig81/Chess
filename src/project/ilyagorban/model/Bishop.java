package project.ilyagorban.model;

// ♗♝figures
public class Bishop extends Figure {

	public Bishop(XY p, Rank r) {
		super(p, r);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkMoveCorrectness(XY from, XY to) {
		int stepsX = Math.abs(to.getX() - from.getX());
		int stepsY = Math.abs(to.getY() - from.getY());
		return stepsX == stepsY ? true : false;
	}

	@Override
	protected boolean checkNoFigureOnTheWay(Figure[][] board, XY to) {
		int stepsX = Math.abs(to.getX() - this.getXY().getX());
		int directionX = (to.getX() -  this.getXY().getX()) / stepsX;
		int directionY = (to.getY() - this.getXY().getY()) /stepsX;
		for (int i = 1; i < stepsX; i++){
			if (board[this.getXY().getX() + directionX * i][this.getXY().getY() + directionY * i] != null){
				return false;
			}
		}
		return true;
	}


}
