package project.ilyagorban.model.figures;

import static project.ilyagorban.model.ChessModel.CORRECT_MOVE;
import static project.ilyagorban.model.ChessModel.EN_PASSANT;
import static project.ilyagorban.model.ChessModel.PAWN_PROMOTION;

import java.util.ArrayList;

import project.ilyagorban.model.Board;
import project.ilyagorban.model.Owner;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♙♟ figures
public class Pawn extends Figure {
	
	public Pawn(XY p, Rank r) {
		super(p, r);
	}
	
	@Override
	public int checkMove(Board board, XY to) {
		int superMethod = super.checkMove(board, to);
		if (superMethod == CORRECT_MOVE) {
			boolean isReadyToBePromoted = ((to.getY() == 7 && this.getRank().getOwner() == Owner.WHITE) || (to.getY() == 0 && this.getRank().getOwner() == Owner.BLACK));
			if (isReadyToBePromoted) {
				return PAWN_PROMOTION;
			}
			if (to.equals(board.xyEnPassantPossible(this))) {
				return EN_PASSANT;
			}
		}
		return superMethod;
		
	}
	
	@Override
	public ArrayList<XY> getPossibleMoves(Board board) {
		ArrayList<XY> output = new ArrayList<XY>();
		int direction = this.getRank().getOwner().getDirection();
		int x = this.getXY().getX();
		int y = this.getXY().getY();
		boolean isAbleToGoStraightForwardOneMove = (board.getFigure(x, y + direction) == null);
		if (isAbleToGoStraightForwardOneMove == true) {
			output.add(new XY(x, y + direction));
		}
		
		boolean isAbleToGoStraightForwardUntouchedTwoMoves = (this.isTouched() == false && board.getFigure(x, y + direction) == null && board.getFigure(x, y + 2 * direction) == null);
		if (isAbleToGoStraightForwardUntouchedTwoMoves == true) {
			output.add(new XY(x, y + 2 * direction));
		}
		// take figure
		ArrayList<XY> removableEnemysXY = this.getPawnPossibleAttack(board);
		
		for (XY xy : removableEnemysXY) {
			boolean isAbleToTakeFigure = (board.getFigure(xy) != null && board.getFigure(xy).getRank().getOwner() != this.getRank().getOwner());
			
			if (isAbleToTakeFigure == true)
				output.add(xy);
		}
		// TODO make en passant
		XY xyEnPassant = board.xyEnPassantPossible(this);
		if (xyEnPassant != null) {
			output.add(xyEnPassant);
		}
		
		return output;
	}
}
