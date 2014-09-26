package project.ilyagorban.model;

public class XY {
	public static XY getNewXY(char c, char i) {
		if (c >= 'a' && c <= 'h' && i >= '1' && i <= '8') {
			return new XY(c, i);
		} else {
			return null;
		}
	}
	
	public static XY getNewXY(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return null;
		} else {
			return new XY(x, y);
		}
	}
	
	public static XY[] getXYfromInput(String input) {
		if (input.length() != 4) {
			return null;
		}
		char[] inputChars = input.toCharArray();
		if (!(inputChars[0] == inputChars[2] && inputChars[1] == inputChars[3])) {
			XY from = getNewXY(inputChars[0], inputChars[1]);
			XY to = getNewXY(inputChars[2], inputChars[3]);
			if (from == null || to == null) {
				return null;
			} else {
				return new XY[] { from, to };
			}
		}
		return null;
	}
	
	public static XY getXYFromLog(String log) {
		if (log == null || log.length() != 4) {
			return null;
		}
		char[] aChars = log.toCharArray();
		return getNewXY(aChars[2], aChars[3]);
	}
	
	private CoordX cX;
	
	private int y;
	
	private XY(char c, char i) {
		setXY(c - 'a', (Character.digit(i, 10) - 1));
		
	}
	
	private XY(int x, int y) {
		setXY(x, y);
	}
	
	public XY(XY xy) {
		this.cX = xy.cX;
		this.y = xy.y;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		XY other = (XY) o;
		return this.hashCode() == other.hashCode();
		
	}
	
	public int getX() {
		return cX.getX();
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public int hashCode() {
		return cX.getX() * 8 + y;
	}
	
	public void setXY(int x, int y) {
		this.cX = CoordX.getCoordX(x);
		this.y = y;
	}
	
	public void setXY(String xy) {
		this.cX = CoordX.getCoordX(xy.charAt(0));
		this.y = Integer.parseInt(String.valueOf(xy.charAt(1))) - 1;
	}
	
	@Override
	public String toString() {
		return cX.toString() + (y + 1);
	}
	
}
