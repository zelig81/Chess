/**
 * 
 */
package project.ilyagorban.model.figures;

import project.ilyagorban.model.Owner;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;
import static project.ilyagorban.model.ChessModel.*;

/**
 * @author zelig
 *
 */
public abstract class Figure {
	private XY xy;
	private boolean touched;
	private Rank rank;
	
	public Figure(XY xy, Rank r){
		this.xy = xy;
		this.setRank(r);
	}
	
	protected boolean isEndPointEmptyOrEnemy(Owner o, Figure figTo){
		boolean result = (figTo == null || figTo.getRank().getOwner() != o) ;
		return result;

	}
	
	public int checkMove(Figure[][] board, XY to) {
		boolean isMoveMade = !this.getXY().equals(to) ;
		if (isMoveMade == false)
			return NO_MOVE;
		
		int moveCorrect = checkMoveCorrect(board, to);
		if (moveCorrect == INCORRECT_MOVE)
			return moveCorrect;
		
		boolean isEndPointEmptyOrEnemy = isEndPointEmptyOrEnemy(this.getRank().getOwner(), board[to.getX()][to.getY()]);
		if (isEndPointEmptyOrEnemy == false)
			return OBSTACLE_ON_END_POINT;
		
		boolean isNoFigureOnTheWay = (isNoFigureOnTheWay(board, to));
		if (isNoFigureOnTheWay == false)
			return OBSTACLE_ON_WAY;
		
		return moveCorrect;
		
	}
	
	protected abstract int checkMoveCorrect(Figure[][] board, XY to);
	
	protected abstract boolean isNoFigureOnTheWay(Figure[][] board, XY to);
	
	public Rank getRank() {
		return rank;
	}
	
	public XY getXY() {
		return xy;
	}
	
	public void setXY(XY xy){
		this.xy = xy;
	}

	public String toString(){
		return getRank().getPicture();
	}

	public Owner checkKingRemove() {
		return null;
		
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

}
