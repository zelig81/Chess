package project.ilyagorban.model.figures;

import java.util.ArrayList;

import project.ilyagorban.model.Owner;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♙♟ figures
public class Pawn extends Figure {
	
	public Pawn(XY p, Rank r) {
		super(p, r);
	}

	@Override
	protected int checkMoveCorrect(Figure[][] board, XY to) {
		int stepY = to.getY() - this.getXY().getY();
		int stepX = to.getX() - this.getXY().getX();
		int direction = (this.getRank().getOwner() == Owner.WHITE) ? 1 : -1;
		boolean isAbleToGoStraightForwardUntouched = (this.isTouched() == false && stepX == 0 && (stepY * direction == 1 || stepY * direction ==2));
		boolean isAbleToGoStraightForwardTouched = (this.isTouched() == true && stepX == 0 && (stepY * direction == 1));
		ArrayList<XY> removableEnemysXY = new ArrayList<>(2);
		if (this.getXY().getY() > 0 && this.getXY().getY() < 7){
			if (this.getXY().getX() != 7){
				removableEnemysXY.add( new XY(this.getXY().getX() + 1, this.getXY().getY() + direction));
			}
			if (this.getXY().getY() != 0){
				removableEnemysXY.add( new XY(this.getXY().getX() - 1, this.getXY().getY() + direction));
			}
		}
		
		boolean isAbleToTakeFigure = false;
		for (XY xy : removableEnemysXY){
			if (board[xy.getX()][xy.getY()] != null && board[xy.getX()][xy.getY()].getRank().getOwner() != this.getRank().getOwner())
				isAbleToTakeFigure = true;
		}
		
		boolean result = isAbleToGoStraightForwardTouched || isAbleToGoStraightForwardUntouched || isAbleToTakeFigure;
		boolean isReadyToBePromoted = (to.getY() == 7 || to.getY() == 0);
		if (result && isReadyToBePromoted){
			return PAWN_PROMOTION;
		}
		return result ? CORRECT_MOVE : INCORRECT_MOVE;
	}

	@Override
	protected boolean isNoFigureOnTheWay(Figure[][] board, XY to) {
		//in checkMoveCorrect i checked if this pawn can remove any opponent's chess piece
		int stepY = to.getY() - this.getXY().getY();
		int stepX = to.getX() - this.getXY().getX();
		int direction = (this.getRank().getOwner() == Owner.WHITE) ? 1 : -1;
		if (stepX == 0)
			for (int i = 1; i <= stepY * direction; i++){
				if (board[this.getXY().getX()][this.getXY().getY() + i * direction] != null)
					return false;
			}
		return true;
	}

}
