package project.ilyagorban.model;

public class XY {
	public static XY[] getXYfromInput(String input) {
		String[] arrStr = input.split(" ");
		if (arrStr.length == 2 && arrStr[0].length() == 2
				&& arrStr[1].length() == 2 && !arrStr[0].equals(arrStr[1])) {
			char cFrom = arrStr[0].charAt(0);
			char cTo = arrStr[1].charAt(0);
			if ((cFrom >= 'a' && cFrom <= 'h') && (cTo >= 'a' && cTo <= 'h')) {
				char iFrom = arrStr[0].charAt(1);
				char iTo = arrStr[1].charAt(1);
				if ((iFrom >= '1' && iFrom <= '8')
						&& (iTo >= '1' && iTo <= '8')) {
					return new XY[] { new XY(arrStr[0]), new XY(arrStr[1]) };
				}
			}
		}
		return null;
	}

	private CoordX cX;

	private int y;

	public XY(int x, int y) {
		setXY(x, y);
	}

	public XY(String xy) { // [e2 e4] -> 3.1 3.3
		setXY(xy);

	}

	@Override
	public boolean equals(Object o) {
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

}
