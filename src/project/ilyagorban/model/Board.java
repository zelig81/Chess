package project.ilyagorban.model;

import java.util.ArrayList;
import java.util.HashMap;

import static project.ilyagorban.model.ChessModel.*;
import project.ilyagorban.model.figures.Bishop;
import project.ilyagorban.model.figures.Figure;
import project.ilyagorban.model.figures.King;
import project.ilyagorban.model.figures.Knight;
import project.ilyagorban.model.figures.Pawn;
import project.ilyagorban.model.figures.Queen;
import project.ilyagorban.model.figures.Rook;

public class Board {
	public static Board getInstance() {
		return new Board();
	}
	
	private Figure[][] board = new Figure[8][8];
	private HashMap<Owner, ArrayList<XY>> xyOfSides = new HashMap<>();
	private HashMap<Owner, XY> xyOfKings = new HashMap<>();
	private XY startPositions;
	private XY endPositions;
	private Figure lastMovedFigure;
	
	private Board() {
		super();
		xyOfSides.put(Owner.WHITE, new ArrayList<XY>());
		xyOfSides.put(Owner.BLACK, new ArrayList<XY>());
	}
	
	public void castling(Figure king, XY to) {
		int rookX = (to.getX() == 6) ? 7 : 0;
		int newRookX = (to.getX() == 6) ? 5 : 3;
		move(king, to);
		move(getFigure(rookX, king.getXY().getY()), newRookX, king.getXY().getY());
		
	}
	
	public int check(Figure figFrom, XY to) {
		Owner w = Owner.WHITE;
		Owner b = Owner.BLACK;
		Owner o = figFrom.getRank().getOwner();
		XY origXY = new XY(figFrom.getXY());
		int result = CORRECT_MOVE;
		moveWithoutTrace(figFrom, to);
		
		if (origXY.equals(xyOfKings.get(o))) {
			xyOfKings.put(o, to);
			boolean isKingsNotOnNeighborSquares = Math.abs(xyOfKings.get(w).getX() - xyOfKings.get(b).getX()) > 1 && Math.abs(xyOfKings.get(w).getY() - xyOfKings.get(b).getY()) > 1;
			if (isKingsNotOnNeighborSquares == false) {
				result = INCORRECT_MOVE;
			} else {
				result = checkFromKingsPointOfView(o);
			}
			xyOfKings.put(o, origXY);
		} else {
			result = checkFromKingsPointOfView(o);
		}
		
		this.moveWithoutTrace(figFrom, origXY);
		return result;
		
	}
	
	public int checkFromKingsPointOfView(Owner o) {
		King currentKing = (King) getFigure(xyOfKings.get(o));
		boolean isUnderAttack = currentKing.isUnderAttack(this);
		if (isUnderAttack == true) {
			return CHECK_TO_CURRENT_SIDE;
		}
		Owner oo = o.oppositeOwner();
		King oppositeKing = (King) getFigure(xyOfKings.get(oo));
		isUnderAttack = oppositeKing.isUnderAttack(this);
		if (isUnderAttack == true) {
			return CHECK_TO_AWAITING_SIDE;
		}
		return CORRECT_MOVE;
		
	}
	
	public boolean isEnPassantPossible(Figure p) {
		boolean isPawnOnRightY = (p.getXY().getY() == (int) (3.5 + 0.5 * p.getRank().getOwner().getDirection()));
		if (isPawnOnRightY == false) {
			return false;
		}
		boolean isLastMovedFigurePawnAndInEnPassantLetalZone = (lastMovedFigure.getRank().getIndex().equals("p") && (Math.abs(endPositions.getY() - startPositions.getY()) == 2) && (Math.abs(endPositions.getX()
				- p.getXY().getX()) == 1));
		return isLastMovedFigurePawnAndInEnPassantLetalZone;
		
	}
	
	public XY xyEnPassantPossible(Figure p) {
		boolean isLastMovedFigurePawnAndInEnPassantLetalZone = isEnPassantPossible(p);
		if (isLastMovedFigurePawnAndInEnPassantLetalZone) {
			return new XY(endPositions.getX(), endPositions.getY() + p.getRank().getOwner().getDirection());
		}
		return null;
	}
	
	public void enPassant(Figure pawnKiller) {
		remove(endPositions);
		move(pawnKiller, endPositions.getX(), endPositions.getY() + pawnKiller.getRank().getOwner().getDirection());
	}
	
	public Figure[][] getBoard() {
		return board;
	}
	
	public XY getEndPositions() {
		return endPositions;
	}
	
	public Figure getFigure(char x, char y) {
		return board[x - 'a'][(Character.digit(y, 10) - 1)];
	}
	
	public Figure getFigure(int x, int y) {
		return board[x][y];
	}
	
	public Figure getFigure(String in) {
		return getFigure(in.charAt(0), in.charAt(1));
	}
	
	public Figure getFigure(XY from) {
		return board[from.getX()][from.getY()];
	}
	
	public Figure getLastMovedFigure() {
		return lastMovedFigure;
	}
	
	public XY getStartPositions() {
		return startPositions;
	}
	
	public XY getXyOfKing(Owner o) {
		return xyOfKings.get(o);
	}
	
	public ArrayList<XY> getXyOfSides(Owner o) {
		return xyOfSides.get(o);
	}
	
	public void initializeGame() {
		xyOfKings.put(Owner.WHITE, new XY(4, 0));
		xyOfKings.put(Owner.BLACK, new XY(4, 7));
		setFigureToPosition(new Rook(new XY(0, 0), Rank.WHITE_ROOK));
		setFigureToPosition(new Knight(new XY(1, 0), Rank.WHITE_KNIGHT));
		setFigureToPosition(new Bishop(new XY(2, 0), Rank.WHITE_BISHOP));
		setFigureToPosition(new Queen(new XY(3, 0), Rank.WHITE_QUEEN));
		setFigureToPosition(new King(xyOfKings.get(Owner.WHITE), Rank.WHITE_KING));
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
		setFigureToPosition(new King(xyOfKings.get(Owner.BLACK), Rank.BLACK_KING));
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
	
	public boolean mate() {
		// TODO make mate
		return false;
	}
	
	public void move(Figure figFrom, int x, int y) {
		startPositions = new XY(figFrom.getXY());
		moveWithoutTrace(figFrom, x, y);
		endPositions = new XY(figFrom.getXY());
		lastMovedFigure = figFrom;
	}
	
	/*
	 * need to make this check after making move
	 */
	
	public void move(Figure figFrom, XY to) {
		move(figFrom, to.getX(), to.getY());
	}
	
	public void moveWithoutTrace(Figure figFrom, int x, int y) {
		board[figFrom.getXY().getX()][figFrom.getXY().getY()] = null;
		figFrom.getXY().setXY(x, y);
		Owner o = figFrom.getRank().getOwner();
		if (figFrom.getXY().equals(xyOfKings.get(o))) {
			xyOfKings.get(o).setXY(x, y);
		}
		board[x][y] = figFrom;
		figFrom.setTouched(true);
	}
	
	public void moveWithoutTrace(Figure figFrom, XY to) {
		moveWithoutTrace(figFrom, to.getX(), to.getY());
		
	}
	
	public boolean promotePawn(Figure pawn, Rank gotRank, XY to) {
		pawn.setRank(gotRank);
		return true;
	}
	
	public void remove(int x, int y) {
		Figure fig = board[x][y];
		xyOfSides.get(fig.getRank().getOwner()).remove(fig.getXY());
		board[x][y] = null;
		
	}
	
	public void remove(XY xy) {
		remove(xy.getX(), xy.getY());
	}
	
	public void setFigureToPosition(Figure fig) {
		xyOfSides.get(fig.getRank().getOwner()).add(fig.getXY());
		board[fig.getXY().getX()][fig.getXY().getY()] = fig;
	}
}
