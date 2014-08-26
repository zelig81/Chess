package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;
import static project.ilyagorban.model.ChessModel.*;

// ♛♕ figures
public class Queen extends Figure {

	public Queen(XY p, Rank r) {
		super(p, r);
	}

	@Override
	protected int checkMoveCorrect(Figure[][] board, XY to) {
		int stepsX = Math.abs(to.getX() - this.getXY().getX());
		int stepsY = Math.abs(to.getY() - this.getXY().getY());
		boolean isMoveAsRook = (stepsX == 0 || stepsY == 0) ;
		boolean isMoveAsBishop = (stepsX == stepsY);
		boolean result = isMoveAsRook || isMoveAsBishop;
		return (result) ? CORRECT_MOVE : INCORRECT_MOVE;
	}

	@Override
	protected boolean isNoFigureOnTheWay(Figure[][] board, XY to) {
		int xMoves = to.getX() - this.getXY().getX();
		int xDivision = (xMoves != 0 ? Math.abs(xMoves) : 1);
		int xDirection = xMoves / xDivision;
		int yMoves = to.getY() - this.getXY().getY();
		int yDivision = (yMoves != 0 ? Math.abs(yMoves) : 1);
		int yDirection = yMoves / yDivision;
		int steps = Math.max(xDivision, yDivision) - 1;
		int x = 0;
		int y = 0;
		while(steps != 0){
			if (y < yDivision - 1)
				y++;
			if (x < xDivision - 1)
				x++;
			steps--;
			Figure next = board[this.getXY().getX() + xDirection * x][this.getXY().getY() + yDirection * y];
			if (next != this && next != null){
				return false;
			}
		}
		return true;
	}

}
