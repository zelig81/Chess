package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♗♝figures
public class Bishop extends Figure {

	public Bishop(XY p, Rank r) {
		super(p, r);
	}

	@Override
	public int checkMoveCorrect(XY from, XY to) {
		int stepsX = Math.abs(to.getX() - from.getX());
		int stepsY = Math.abs(to.getY() - from.getY());
		return stepsX == stepsY ? CORRECT_MOVE : INCORRECT_MOVE;
	}

	@Override
	protected boolean isNoFigureOnTheWay(Figure[][] board, XY to) {
		int xMoves = to.getX() - this.getXY().getX();
		int xDirection = xMoves / Math.abs(xMoves);
		int yDirection = (to.getY() - this.getXY().getY()) / Math.abs(xMoves);
		for (int i = 1; i < Math.abs(xMoves); i++){
			if (board[this.getXY().getX() + xDirection * i][this.getXY().getY() + yDirection * i] != null){
				return false;
			}
		}
		return true;
	}


}
