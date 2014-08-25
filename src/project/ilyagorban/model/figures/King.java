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
	protected int checkMoveCorrect(Figure[][] board, XY to) {
		int stepsX = Math.abs(to.getX() -  this.getXY().getX());
		int stepsY = Math.abs(to.getY() - this.getXY().getY() );
		return (stepsX <=1 && stepsY <=1) ? CORRECT_MOVE : INCORRECT_MOVE;
	}

	@Override
	protected boolean isNoFigureOnTheWay(Figure[][] board, XY to) {
		Figure endPointFigure = board[to.getX()][to.getY()];
		return (endPointFigure == null || endPointFigure.getRank().getOwner() != this.getRank().getOwner());
	}

	@Override
	public Owner checkKingRemove() {
		return this.getRank().getOwner();
		
	}

}
