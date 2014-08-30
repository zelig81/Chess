package project.ilyagorban.model.figures;

import static project.ilyagorban.model.ChessModel.CORRECT_MOVE;
import static project.ilyagorban.model.ChessModel.INCORRECT_MOVE;

import java.util.ArrayList;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♗♝figures
public class Bishop extends Figure {

    public Bishop(XY p, Rank r) {
	super(p, r);
    }

    @Override
    public int checkMoveCorrect(Figure[][] board, XY to) {
	int stepsX = Math.abs(to.getX() - this.getXY().getX());
	int stepsY = Math.abs(to.getY() - this.getXY().getY());
	return stepsX == stepsY ? CORRECT_MOVE : INCORRECT_MOVE;
    }

    @Override
    protected boolean isNoFigureOnTheWay(Figure[][] board, XY to) {
	int xMoves = to.getX() - this.getXY().getX();
	int xDirection = xMoves / Math.abs(xMoves);
	int yDirection = (to.getY() - this.getXY().getY()) / Math.abs(xMoves);
	for (int i = 1; i < Math.abs(xMoves); i++) {
	    if (board[this.getXY().getX() + xDirection * i][this.getXY().getY()
		    + yDirection * i] != null) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public ArrayList<XY> getPossibleMoves(Figure[][] board) {
	ArrayList<XY> output = new ArrayList<>();
	// cycle of directions
	int[][] directions = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };
	for (int[] dir : directions) {
	    int i = 1;
	    while (true) {
		int newX = this.getXY().getX() + dir[0] * i;
		int newY = this.getXY().getY() + dir[1] * i;
		i++;
		if (newX < 0 || newX > 7 || newY < 0 || newX > 7)
		    break;
		XY xy = new XY(newX, newY);
		Figure fig = board[newX][newY];
		if (fig == null) {
		    output.add(xy);
		    continue;
		}
		if (fig.getRank().getOwner() != this.getRank().getOwner())
		    output.add(xy);
		break;

	    }
	}
	return output;
    }
}
