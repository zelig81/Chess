package project.ilyagorban.model.figures;

import static project.ilyagorban.model.ChessModel.CASTLING;
import static project.ilyagorban.model.ChessModel.CORRECT_MOVE;
import static project.ilyagorban.model.ChessModel.INCORRECT_MOVE;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♚♔ figures
public class King extends Figure {

    public King(XY p, Rank r) {
	super(p, r);
    }

    @Override
    protected int checkMoveCorrect(Figure[][] board, XY to) {
	boolean isCastling = isCastling(board, to);
	if (isCastling == true) {
	    return CASTLING;
	}
	int stepsX = Math.abs(to.getX() - this.getXY().getX());
	int stepsY = Math.abs(to.getY() - this.getXY().getY());
	return (stepsX <= 1 && stepsY <= 1) ? CORRECT_MOVE : INCORRECT_MOVE;
    }

    private boolean isCastling(Figure[][] board, XY to) {
	boolean criterionOnKingForCastling = this.isTouched() == false
		&& (this.getXY().getY() - to.getY() == 0)
		&& (Math.abs(this.getXY().getX() - to.getX()) == 2);
	if (criterionOnKingForCastling == false) {
	    return false;
	}

	boolean rookNotTouched;
	int rookX;
	int[] squares;
	if (to.getX() == 6) {
	    rookX = 7;
	    squares = new int[] { 5, 6 };
	} else {
	    rookX = 0;
	    squares = new int[] { 3, 2, 1 };
	}
	Figure rook = board[rookX][to.getY()];
	rookNotTouched = ((rook != null) && (rook.isTouched() == false));
	if (rookNotTouched == false) {
	    return false;
	}

	// noFigureInBetween
	for (int i : squares) {
	    Figure fig = board[i][to.getY()];
	    if (fig != null) {
		return false;
	    }
	}

	// all criterions are acomplished ->
	return true;
    }

    @Override
    protected boolean isNoFigureOnTheWay(Figure[][] board, XY to) {
	Figure endPointFigure = board[to.getX()][to.getY()];
	return (endPointFigure == null || endPointFigure.getRank().getOwner() != this
		.getRank().getOwner());
    }

}
