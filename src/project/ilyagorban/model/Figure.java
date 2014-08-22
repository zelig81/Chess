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
	protected XY xy;
	protected Rank rank;
	
	public Figure(XY xy, Rank r){
		this.xy = xy;
		this.rank = r;
	}
	
	public XY getXY() {
		return xy;
	}
	
	public void setXY(XY xy){
		this.xy = xy;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public abstract void removeFigure();
	
	public String toString(){
		return rank.getPicture();
	}
	
}
