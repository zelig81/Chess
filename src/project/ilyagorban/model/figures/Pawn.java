package project.ilyagorban.model.figures;

import static project.ilyagorban.model.ChessModel.CORRECT_MOVE;
import static project.ilyagorban.model.ChessModel.INCORRECT_MOVE;
import static project.ilyagorban.model.ChessModel.PAWN_PROMOTION;

import java.util.ArrayList;

import project.ilyagorban.model.Board;
import project.ilyagorban.model.Owner;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♙♟ figures
public class Pawn extends Figure {

    public Pawn(XY p, Rank r) {
	super(p, r);
    }

    // @Override
    protected int checkMoveCorrect(Figure[][] board, XY to) {
	// TODO en-passant
	int stepY = to.getY() - this.getXY().getY();
	int stepX = to.getX() - this.getXY().getX();
	int direction = (this.getRank().getOwner() == Owner.WHITE) ? 1 : -1;
	boolean isAbleToGoStraightForwardUntouchedTwoMoves = (this.isTouched() == false
		&& stepX == 0 && stepY * direction == 2);
	boolean isAbleToGoStraightForwardOneMove = (stepX == 0 && (stepY
		* direction == 1));
	boolean isAbleToTakeFigure = (Math.abs(stepX) == 1) ? isAbleToTakeFigure(
		board, to, direction) : false;

	boolean result = isAbleToGoStraightForwardOneMove
		|| isAbleToGoStraightForwardUntouchedTwoMoves
		|| isAbleToTakeFigure;
	boolean isReadyToBePromoted = (to.getY() == 7 || to.getY() == 0);
	if (result && isReadyToBePromoted) {
	    return PAWN_PROMOTION;
	}
	return result ? CORRECT_MOVE : INCORRECT_MOVE;
    }

    private boolean isAbleToTakeFigure(Figure[][] board, XY to, int direction) {
	ArrayList<XY> removableEnemysXY = new ArrayList<>(2);
	if (this.getXY().getY() > 0 && this.getXY().getY() < 7) {
	    if (this.getXY().getX() != 7) {
		removableEnemysXY.add(new XY(this.getXY().getX() + 1, this
			.getXY().getY() + direction));
	    }
	    if (this.getXY().getY() != 0) {
		removableEnemysXY.add(new XY(this.getXY().getX() - 1, this
			.getXY().getY() + direction));
	    }
	}

	boolean isAbleToTakeFigure = false;
	for (XY xy : removableEnemysXY) {
	    isAbleToTakeFigure = xy.equals(to)
		    && board[xy.getX()][xy.getY()] != null
		    && board[xy.getX()][xy.getY()].getRank().getOwner() != this
			    .getRank().getOwner();
	    if (isAbleToTakeFigure == true)
		break;
	}

	return isAbleToTakeFigure;
    }

    // @Override
    protected boolean isNoFigureOnTheWay(Figure[][] board, XY to) {
	// in checkMoveCorrect i checked if this pawn can remove any opponent's
	// chess piece
	int stepY = to.getY() - this.getXY().getY();
	int stepX = to.getX() - this.getXY().getX();
	int direction = (this.getRank().getOwner() == Owner.WHITE) ? 1 : -1;
	if (stepX == 0) {
	    for (int i = 1; i <= stepY * direction; i++) {
		Figure fig = board[this.getXY().getX()][this.getXY().getY() + i
			* direction];
		if (fig != null)
		    return false;
	    }
	    return true;
	} else {
	    boolean isAbleToTakeFigure = isAbleToTakeFigure(board, to,
		    direction);
	    if (isAbleToTakeFigure == true) {
		return true;
	    }
	    return false;
	}
    }

    @Override
    public ArrayList<XY> getPossibleMoves(Board board) {
	ArrayList<XY> list = new ArrayList<XY>();
	list.add(new XY(2, 2));
	return list;
    }

}
