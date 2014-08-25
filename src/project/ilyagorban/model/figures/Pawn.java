package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♙♟ figures
public class Pawn extends Figure {
	
	public Pawn(XY p, Rank r) {
		super(p, r);
	}

	public void setRank(Rank rank){
		super.rank = rank;
	}


	@Override
	public void move() {
		// TODO Auto-generated method stub

	}


	@Override
	protected boolean checkMoveCorrectness(XY from, XY to) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected boolean checkNoFigureOnTheWay(Figure[][] board, XY to) {
		// TODO Auto-generated method stub
		return true;
	}

}
