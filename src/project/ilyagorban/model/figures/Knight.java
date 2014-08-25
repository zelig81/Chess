package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♘♞ figures
public class Knight extends Figure {

	public Knight(XY p, Rank r) {
		super(p, r);
	}

	@Override
	protected int checkMoveCorrect(XY from, XY to) {
		int stepX = Math.abs(to.getX() - from.getX());
		int stepY = Math.abs(to.getY() - from.getY());
		boolean isEquals2 = (stepX * stepY == 2);
		return (isEquals2 == true) ? CORRECT_MOVE : INCORRECT_MOVE;
	}

	@Override
	protected boolean isNoFigureOnTheWay(Figure[][] board, XY to) {
		return true;
	}

}
