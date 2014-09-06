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
	setDirections(directionsOfQueen);
	setMoveLen(1);
    }

    @Override
    public ArrayList<XY> getPossibleMoves(Board board) {
	ArrayList<XY> output = super.getPossibleMoves(board);

	if (this.isTouched() == false) {
	    // left and right castling check:
	    for (int x = 0; x <= 7; x = x + 7) {
		int stepsX = x - this.getXY().getX();
		int direction = stepsX / Math.abs(stepsX);
		Figure rook = board.getFigure(x, this.getXY().getY());
		if (rook != null && rook.isTouched() == false) {
		    boolean isAbleToCastle = true;
		    for (int i = 1; i < Math.abs(stepsX); i++) {
			int newX = this.getXY().getX() + i * direction;
			boolean isEmpty = board.getFigure(newX, this.getXY()
				.getY()) == null;
			if (isEmpty) {
			    continue;
			} else {
			    isAbleToCastle = false;
			    break;
			}
		    }
		    if (isAbleToCastle == true)
			output.add(new XY(this.getXY().getX() + direction * 2,
				this.getXY().getY()));
		}
	    }
	}

	return output;
    }

    @Override
    public int checkMove(Board board, XY to) {
	int output = super.checkMove(board, to);
	if (output < CORRECT_MOVE)
	    return output;
	else {
	    if (Math.abs(this.getXY().getX() - to.getX()) == 2)
		output = CASTLING;
	}
	return output;
    }

    public boolean isUnderAttack(Board board) {
	boolean output = false;
	for (int i = 0; i < 3; i++) {
	    if (output == true)
		break;
	    this.setDirections(fourFigureDirections[i]);
	    this.setMoveLen(fourFigureMoveLen[i]);
	    ArrayList<XY> pm = this.getPossibleMoves(board);
	    for (XY xy : pm) {
		Figure fig = board.getFigure(xy);
		if (fig != null) {
		    for (String s : fourFigureIndex[i]) {
			if (fig.getRank().getIndex().equals(s)) {
			    output = true;
			    break;
			}
		    }
		}
	    }
	}
	if (output == false) {
	    ArrayList<XY> ppa = this.getPawnPossibleAttack(board);
	    for (XY xy : ppa) {
		Figure fig = board.getFigure(xy);
		if (fig.getRank().getIndex() == "p") {
		    output = true;
		    break;
		}
	    }
	}
	this.setDirections(directionsOfQueen);
	this.setMoveLen(1);
	return output;

    }

}
