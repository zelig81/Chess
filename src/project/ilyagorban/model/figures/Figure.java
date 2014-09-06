/**
 * 
 */
package project.ilyagorban.model.figures;

import static project.ilyagorban.model.ChessModel.*;

import java.util.ArrayList;

import project.ilyagorban.model.Board;
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
    private int[][] directions;
    private int moveLen;

    public static final int[][] directionsOfBishop = new int[][] { { 1, 1 },
	    { 1, -1 }, { -1, -1 }, { -1, 1 } };
    public static final int[][] directionsOfRook = new int[][] { { 0, 1 },
	    { 0, -1 }, { 1, 0 }, { -1, 0 } };
    public static final int[][] directionsOfQueen = new int[][] { { 1, 0 },
	    { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { -1, -1 }, { 0, -1 },
	    { 1, -1 } };
    public static final int[][] directionsOfKnight = new int[][] { { 2, 1 },
	    { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { -1, 2 }, { 1, -2 },
	    { -1, -2 } };
    public static final int[][][] fourFigureDirections = { directionsOfBishop,
	    directionsOfRook, directionsOfKnight };
    public static final int[] fourFigureMoveLen = { 8, 8, 1 };
    public static final String[][] fourFigureIndex = { { "q", "b" },
	    { "q", "r" }, { "n" } };

    public Figure(XY xy, Rank r) {
	this.xy = xy;
	this.setRank(r);
    }

    public int checkMove(Board board, XY to) {
	boolean isMoveMade = !this.getXY().equals(to);
	if (isMoveMade == false)
	    return NO_MOVE;

	boolean isEndPointEmptyOrEnemy = isEndPointEmptyOrEnemy(this.getRank()
		.getOwner(), board.getFigure(to));
	if (isEndPointEmptyOrEnemy == false)
	    return OBSTACLE_ON_END_POINT;

	ArrayList<XY> possibleMoves = getPossibleMoves(board);
	if (possibleMoves.contains(to) == true) {
	    int result = board.check(this, to);
	    return result;
	} else {
	    return INCORRECT_MOVE;
	}
    }

    public int[][] getDirections() {
	return this.directions;
    }

    public int getMoveLen() {
	return moveLen;
    }

    public ArrayList<XY> getPossibleMoves(Board board) {
	ArrayList<XY> output = new ArrayList<>();
	// cycle of directions
	int thisX = this.getXY().getX();
	int thisY = this.getXY().getY();
	for (int[] dir : getDirections()) {
	    int i = 1;
	    while (true) {
		if (i > getMoveLen())
		    break;
		int newX = thisX + dir[0] * i;
		int newY = thisY + dir[1] * i;
		if (newX < 0 || newX > 7 || newY < 0 || newY > 7)
		    break;
		XY xy = new XY(newX, newY);
		i++;
		Figure fig = board.getFigure(newX, newY);
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

    protected void setDirections(int[][] directions) {
	this.directions = directions;

    }

    public void setMoveLen(int moveLen) {
	this.moveLen = moveLen;
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

    public ArrayList<XY> getPawnPossibleAttack(Board board) {
	ArrayList<XY> output = new ArrayList<>(2);
	int direction = this.getRank().getOwner().getDirection();
	if (this.getXY().getY() > 0 && this.getXY().getY() < 7) {
	    if (this.getXY().getX() != 7) {
		Figure target = board.getFigure(this.getXY().getX() + 1, this
			.getXY().getY() + direction);
		boolean isRemovable = (target != null && target.getRank()
			.getOwner() != this.getRank().getOwner());
		if (isRemovable == true)
		    output.add(target.getXY());
	    }
	    if (this.getXY().getX() != 0) {
		Figure target = board.getFigure(this.getXY().getX() - 1, this
			.getXY().getY() + direction);
		boolean isRemovable = (target != null && target.getRank()
			.getOwner() != this.getRank().getOwner());
		if (isRemovable == true)
		    output.add(target.getXY());
	    }
	}
	return output;
    }

}
