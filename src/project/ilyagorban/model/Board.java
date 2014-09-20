package project.ilyagorban.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static project.ilyagorban.model.ChessModel.*;
import project.ilyagorban.model.figures.Figure;
import project.ilyagorban.model.figures.King;

public class Board {
	public static Board getInstance() {
		return new Board();
	}
	
	private Figure[][] board = new Figure[8][8];
	private HashMap<Owner, ArrayList<XY>> xyOfSides;
	private HashSet<HashSet<String>> log;
	private HashMap<Owner, XY> xyOfKings;
	private XY startPositions;
	private XY endPositions;
	private Figure lastMovedFigure;
	private int numberOfMove;
	private int numberOfFiftyRule;
	private static final ArrayList<String> startGamePositions = new ArrayList<>();
	static {
		startGamePositions.add("wr00");
		startGamePositions.add("wn10");
		startGamePositions.add("wb20");
		startGamePositions.add("wq30");
		startGamePositions.add("wk40");
		startGamePositions.add("wb50");
		startGamePositions.add("wn60");
		startGamePositions.add("wr70");
		startGamePositions.add("wp01");
		startGamePositions.add("wp11");
		startGamePositions.add("wp21");
		startGamePositions.add("wp31");
		startGamePositions.add("wp41");
		startGamePositions.add("wp51");
		startGamePositions.add("wp61");
		startGamePositions.add("wp71");
		
		startGamePositions.add("br07");
		startGamePositions.add("bn17");
		startGamePositions.add("bb27");
		startGamePositions.add("bq37");
		startGamePositions.add("bk47");
		startGamePositions.add("bb57");
		startGamePositions.add("bn67");
		startGamePositions.add("br77");
		startGamePositions.add("bp06");
		startGamePositions.add("bp16");
		startGamePositions.add("bp26");
		startGamePositions.add("bp36");
		startGamePositions.add("bp46");
		startGamePositions.add("bp56");
		startGamePositions.add("bp66");
		startGamePositions.add("bp76");
	}
	
	private Board() {
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
	
	public boolean initializeGame(int numberOfMove, HashSet<HashSet<String>> log, int numberOfFiftyRule, ArrayList<String> startGamePositions) {
		this.numberOfMove = numberOfMove;
		this.log = log;
		boolean output = true;
		xyOfKings = new HashMap<>();
		xyOfSides = new HashMap<>();
		xyOfSides.put(Owner.WHITE, new ArrayList<XY>());
		xyOfSides.put(Owner.BLACK, new ArrayList<XY>());
		for (String startGamePosition : startGamePositions) {
			output = setFigureToPosition(startGamePosition);
			System.out.println(output + " is result of setFigureToPosition");
			if (output == false) {
				break;
			}
		}
		return output;
		
	}
	
	public boolean setFigureToPosition(String startGamePosition) {
		Figure fig = Figure.newInstance(startGamePosition);
		if (fig == null) {
			return false;
		}
		boolean isXYAlreadyPresent = xyOfSides.get(Owner.BLACK).contains(fig.getXY()) || xyOfSides.get(Owner.WHITE).contains(fig.getXY()) || xyOfKings.containsValue(fig.getXY());
		if (isXYAlreadyPresent == true) {
			return false;
		}
		if (fig instanceof King) {
			if (xyOfKings.get(fig.getRank().getOwner()) != null) {
				return false;
			}
			xyOfKings.put(fig.getRank().getOwner(), fig.getXY());
		}
		setFigureToPosition(fig);
		return true;
	}
	
	public boolean initializeGame() {
		numberOfMove = 0;
		log = new HashSet<>();
		numberOfFiftyRule = 0;
		return initializeGame(numberOfMove, log, numberOfFiftyRule, startGamePositions);
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
	
	public int assessPositions(Figure fig, XY to) {
		int output = check(fig, to);
		if (output >= CORRECT_MOVE) {
			if (output != CHECK_TO_AWAITING_SIDE) {
				output = assessDraw(fig, to);
			}
			
			if (output < DRAW) {
				output = assessMateOrStalemate(fig, to, output);
			}
		}
		return output;
	}
	
	/*
	 * already known that to current side this move is correct
	 * 
	 * if opposite side could move without further check it's asserted that it's
	 * not mate or stalemate so it's correct move
	 */
	
	public int assessMateOrStalemate(Figure fig, XY to, int checkOutput) {
		int output = CORRECT_MOVE;
		XY origXY = fig.getXY();
		Owner o = fig.getRank().getOwner();
		moveWithoutTrace(fig, to);
		// TODO add assessment for king of other side
		for (XY xy : xyOfSides.get(o.oppositeOwner())) {
			output = checkInAssessMateOrStalemate(xy);
			if (output != CHECK_TO_CURRENT_SIDE)
				break;
		}
		moveWithoutTrace(fig, origXY);
		if (output == CHECK_TO_CURRENT_SIDE) {
			// mate or stalemate to awaiting side
			if (checkOutput == CORRECT_MOVE) {
				output = DRAW_STALEMATE;
			} else {
				output = (o == Owner.WHITE) ? CHECKMATE_TO_BLACK : CHECKMATE_TO_WHITE;
			}
		}
		// correct move
		return output;
		
	}
	
	protected int checkInAssessMateOrStalemate(XY xy) {
		int output = CORRECT_MOVE;
		Figure figFromOtherSide = getFigure(xy);
		ArrayList<XY> possibleMoves = figFromOtherSide.getPossibleMoves(this);
		for (XY figureFromOtherSidePossibleMove : possibleMoves) {
			output = check(figFromOtherSide, figureFromOtherSidePossibleMove);
			if (output != CHECK_TO_CURRENT_SIDE) {
				break;
			}
		}
		return output;
	}
	
	protected int assessDraw(Figure fig, XY to) {
		if (numberOfFiftyRule >= 50) {
			return DRAW_50_RULE;
		} else if (log.size() < numberOfMove - 3) {
			return DRAW_3_FOLD_REPETITION;
		} else if (assessImpossibilityOfMate() == true) {
			return DRAW_IMPOSSIBILITY_OF_MATE;
		} else
			return CORRECT_MOVE;
	}
	
	protected boolean assessImpossibilityOfMate() {
		int importanceSum = 0;
		int[] numFigures = { 0, 0 };
		boolean isAllFiguresBesidesKingAreBishopsAndSameColor = true;
		boolean isFirstBishop = true;
		int colour = -1;
		for (int i = 0; i < 2; i++) {
			Owner o = Owner.values()[i];
			for (XY xy : xyOfSides.get(o)) {
				Figure fig = getFigure(xy);
				Rank r = fig.getRank();
				int importance = r.getImportance();
				if (importance != 2)
					return false;
				
				numFigures[i]++;
				
				if (isAllFiguresBesidesKingAreBishopsAndSameColor == true && r.getIndex().equals("b") == false) {
					isAllFiguresBesidesKingAreBishopsAndSameColor = false;
				}
				if (isAllFiguresBesidesKingAreBishopsAndSameColor == true) {
					if (isFirstBishop == true) {
						colour = (fig.getXY().getX() + fig.getXY().getY()) % 2;
						isFirstBishop = false;
					} else {
						int newColour = (fig.getXY().getX() + fig.getXY().getY()) % 2;
						if (newColour != colour) {
							isAllFiguresBesidesKingAreBishopsAndSameColor = false;
						}
					}
				}
				importanceSum += importance;
			}
		}
		
		if (importanceSum <= 1) {
			// both sides have nothing or a bishop or a knight
			return true;
		} else if (isAllFiguresBesidesKingAreBishopsAndSameColor == true && (numFigures[0] * numFigures[1] != 0)) {
			// All Figures Besides the King Are Bishops And Same Color and each
			// side has at least one of them
			return true;
		} else
			return false;
		
	}
	
	public void saveMove() {
		numberOfMove++;
		HashSet<String> thisMove = new HashSet<>();
		thisMove.add(getStringRepresentationOfFigure(xyOfKings.get(Owner.WHITE)));
		thisMove.add(getStringRepresentationOfFigure(xyOfKings.get(Owner.BLACK)));
		for (XY xy : xyOfSides.get(Owner.WHITE)) {
			thisMove.add(getStringRepresentationOfFigure(xy));
		}
		for (XY xy : xyOfSides.get(Owner.BLACK)) {
			thisMove.add(getStringRepresentationOfFigure(xy));
		}
		log.add(thisMove);
	}
	
	private String getStringRepresentationOfFigure(XY xy) {
		Figure fig = getFigure(xy);
		return fig.toString() + xy.toString();
	}
	
	public void resetNumberOfFiftyRule() {
		numberOfFiftyRule = 0;
	}
}
