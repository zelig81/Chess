package project.ilyagorban.model;

// ♜♖
public class Rook extends Figure {

	public Rook(XY p, Rank r) {
		super(p, r);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean checkMoveCorrectness(XY from, XY to) {
		return (Math.abs(to.getX() - from.getX()) + Math.abs(to.getY() - from.getY()) > 0) && (Math.abs(to.getX() - from.getX()) == 0 || Math.abs(to.getY() - from.getY()) == 0);
	}

	@Override
	protected boolean checkNoFigureOnTheWay(Figure[][] board, XY to) {
		if (Math.abs(to.getX() - this.getXY().getX()) == 0){
			int direction = to.getY() - this.getXY().getY();
			int step = direction / Math.abs(direction);
			for (int i = 1; i < Math.abs(direction); i++){
				if (board[this.getXY().getX()][this.getXY().getY() + step * i] != null){
					return false;
				}
			}
			return true;
		}
		else if (Math.abs(to.getY() - this.getXY().getY()) == 0){
			int direction = to.getX() - this.getXY().getX();
			int step = direction / Math.abs(direction);
			for (int i = 1; i < Math.abs(direction); i++){
				if (board[this.getXY().getX() + step * i][this.getXY().getY()] != null){
					return false;
				}
			}
			return true;
		}
		else{
			return false;
		}
	}

}
