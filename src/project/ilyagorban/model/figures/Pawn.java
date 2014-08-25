package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♙♟ figures
public class Pawn extends Figure {
	
	public Pawn(XY p, Rank r) {
		super(p, r);
	}

	@Override
	protected int checkMoveCorrect(XY from, XY to) {
		return 0;
	}

	@Override
	protected boolean isNoFigureOnTheWay(Figure[][] board, XY to) {
		return true;
	}

}
