package project.ilyagorban.model;

import project.ilyagorban.model.figures.Figure;

public class ChessModel {
    public static final int OBSTACLE_ON_WAY = -6;
    public static final int DONT_TOUCH_NOT_YOUR_FIGURE_TO_MOVE = -5;
    public static final int OBSTACLE_ON_END_POINT = -4;
    public static final int NO_MOVE = -3;
    public static final int INCORRECT_MOVE = -2;
    public static final int INCORRECT_INPUT = -1;
    public static final int CORRECT_MOVE = 0;
    public static final int PAWN_PROMOTION = 1;
    public static final int CASTLING = 2;
    public static final int CHECK = 3;
    public static final int CHECKMATE = 4;

    private static Board board;

    public void initializeGame() {
	board = Board.getInstance();
	board.initializeGame();
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
	Figure figFrom = board.getFigure(from);
	if (figFrom != null && figFrom.getRank().getOwner() == o) {
	    checkMove = figFrom.checkMove(board, to);
	    if (checkMove == CORRECT_MOVE || checkMove == PAWN_PROMOTION
		    || checkMove == CASTLING) {
		if (board.getFigure(to) != null) {
		    board.remove(to);
		}
		board.move(figFrom, to);
		// check or mate
		if (board.check(to) == true) {
		    return CHECK;

		}
		if (board.mate() == true) {
		    return CHECKMATE;
		}
		// castling
		if (checkMove == CASTLING) {
		    board.castling(figFrom, to);
		}
	    }
	} else {
	    return DONT_TOUCH_NOT_YOUR_FIGURE_TO_MOVE;
	}
	return checkMove;

    }

    public boolean promotePawn(String input, String promotion) {
	XY to = XY.getXYfromInput(input)[1];
	Figure pawn = board.getFigure(to);
	Rank gotRank = Rank.getFigure(promotion, pawn.getRank().getOwner());
	if (gotRank == null)
	    return false;
	pawn.setRank(gotRank);
	return true;
    }

    public boolean castling(String input) {
	return false;

    }

    public boolean enPassant(String input) {
	return false;

    }

    public int check() {
	// TODO check/checkmate/correct_move
	return 0;

    }

    public Figure[][] getBoard() {
	return board.getBoard();
    }

}
