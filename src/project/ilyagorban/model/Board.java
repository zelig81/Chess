package project.ilyagorban.model;

import java.util.ArrayList;

import project.ilyagorban.model.figures.Bishop;
import project.ilyagorban.model.figures.Figure;
import project.ilyagorban.model.figures.King;
import project.ilyagorban.model.figures.Knight;
import project.ilyagorban.model.figures.Pawn;
import project.ilyagorban.model.figures.Queen;
import project.ilyagorban.model.figures.Rook;

public class Board {
    private Figure[][] board = new Figure[8][8];
    private ArrayList<XY> xyOfWhites = new ArrayList<>();
    private ArrayList<XY> xyOfBlacks = new ArrayList<>();
    private XY xyWhiteKing;
    private XY xyBlackKing;

    public Figure[][] getBoard() {
	return board;
    }

    public void initializeGame() {
	xyWhiteKing = new XY(4, 0);
	xyBlackKing = new XY(4, 7);
	setFigureToPosition(new Rook(new XY(0, 0), Rank.WHITE_ROOK));
	setFigureToPosition(new Knight(new XY(1, 0), Rank.WHITE_KNIGHT));
	setFigureToPosition(new Bishop(new XY(2, 0), Rank.WHITE_BISHOP));
	setFigureToPosition(new Queen(new XY(3, 0), Rank.WHITE_QUEEN));
	setFigureToPosition(new King(xyWhiteKing, Rank.WHITE_KING));
	setFigureToPosition(new Bishop(new XY(5, 0), Rank.WHITE_BISHOP));
	setFigureToPosition(new Knight(new XY(6, 0), Rank.WHITE_KNIGHT));
	setFigureToPosition(new Rook(new XY(7, 0), Rank.WHITE_ROOK));
	setFigureToPosition(new Pawn(new XY(0, 1), Rank.WHITE_PAWN));
	setFigureToPosition(new Pawn(new XY(1, 1), Rank.WHITE_PAWN));
	setFigureToPosition(new Pawn(new XY(2, 1), Rank.WHITE_PAWN));
	setFigureToPosition(new Pawn(new XY(3, 1), Rank.WHITE_PAWN));
	setFigureToPosition(new Pawn(new XY(4, 1), Rank.WHITE_PAWN));
	setFigureToPosition(new Pawn(new XY(5, 1), Rank.WHITE_PAWN));
	setFigureToPosition(new Pawn(new XY(6, 1), Rank.WHITE_PAWN));
	setFigureToPosition(new Pawn(new XY(7, 1), Rank.WHITE_PAWN));

	setFigureToPosition(new Rook(new XY(0, 7), Rank.BLACK_ROOK));
	setFigureToPosition(new Knight(new XY(1, 7), Rank.BLACK_KNIGHT));
	setFigureToPosition(new Bishop(new XY(2, 7), Rank.BLACK_BISHOP));
	setFigureToPosition(new Queen(new XY(3, 7), Rank.BLACK_QUEEN));
	setFigureToPosition(new King(xyBlackKing, Rank.BLACK_KING));
	setFigureToPosition(new Bishop(new XY(5, 7), Rank.BLACK_BISHOP));
	setFigureToPosition(new Knight(new XY(6, 7), Rank.BLACK_KNIGHT));
	setFigureToPosition(new Rook(new XY(7, 7), Rank.BLACK_ROOK));
	setFigureToPosition(new Pawn(new XY(0, 6), Rank.BLACK_PAWN));
	setFigureToPosition(new Pawn(new XY(1, 6), Rank.BLACK_PAWN));
	setFigureToPosition(new Pawn(new XY(2, 6), Rank.BLACK_PAWN));
	setFigureToPosition(new Pawn(new XY(3, 6), Rank.BLACK_PAWN));
	setFigureToPosition(new Pawn(new XY(4, 6), Rank.BLACK_PAWN));
	setFigureToPosition(new Pawn(new XY(5, 6), Rank.BLACK_PAWN));
	setFigureToPosition(new Pawn(new XY(6, 6), Rank.BLACK_PAWN));
	setFigureToPosition(new Pawn(new XY(7, 6), Rank.BLACK_PAWN));

    }

    public void setFigureToPosition(Figure fig) {
	if (fig.getRank().getOwner() == Owner.WHITE)
	    xyOfWhites.add(fig.getXY());
	else
	    xyOfBlacks.add(fig.getXY());
	board[fig.getXY().getX()][fig.getXY().getY()] = fig;
    }

    public void move(XY from, XY to) {
	Figure fig = board[from.getX()][from.getY()];
	from.setXY(to.getX(), to.getY());
	board[to.getX()][to.getY()] = null;
    }

    public void remove(XY xy) {
	if (board[xy.getX()][xy.getY()].getRank().getOwner() == Owner.WHITE) {
	    xyOfWhites.remove(board[xy.getX()][xy.getY()]);
	} else {
	    xyOfBlacks.remove(board[xy.getX()][xy.getY()]);
	}
	board[xy.getX()][xy.getY()] = null;

    }

    public boolean check() {
	return false;

    }

    public boolean mate() {
	return false;
    }
}
