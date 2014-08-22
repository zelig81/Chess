package project.ilyagorban;

import project.ilyagorban.model.*;

public class ChessModel {

	private Figure[][] board;

	public void initializeGame() {
		board = new Figure[8][8];
		board[0][0] = new Rook(new XY(0,0), Rank.WHITE_ROOK	);
		board[1][0] = new Knight(new XY(1,0), Rank.WHITE_KNIGHT	);
		board[2][0] = new Bishop(new XY(2,0), Rank.WHITE_BISHOP	);
		board[3][0] = new King(new XY(3,0), Rank.WHITE_KING	);
		board[4][0] = new Queen(new XY(4,0), Rank.WHITE_QUEEN	);
		board[5][0] = new Bishop(new XY(5,0), Rank.WHITE_BISHOP);
		board[6][0] = new Knight(new XY(6,0), Rank.WHITE_KNIGHT	);
		board[7][0] = new Rook(new XY(7,0), Rank.WHITE_ROOK	);
		board[0][1] = new Pawn(new XY(0,1), Rank.WHITE_PAWN	);
		board[1][1] = new Pawn(new XY(1,1), Rank.WHITE_PAWN	);
		board[2][1] = new Pawn(new XY(2,1), Rank.WHITE_PAWN	);
		board[3][1] = new Pawn(new XY(3,1), Rank.WHITE_PAWN	);
		board[4][1] = new Pawn(new XY(4,1), Rank.WHITE_PAWN	);
		board[5][1] = new Pawn(new XY(5,1), Rank.WHITE_PAWN	);
		board[6][1] = new Pawn(new XY(6,1), Rank.WHITE_PAWN	);
		board[7][1] = new Pawn(new XY(7,1), Rank.WHITE_PAWN	);
		
		board[0][7] = new Rook(new XY(0,7), Rank.BLACK_ROOK	);
		board[1][7] = new Knight(new XY(1,7), Rank.BLACK_KNIGHT	);
		board[2][7] = new Bishop(new XY(2,7), Rank.BLACK_BISHOP	);
		board[3][7] = new King(new XY(3,7), Rank.BLACK_KING	);
		board[4][7] = new Queen(new XY(4,7), Rank.BLACK_QUEEN	);
		board[5][7] = new Bishop(new XY(5,7), Rank.BLACK_BISHOP);
		board[6][7] = new Knight(new XY(6,7), Rank.BLACK_KNIGHT	);
		board[7][7] = new Rook(new XY(7,7), Rank.BLACK_ROOK	);
		board[0][6] = new Pawn(new XY(0,6), Rank.BLACK_PAWN	);
		board[1][6] = new Pawn(new XY(1,6), Rank.BLACK_PAWN	);
		board[2][6] = new Pawn(new XY(2,6), Rank.BLACK_PAWN	);
		board[3][6] = new Pawn(new XY(3,6), Rank.BLACK_PAWN	);
		board[4][6] = new Pawn(new XY(4,6), Rank.BLACK_PAWN	);
		board[5][6] = new Pawn(new XY(5,6), Rank.BLACK_PAWN	);
		board[6][6] = new Pawn(new XY(6,6), Rank.BLACK_PAWN	);
		board[7][6] = new Pawn(new XY(7,6), Rank.BLACK_PAWN	);
		
	}
	
	public Figure[][] getBoard(){
		return board;
	}

	public boolean move(String input, Owner o) {
		String[] tokens = input.split("-");
		XY from = new XY(tokens[0]);
		XY to = new XY(tokens[1]);
		//TODO check possibility of move and correctness of input
		if (board[from.getX()][from.getY()] != null && board[from.getX()][from.getY()].getRank().getOwner()== o){
			return true;
		}
		else{
			return false;
		}
		
		
	}

}
