package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♜♖
public class Rook extends Figure {

	public Rook(XY p, Rank r) {
		super(p, r);
	}

	@Override
	protected int checkMoveCorrect(Figure[][] board, XY to) {
		boolean result = (Math.abs(to.getX() - this.getXY().getX()) == 0 || Math.abs(to.getY() - this.getXY().getY()) == 0) ;
		return (result) ? CORRECT_MOVE : INCORRECT_MOVE;
	}

	@Override
	protected boolean isNoFigureOnTheWay(Figure[][] board, XY to) {
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
