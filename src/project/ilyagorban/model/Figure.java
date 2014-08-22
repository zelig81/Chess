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
	protected Coordinates coordinates;
	protected Rank rank;
	protected Owner owner; 
	
	public Coordinates getFigureCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public Rank getRank() {
		return rank;
	}
	public Owner getOwner() {
		return owner;
	}
	
}
