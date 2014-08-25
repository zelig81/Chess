package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♛♕ figures
public class Queen extends Figure {

	public Queen(XY p, Rank r) {
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
