package project.ilyagorban.model;

public class XY {
	private CoordX cX;
	
	private int y;
	
	public XY(int x, int y) {
		setXY(x, y);
	}
	
	public XY(char c, char i) { // [e2e4] -> 3.1 3.3
		setXY(c - 'a', (Character.digit(i, 10) - 1));
		
	}
	
	public static XY[] getXYfromInput(String input) {
		if (input.length() != 4) {
			return null;
		}
		char[] inputChars = input.toCharArray();
		if (!(inputChars[0] == inputChars[2] && inputChars[1] == inputChars[3])) {
			char cFrom = inputChars[0];
			char cTo = inputChars[2];
			if ((cFrom >= 'a' && cFrom <= 'h') && (cTo >= 'a' && cTo <= 'h')) {
				char iFrom = inputChars[1];
				char iTo = inputChars[3];
				if ((iFrom >= '1' && iFrom <= '8') && (iTo >= '1' && iTo <= '8')) {
					if (cFrom == cTo && iFrom == iTo) {
						return null;
					} else {
						return new XY[] { new XY(cFrom, iFrom), new XY(cTo, iTo) };
					}
				}
			}
		}
		return null;
	}
	
	public XY(XY xy) {
		this.cX = xy.cX;
		this.y = xy.y;
	}
	
	public static XY getXYFromLog(String log) {
		int x, y;
		String[] aLog = log.split("");
		try {
			x = Integer.parseInt(aLog[2]);
			y = Integer.parseInt(aLog[3]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		} else {
			return new XY(x, y);
		}
		
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		XY other = (XY) o;
		return this.getY() == other.getY() && this.getX() == other.getX();
		
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
