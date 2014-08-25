package project.ilyagorban.model.figures;

import project.ilyagorban.model.Owner;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♚♔ figures
public class King extends Figure {


	public King(XY p, Rank r) {
		super(p, r);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean checkMoveCorrectness(XY from, XY to) {
		int stepsX = Math.abs(to.getX() -  from.getX());
		int stepsY = Math.abs(to.getY() - from.getY() );
		return (stepsX <=1 && stepsY <=1);
	}

	@Override
	protected boolean checkNoFigureOnTheWay(Figure[][] board, XY to) {
		Figure endPointFigure = board[to.getX()][to.getY()];
		return (endPointFigure == null || endPointFigure.getRank().getOwner() != this.getRank().getOwner());
	}

	@Override
	public Owner checkKingRemove() {
		return this.getRank().getOwner();
		
	}

}
