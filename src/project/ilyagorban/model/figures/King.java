package project.ilyagorban.model.figures;

import java.util.ArrayList;

import project.ilyagorban.model.Board;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;
import static project.ilyagorban.model.ChessModel.*;

// ♚♔ figures
public class King extends Figure {
	
	public King(XY p, Rank r) {
		super(p, r);
		setMoveDirections(moveDirectionsOfQueen);
		setKillDirections(moveDirectionsOfQueen);
		setMoveLen(1);
	}
	
	@Override
	public int getSpecialCorrectMoveName(XY to) {
		// TODO Auto-generated method stub
		return super.getSpecialCorrectMoveName(to);
	}
	
	public int checkMove(Board board, XY to) {
		// TODO move checkMove to board / getPSM
		int output = CORRECT_MOVE;
		if (output < CORRECT_MOVE)
			return output;
		else {
			boolean isAbleToCastle = Math.abs(this.getXY().getX() - to.getX()) == 2;
			if (isAbleToCastle)
				output = CASTLING;
		}
		return output;
	}
	
	// TODO remove king.getPM
	// @Override
	// public ArrayList<XY> getPossibleMoves(Board board) {
	// ArrayList<XY> output = super.getPossibleMoves(board);
	// int kingX = this.getXY().getX();
	// int kingY = this.getXY().getY();
	//
	// boolean isUntouchedKing = this.getRank().getIndex().equals("k") &&
	// this.isTouched() == false;
	// if (isUntouchedKing == true) {
	// // left and right castling check:
	// for (int x = 0; x <= 7; x = x + 7) {
	// int stepsX = x - kingX;
	// int direction = stepsX / Math.abs(stepsX);
	// Figure rook = board.getFigure(x, kingY);
	// if (rook != null && rook.isTouched() == false) {
	// boolean isAbleToCastle = true;
	// for (int i = 1; i < Math.abs(stepsX); i++) {
	// int newX = kingX + i * direction;
	// Figure fig = board.getFigure(newX, kingY);
	// boolean isEmpty = (fig == null);
	// if (isEmpty) {
	// continue;
	// } else {
	// isAbleToCastle = false;
	// break;
	// }
	// }
	// if (isAbleToCastle == true)
	// output.add(XY.getNewXY(kingX + direction * 2, kingY));
	// }
	// }
	// }
	//
	// return output;
	// }
	//
	
	public boolean isUnderAttack(Board board) {
		// TODO move isUnderAttack to board + add move/killdirections
		// boolean output = false;
		// for (int i = 0; i < 3; i++) {
		// if (output == true)
		// break;
		// this.setMoveDirections(allKillDirections[i]);
		// this.setMoveLen(allMoveLen[i]);
		// ArrayList<XY> pm = board.getPossibleMoves(this);
		// if (pm == null) {
		// System.out.println(this.toLog() + allKillDirections[i].toString());
		// break;
		// }
		// if (pm.size() != 0)
		// for (XY xy : pm) {
		// Figure fig = board.getFigure(xy);
		// if (fig != null) {
		// for (String s : allKillerIndex[i]) {
		// boolean isThreat = fig.isEnemy(this) &&
		// fig.getRank().getIndex().equals(s);
		// if (isThreat) {
		// output = true;
		// break;
		// }
		// }
		// }
		// }
		// }
		// if (output == false) {
		// ArrayList<XY> ppa = board.getPawnPossibleAttack(null, null);
		// for (XY xy : ppa) {
		// Figure fig = board.getFigure(xy);
		// if (this.isEnemy(fig) && fig.getRank().getIndex() == "p") {
		// output = true;
		// break;
		// }
		// }
		// }
		// this.setMoveDirections(moveDirectionsOfQueen);
		// this.setMoveLen(1);
		// return output;
		//
		return false;
	}
	
}
