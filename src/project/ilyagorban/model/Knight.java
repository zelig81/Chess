package project.ilyagorban.model;

// ♘♞ figures
public class Knight extends Figure {

	public Knight(XY p, Rank r) {
		super(p, r);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean checkMoveCorrectness(XY from, XY to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean checkNoFigureOnTheWay(Figure[][] board, XY to) {
		// TODO Auto-generated method stub
		return false;
	}

}
