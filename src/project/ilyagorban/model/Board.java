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
    private ArrayList<XY> startPositions = new ArrayList<>();
    private ArrayList<XY> endPositions = new ArrayList<>();
    private ArrayList<Figure> movedFigure = new ArrayList<>();

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

    public void move(Figure figFrom, int x, int y) {
	startPositions.add(new XY(figFrom.getXY()));
	board[figFrom.getXY().getX()][figFrom.getXY().getY()] = null;
	figFrom.getXY().setXY(x, y);
	endPositions.add(new XY(figFrom.getXY()));
	movedFigure.add(figFrom);
	board[x][y] = figFrom;
	figFrom.setTouched(true);
    }

    public ArrayList<XY> getXyOfWhites() {
	return xyOfWhites;
    }

    public ArrayList<XY> getXyOfBlacks() {
	return xyOfBlacks;
    }

    public XY getXyWhiteKing() {
	return xyWhiteKing;
    }

    public XY getXyBlackKing() {
	return xyBlackKing;
    }

    public ArrayList<XY> getStartPositions() {
	return startPositions;
    }

    public ArrayList<XY> getEndPositions() {
	return endPositions;
    }

    public ArrayList<Figure> getMovedFigure() {
	return movedFigure;
    }

    public void move(Figure figFrom, XY to) {
	move(figFrom, to.getX(), to.getY());
    }

    public Figure getFigure(XY from) {
	return board[from.getX()][from.getY()];
    }

    public Figure getFigure(String in) {
	return getFigure(in.charAt(0), in.charAt(1));
    }

    public Figure getFigure(int x, int y) {
	return board[x][y];
    }

    public Figure getFigure(char x, char y) {
	return board[x - 'a'][(Character.digit(y, 10) - 1)];
    }

    public void remove(int x, int y) {
	if (board[x][y].getRank().getOwner() == Owner.WHITE) {
	    xyOfWhites.remove(board[x][y].getXY());
	} else {
	    xyOfBlacks.remove(board[x][y].getXY());
	}
	board[x][y] = null;

    }

    public void remove(XY xy) {
	remove(xy.getX(), xy.getY());
    }

    public boolean promotePawn(Figure pawn, Rank gotRank, XY to) {
	pawn.setRank(gotRank);
	return true;
    }

    public int check(Figure figFrom, XY to) {
	return check(figFrom, to.getX(), to.getY());

    }

    private int check(Figure figFrom, int x, int y) {
	// TODO make check
	return ChessModel.CORRECT_MOVE;
    }

    public boolean mate() {
	// TODO make mate
	return false;
    }

    public static Board getInstance() {
	return new Board();
    }

    public void castling(Figure king, XY to) {
	int rookX = (to.getX() == 6) ? 7 : 0;
	int newRookX = (to.getX() == 6) ? 5 : 3;
	move(king, to);
	move(getFigure(rookX, king.getXY().getY()), newRookX, king.getXY()
		.getY());

    }

    public void enPassant(XY to) {
	// TODO make board en passant

    }

}
