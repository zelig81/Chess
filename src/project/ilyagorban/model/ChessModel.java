package project.ilyagorban.model;

import project.ilyagorban.model.figures.Figure;

public class ChessModel {
    public static final int CHECK_TO_CURRENT_SIDE = -7;
    public static final int OBSTACLE_ON_WAY = -6;
    public static final int DONT_TOUCH_NOT_YOUR_FIGURE_TO_MOVE = -5;
    public static final int OBSTACLE_ON_END_POINT = -4;
    public static final int NO_MOVE = -3;
    public static final int INCORRECT_MOVE = -2;
    public static final int INCORRECT_INPUT = -1;
    public static final int CORRECT_MOVE = 0;
    public static final int PAWN_PROMOTION = 1;
    public static final int CASTLING = 2;
    public static final int EN_PASSANT = 3;
    public static final int CHECK_TO_AWAITING_SIDE = 4;
    public static final int CHECKMATE_TO_WHITE = 5;
    public static final int CHECKMATE_TO_BLACK = 6;
    public static final int DRAW = 7;

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
	int checkMove = INCORRECT_MOVE;
	Figure figFrom = board.getFigure(from);

	if (figFrom != null && figFrom.getRank().getOwner() == o) {
	    checkMove = figFrom.checkMove(board, to);

	    if (checkMove == CORRECT_MOVE || checkMove == PAWN_PROMOTION) {
		if (board.getFigure(to) != null) {
		    board.remove(to);
		}
		board.move(figFrom, to);
	    } else if (checkMove == CASTLING) {
		board.castling(figFrom, to);
	    } else if (checkMove == EN_PASSANT) {
		board.enPassant(figFrom, board.getMovedFigure());
	    }
	    return checkMove;

	} else {
	    return DONT_TOUCH_NOT_YOUR_FIGURE_TO_MOVE;
	}

    }

    public boolean promotePawn(String input, String promotion) {
	XY to = XY.getXYfromInput(input)[1];
	Figure pawn = board.getFigure(to);
	Rank gotRank = Rank.getRank(promotion, pawn.getRank().getOwner());
	if (gotRank == null)
	    return false;
	else
	    return board.promotePawn(pawn, gotRank, to);
    }

    public Figure[][] getBoard() {
	return board.getBoard();
    }

    public Board getBoardObject() {
	return board;
    }
}
