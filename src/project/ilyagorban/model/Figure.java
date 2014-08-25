/**
 * 
 */
package project.ilyagorban.model;

/**
 * @author zelig
 *
 */
public abstract class Figure {
	private XY xy;
	protected Rank rank;
	public Figure(XY xy, Rank r){
		this.xy = xy;
		this.rank = r;
	}
	
	protected boolean checkEndPointEmptyOrEnemy(Owner o, Figure figTo){
		boolean result = (figTo == null || figTo.getRank().getOwner() != o) ;
		return result;

	}
	
	public boolean checkMove(Figure[][] board, XY to) {
		boolean isMoveMade = (Math.abs(to.getX() - this.getXY().getX()) + Math.abs(to.getY() - this.getXY().getY()) > 0) ;
		boolean moveCorrectness = (checkMoveCorrectness(this.getXY(), to));
		boolean noFigureOnTheWay = (checkNoFigureOnTheWay(board, to));
		boolean endPointEmptyOrEnemy = checkEndPointEmptyOrEnemy(this.getRank().getOwner(), board[to.getX()][to.getY()]);
		boolean result =  isMoveMade && moveCorrectness && noFigureOnTheWay && endPointEmptyOrEnemy;
		return result;
		
	}
	
	protected abstract boolean checkMoveCorrectness(XY from, XY to);
	
	protected abstract boolean checkNoFigureOnTheWay(Figure[][] board, XY to);
	
	public Rank getRank() {
		return rank;
	}
	
	public XY getXY() {
		return xy;
	}
	
	public abstract void move();
	
	public void setXY(XY xy){
		this.xy = xy;
	}

	public String toString(){
		return rank.getPicture();
	}

	public Owner checkKingRemove() {
		return null;
		
	}

}
