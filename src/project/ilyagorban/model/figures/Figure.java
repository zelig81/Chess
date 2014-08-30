/**
 * 
 */
package project.ilyagorban.model.figures;

import static project.ilyagorban.model.ChessModel.CORRECT_MOVE;
import static project.ilyagorban.model.ChessModel.INCORRECT_MOVE;
import static project.ilyagorban.model.ChessModel.NO_MOVE;
import static project.ilyagorban.model.ChessModel.OBSTACLE_ON_END_POINT;

import java.util.ArrayList;

import project.ilyagorban.model.Owner;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

/**
 * @author zelig
 *
 */
public abstract class Figure {
    private XY xy;
    private boolean touched;
    private Rank rank;

    public Figure(XY xy, Rank r) {
	this.xy = xy;
	this.setRank(r);
    }

    public int checkMove(Figure[][] board, XY to) {
	boolean isMoveMade = !this.getXY().equals(to);
	if (isMoveMade == false)
	    return NO_MOVE;

	boolean isEndPointEmptyOrEnemy = isEndPointEmptyOrEnemy(this.getRank()
		.getOwner(), board[to.getX()][to.getY()]);
	if (isEndPointEmptyOrEnemy == false)
	    return OBSTACLE_ON_END_POINT;

	ArrayList<XY> possibleMoves = getPossibleMoves(board);
	if (possibleMoves.contains(to) == true)
	    return CORRECT_MOVE;
	else
	    return INCORRECT_MOVE;
    }

    public Rank getRank() {
	return rank;
    }

    public XY getXY() {
	return xy;
    }

    protected boolean isEndPointEmptyOrEnemy(Owner o, Figure figTo) {
	boolean result = (figTo == null || figTo.getRank().getOwner() != o);
	return result;

    }

    public boolean isTouched() {
	return touched;
    }

    public void setRank(Rank rank) {
	this.rank = rank;
    }

    public void setTouched(boolean touched) {
	this.touched = touched;
    }

    public void setXY(XY xy) {
	this.xy = xy;
    }

    @Override
    public String toString() {
	return getRank().getPicture();
    }

    public boolean checkMate(Figure[][] board, XY xyWhiteKing, XY xyBlackKing) {
	return false;

    }

    public abstract ArrayList<XY> getPossibleMoves(Figure[][] board);

}
