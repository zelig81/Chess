package project.ilyagorban.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static project.ilyagorban.model.ChessModel.*;
import project.ilyagorban.model.figures.Figure;
import project.ilyagorban.model.figures.King;
import project.ilyagorban.model.figures.Pawn;

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
		startGamePositions.add("wra1");
		startGamePositions.add("wnb1");
		startGamePositions.add("wbc1");
		startGamePositions.add("wqd1");
		startGamePositions.add("wke1");
		startGamePositions.add("wbf1");
		startGamePositions.add("wng1");
		startGamePositions.add("wrh1");
		startGamePositions.add("wpa2");
		startGamePositions.add("wpb2");
		startGamePositions.add("wpc2");
		startGamePositions.add("wpd2");
		startGamePositions.add("wpe2");
		startGamePositions.add("wpf2");
		startGamePositions.add("wpg2");
		startGamePositions.add("wph2");
		
		startGamePositions.add("bra8");
		startGamePositions.add("bnb8");
		startGamePositions.add("bbc8");
		startGamePositions.add("bqd8");
		startGamePositions.add("bke8");
		startGamePositions.add("bbf8");
		startGamePositions.add("bng8");
		startGamePositions.add("brh8");
		startGamePositions.add("bpa7");
		startGamePositions.add("bpb7");
		startGamePositions.add("bpc7");
		startGamePositions.add("bpd7");
		startGamePositions.add("bpe7");
		startGamePositions.add("bpf7");
		startGamePositions.add("bpg7");
		startGamePositions.add("bph7");
	}
	
	private Board() {
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
	
	public int assessMateOrStalemate(Figure fig, XY to, int checkOutput) {
		int output = CORRECT_MOVE;
		XY origXY = fig.getXY();
		Owner o = fig.getRank().getOwner();
		moveWithoutTrace(fig, to);
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
		return output;
		
	}
	
	public int assessPositions(Figure fig, XY to, int correctMoveResult) {
		// TODO refactor assessPositions
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
	
	public void castling(Figure king, XY to) {
		int rookX = (to.getX() == 6) ? 7 : 0;
		int kingY = king.getXY().getY();
		int newRookX = (to.getX() == 6) ? 5 : 3;
		move(king, to);
		move(getFigure(rookX, kingY), newRookX, kingY);
		
	}
	
	public int check(Figure figFrom, XY to) {
		Owner w = Owner.WHITE;
		Owner b = Owner.BLACK;
		Owner o = figFrom.getRank().getOwner();
		XY origXY = new XY(figFrom.getXY());
		int result = CORRECT_MOVE;
		moveWithoutTrace(figFrom, to);
		
		if (to.equals(xyOfKings.get(o))) {
			boolean isKingsNotOnNeighborSquares = Math.abs(xyOfKings.get(w).getX() - xyOfKings.get(b).getX()) > 1 && Math.abs(xyOfKings.get(w).getY() - xyOfKings.get(b).getY()) > 1;
			if (isKingsNotOnNeighborSquares == false) {
				result = INCORRECT_MOVE;
			} else {
				result = checkFromKingsPointOfView(o);
			}
		} else {
			result = checkFromKingsPointOfView(o);
		}
		
		moveWithoutTrace(figFrom, origXY);
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
	
	protected int checkInAssessMateOrStalemate(XY xy) {
		int output = CORRECT_MOVE;
		Figure figFromOtherSide = getFigure(xy);
		ArrayList<XY> possibleMoves = getPossibleMoves(figFromOtherSide);
		for (XY figureFromOtherSidePossibleMove : possibleMoves) {
			output = check(figFromOtherSide, figureFromOtherSidePossibleMove);
			if (output != CHECK_TO_CURRENT_SIDE) {
				break;
			}
		}
		return output;
	}
	
	public int checkMove(XY from, XY to) {
		Figure figTo = getFigure(to);
		Figure figFrom = getFigure(from);
		int output = INCORRECT_MOVE;
		
		boolean isEndPointEmptyOrEnemy = (figTo == null || figTo.isEnemy(figFrom));
		if (isEndPointEmptyOrEnemy == false)
			return OBSTACLE_ON_WAY;
		
		ArrayList<XY> pm = getPossibleMoves(figFrom);
		if (pm.contains(to) == true) {
			output = CORRECT_MOVE;
		} else {
			ArrayList<XY> psm = getPossibleSpecialMove(figFrom);
			if (psm.contains(to) == true) {
				output = figFrom.getSpecialCorrectMoveName(to);
			} else {
				output = INCORRECT_MOVE;
			}
		}
		
		if (output >= CORRECT_MOVE) {
			output = assessPositions(figFrom, to, output);
		}
		return output;
		
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
		if (from == null) {
			return null;
		}
		return board[from.getX()][from.getY()];
	}
	
	public Figure getLastMovedFigure() {
		return lastMovedFigure;
	}
	
	public ArrayList<XY> getPawnPossibleAttack(XY from, XY to) {
		// TODO remove pawn possible attack
		// ArrayList<XY> output = new ArrayList<>(2);
		// int direction = this.getRank().getOwner().getDirection();
		// if (this.getXY().getY() > 0 && this.getXY().getY() < 7) {
		// if (this.getXY().getX() != 7) {
		// Figure target = board.getFigure(this.getXY().getX() + 1,
		// this.getXY().getY() + direction);
		// boolean isRemovable = (target != null && this.isEnemy(target));
		// if (isRemovable == true)
		// output.add(target.getXY());
		// }
		// if (this.getXY().getX() != 0) {
		// Figure target = board.getFigure(this.getXY().getX() - 1,
		// this.getXY().getY() + direction);
		// boolean isRemovable = (target != null && this.isEnemy(target));
		// if (isRemovable == true)
		// output.add(target.getXY());
		// }
		// }
		// return output;
		return null;
	}
	
	private void getPossibleKillOrMoveForOneDirection(ArrayList<XY> list, Figure figFrom, int[] dir, boolean isKillAction) {
		boolean isPawn = figFrom instanceof Pawn;
		int thisX = figFrom.getXY().getX();
		int thisY = figFrom.getXY().getY();
		int i = 1;
		while (true) {
			if (i > figFrom.getMoveLen())
				break;
			int newX = thisX + dir[0] * i;
			int newY = thisY + dir[1] * i;
			XY xy = XY.getNewXY(newX, newY);
			if (xy == null) {
				break;
			}
			i++;
			Figure figTo = getFigure(newX, newY);
			if (figTo == null) {
				boolean isPawnKiller = (isPawn == true && isKillAction == true);
				if (isPawnKiller == false) {
					list.add(xy);
					continue;
				}
			} else if (figFrom.isEnemy(figTo)) {
				boolean isPawnNotKiller = (isPawn == true && isKillAction == false);
				if (isPawnNotKiller == false) {
					list.add(xy);
				}
			}
			break;
		}
		
	}
	
	public ArrayList<XY> getPossibleMoves(Figure figFrom) {
		ArrayList<XY> output = new ArrayList<>();
		// cycle of directions
		for (int[] dir : figFrom.getKillDirections()) {
			getPossibleKillOrMoveForOneDirection(output, figFrom, dir, true);
		}
		if (figFrom instanceof Pawn) {
			for (int[] dir : figFrom.getMoveDirections()) {
				getPossibleKillOrMoveForOneDirection(output, figFrom, dir, false);
			}
		}
		return output;
	}
	
	private ArrayList<XY> getPossibleSpecialMove(Figure figFrom) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
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
	
	/*
	 * need to make this check after making move
	 */
	
	public boolean initializeGame() {
		numberOfMove = 0;
		log = new HashSet<>();
		numberOfFiftyRule = 0;
		return initializeGame(numberOfMove, log, numberOfFiftyRule, startGamePositions);
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
			if (output == false) {
				break;
			}
		}
		return output;
		
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
	
	public void move(Figure figFrom, int x, int y) {
		startPositions = new XY(figFrom.getXY());
		moveWithoutTrace(figFrom, x, y);
		endPositions = new XY(figFrom.getXY());
		lastMovedFigure = figFrom;
	}
	
	public void move(Figure figFrom, XY to) {
		move(figFrom, to.getX(), to.getY());
		figFrom.setTouched(true);
	}
	
	public void moveWithoutTrace(Figure figFrom, int x, int y) {
		board[figFrom.getXY().getX()][figFrom.getXY().getY()] = null;
		figFrom.getXY().setXY(x, y);
		Owner o = figFrom.getRank().getOwner();
		if (figFrom.getXY().equals(xyOfKings.get(o))) {
			xyOfKings.get(o).setXY(x, y);
		}
		board[x][y] = figFrom;
	}
	
	public void moveWithoutTrace(Figure figFrom, XY to) {
		moveWithoutTrace(figFrom, to.getX(), to.getY());
		
	}
	
	public boolean promotePawn(Figure pawn, Rank gotRank, XY to) {
		pawn.setRank(gotRank);
		return true;
	}
	
	/*
	 * already known that to current side this move is correct
	 * 
	 * if opposite side could move without further check it's asserted that it's
	 * not mate or stalemate so it's correct move
	 */
	
	public void remove(int x, int y) {
		Figure fig = board[x][y];
		xyOfSides.get(fig.getRank().getOwner()).remove(fig.getXY());
		board[x][y] = null;
		
	}
	
	public void remove(XY xy) {
		remove(xy.getX(), xy.getY());
	}
	
	public void resetNumberOfFiftyRule() {
		numberOfFiftyRule = 0;
	}
	
	public void saveMove() {
		numberOfMove++;
		HashSet<String> thisMove = new HashSet<>();
		thisMove.add(getFigure(xyOfKings.get(Owner.WHITE)).toLog());
		thisMove.add(getFigure(xyOfKings.get(Owner.BLACK)).toLog());
		for (XY xy : xyOfSides.get(Owner.WHITE)) {
			thisMove.add(getFigure(xy).toLog());
		}
		for (XY xy : xyOfSides.get(Owner.BLACK)) {
			thisMove.add(getFigure(xy).toLog());
		}
		log.add(thisMove);
	}
	
	public void setFigureToPosition(Figure fig) {
		xyOfSides.get(fig.getRank().getOwner()).add(fig.getXY());
		board[fig.getXY().getX()][fig.getXY().getY()] = fig;
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
	
	public XY xyEnPassantPossible(Figure p) {
		boolean isLastMovedFigurePawnAndInEnPassantLetalZone = isEnPassantPossible(p);
		if (isLastMovedFigurePawnAndInEnPassantLetalZone) {
			return XY.getNewXY(endPositions.getX(), endPositions.getY() + p.getRank().getOwner().getDirection());
		}
		return null;
	}
}
