package project.ilyagorban.model;

import project.ilyagorban.model.figures.*;

public class ChessModel {
	public static final int CORRECT_MOVE = 0;
	public static final int DONT_TOUCH_NOT_YOUR_FIGURE_TO_MOVE = 1;
	public static final int INCORRECT_MOVE = 2;
	public static final int NO_MOVE = 3;
	public static final int OBSTACLE_ON_END_POINT = 4;
	public static final int OBSTACLE_ON_WAY = 5;
	public static final int PAWN_PROMOTION = 6;
	public static final int CASTLING = 7;
	public static final int CHECK = 9;
	public static final int CHECKMATE = 10;
	public static final int INCORRECT_INPUT = -1;

	private Figure[][] board;

	public Figure[][] getBoard() {
		return board;
	}

	public void initializeGame() {
		board = new Figure[8][8];
		board[0][0] = new Rook(new XY(0, 0), Rank.WHITE_ROOK);
		board[1][0] = new Knight(new XY(1, 0), Rank.WHITE_KNIGHT);
		board[2][0] = new Bishop(new XY(2, 0), Rank.WHITE_BISHOP);
		board[3][0] = new Queen(new XY(3, 0), Rank.WHITE_QUEEN);
		board[4][0] = new King(new XY(4, 0), Rank.WHITE_KING);
		board[5][0] = new Bishop(new XY(5, 0), Rank.WHITE_BISHOP);
		board[6][0] = new Knight(new XY(6, 0), Rank.WHITE_KNIGHT);
		board[7][0] = new Rook(new XY(7, 0), Rank.WHITE_ROOK);
		board[0][1] = new Pawn(new XY(0, 1), Rank.WHITE_PAWN);
		board[1][1] = new Pawn(new XY(1, 1), Rank.WHITE_PAWN);
		board[2][1] = new Pawn(new XY(2, 1), Rank.WHITE_PAWN);
		board[3][1] = new Pawn(new XY(3, 1), Rank.WHITE_PAWN);
		board[4][1] = new Pawn(new XY(4, 1), Rank.WHITE_PAWN);
		board[5][1] = new Pawn(new XY(5, 1), Rank.WHITE_PAWN);
		board[6][1] = new Pawn(new XY(6, 1), Rank.WHITE_PAWN);
		board[7][1] = new Pawn(new XY(7, 1), Rank.WHITE_PAWN);

		board[0][7] = new Rook(new XY(0, 7), Rank.BLACK_ROOK);
		board[1][7] = new Knight(new XY(1, 7), Rank.BLACK_KNIGHT);
		board[2][7] = new Bishop(new XY(2, 7), Rank.BLACK_BISHOP);
		board[3][7] = new Queen(new XY(3, 7), Rank.BLACK_QUEEN);
		board[4][7] = new King(new XY(4, 7), Rank.BLACK_KING);
		board[5][7] = new Bishop(new XY(5, 7), Rank.BLACK_BISHOP);
		board[6][7] = new Knight(new XY(6, 7), Rank.BLACK_KNIGHT);
		board[7][7] = new Rook(new XY(7, 7), Rank.BLACK_ROOK);
		board[0][6] = new Pawn(new XY(0, 6), Rank.BLACK_PAWN);
		board[1][6] = new Pawn(new XY(1, 6), Rank.BLACK_PAWN);
		board[2][6] = new Pawn(new XY(2, 6), Rank.BLACK_PAWN);
		board[3][6] = new Pawn(new XY(3, 6), Rank.BLACK_PAWN);
		board[4][6] = new Pawn(new XY(4, 6), Rank.BLACK_PAWN);
		board[5][6] = new Pawn(new XY(5, 6), Rank.BLACK_PAWN);
		board[6][6] = new Pawn(new XY(6, 6), Rank.BLACK_PAWN);
		board[7][6] = new Pawn(new XY(7, 6), Rank.BLACK_PAWN);

	}

	public int move(String input, Owner o) {
		XY[] arrXY = XY.getXYfromInput(input);
		if (arrXY == null) {
			return INCORRECT_INPUT;
		}
		XY from = arrXY[0];
		XY to = arrXY[1];
		// TODO make checking pat/check/mate
		int checkMove = INCORRECT_MOVE;
		Figure figFrom = board[from.getX()][from.getY()];
		if (figFrom != null && figFrom.getRank().getOwner() == o) {
			checkMove = figFrom.checkMove(board, to);
			if (checkMove == CORRECT_MOVE || checkMove == PAWN_PROMOTION
					|| checkMove == CASTLING) {
				board[from.getX()][from.getY()] = null;
				Figure figTo = board[to.getX()][to.getY()];
				if (figTo != null) {
					Owner ownerOfremovedKing = figTo.checkKingRemove();
					if (ownerOfremovedKing != null) {
						return CHECKMATE;
					}

				}
				figFrom.setXY(to);
				board[to.getX()][to.getY()] = figFrom;
				figFrom.setTouched(true);
				if (checkMove == CASTLING) {
					int rookX = (to.getX() == 6) ? 7 : 0;
					int newRookX = (to.getX() == 6) ? 5 : 3;
					Figure figRook = board[rookX][to.getY()];
					figRook.setTouched(true);
					board[rookX][to.getY()] = null;
					board[newRookX][to.getY()] = figRook;
				}
			}
		} else {
			return DONT_TOUCH_NOT_YOUR_FIGURE_TO_MOVE;
		}
		return checkMove;

	}

	public boolean promote(String input, String promotion) {
		XY to = XY.getXYfromInput(input)[1];
		Figure pawn = board[to.getX()][to.getY()];
		Rank gotRank = Rank.getFigure(promotion, pawn.getRank().getOwner());
		if (gotRank == null)
			return false;
		pawn.setRank(gotRank);
		return true;
	}

}
