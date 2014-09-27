/**
 * 
 */
package project.ilyagorban.model.figures;

import static project.ilyagorban.model.ChessModel.*;

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
	private int[][] moveDirections;
	private int[][] killDirections;
	private int moveLen;
	
	public static final int[][] moveDirectionsOfBishop = new int[][] { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };
	public static final int[][] moveDirectionsOfRook = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	public static final int[][] moveDirectionsOfQueen = new int[][] { { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 } };
	public static final int[][] moveDirectionsOfKnight = new int[][] { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { -1, 2 }, { 1, -2 }, { -1, -2 } };
	public static final int[][] moveDirectionsOfWhitePawn = new int[][] { { 0, 1 } };
	public static final int[][] moveDirectionsOfBlackPawn = new int[][] { { 0, -1 } };
	public static final int[][] killDirectionsOfWhitePawn = new int[][] { { 1, 1, }, { -1, 1 } };
	public static final int[][] killDirectionsOfBlackPawn = new int[][] { { 1, -1, }, { -1, -1 } };
	public static final int[][][] allKillDirections = { moveDirectionsOfKnight, moveDirectionsOfBishop, moveDirectionsOfRook };
	public static final int[] allMoveLen = { 1, 8, 8 };
	public static final String[][] allKillerIndex = { { "n" }, { "q", "b" }, { "q", "r" } };
	
	protected Figure(XY xy, Rank r) {
		this.xy = xy;
		this.setRank(r);
	}
	
	public int[][] getMoveDirections() {
		return this.moveDirections;
	}
	
	public int[][] getKillDirections() {
		return this.killDirections;
	}
	
	public int getMoveLen() {
		return moveLen;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public XY getXY() {
		return xy;
	}
	
	public boolean isTouched() {
		return touched;
	}
	
	protected void setMoveDirections(int[][] directions) {
		this.moveDirections = directions;
	}
	
	protected void setKillDirections(int[][] directions) {
		this.killDirections = directions;
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
	
	public String toLog() {
		return getRank().toLog() + xy.toString();
	}
	
	public static Figure newInstance(String startGamePosition) {
		if (startGamePosition.length() != 4) {
			return null;
		}
		String[] aChar = startGamePosition.split("");
		XY xy = XY.getXYFromLog(startGamePosition);
		if (xy == null) {
			return null;
		}
		Owner owner = Owner.getOwner(aChar[0]);
		if (owner == null) {
			return null;
		}
		Rank rank = Rank.getRank(aChar[1], owner);
		if (rank == null) {
			return null;
		}
		switch (aChar[1]) {
			case "p":
				return new Pawn(xy, rank);
			case "n":
				return new Knight(xy, rank);
			case "b":
				return new Bishop(xy, rank);
			case "r":
				return new Rook(xy, rank);
			case "q":
				return new Queen(xy, rank);
			case "k":
				return new King(xy, rank);
			default:
				return null;
		}
	}
	
	public boolean isEnemy(Figure fig) {
		if (fig == null) {
			return false;
		}
		return this.getRank().getOwner() != fig.getRank().getOwner();
	}
	
	public boolean isEnemy(Owner o) {
		if (o == null) {
			return false;
		}
		return this.getRank().getOwner() != o;
	}
	
	public int getSpecialCorrectMoveName(XY to) {
		return CORRECT_MOVE;
	}
}
