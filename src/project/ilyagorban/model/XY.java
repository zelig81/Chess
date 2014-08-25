package project.ilyagorban.model;

public class XY{
	private CoordX cX;
	private int y;
	
	public XY (int x, int y){
		setXY(x,y);
	}
	
	public XY(String xy){ //[e2 e4] -> 3.1 3.3
		setXY(xy);
		
	}
	
	public int getX (){
		return cX.getX();
	}
	
	public String getXName(){
		return cX.toString();
	}
	
	public int getY(){
		return y;
	}
	
	public String getYName(){
		return String.valueOf(y+1);
	}
	
	public void setXY(int x, int y){
		this.cX = CoordX.getCoordX(x);
		this.y = y;
	}
	
	public void setXY(String xy){
		this.cX = CoordX.getCoordX(xy.charAt(0));
		this.y = (int) Integer.parseInt(String.valueOf(xy.charAt(1)))- 1;
	}

}
