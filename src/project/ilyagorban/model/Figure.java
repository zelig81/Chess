/**
 * 
 */
package project.ilyagorban.model;

/**
 * @author zelig
 *
 */
public abstract class Figure {
	public abstract void move();
	protected CoordX coordX;
	protected int y;
	protected Rank rank;
	
	public Figure(int x, int y, Rank r){
		this.coordX = CoordX.getCoordX(x);
		this.y = y;
		this.rank = r;
	}
	
	public CoordX getCoordX() {
		return coordX;
	}
	
	public int getX(){
		return coordX.getX();
	}
	
	public void setX(int x) {
		this.coordX = CoordX.getCoordX(x);
	}
	public Rank getRank() {
		return rank;
	}
	
	public abstract void removeFigure();
	
}
