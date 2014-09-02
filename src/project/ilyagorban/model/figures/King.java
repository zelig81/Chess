package project.ilyagorban.model.figures;

import java.util.ArrayList;

import project.ilyagorban.model.Board;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♚♔ figures
public class King extends Figure {

    public King(XY p, Rank r) {
	super(p, r);
	setDirections(new int[][] { { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 },
		{ -1, 0 }, { -1, -1 }, { 0, -1 }, { -1, 1 } });
	setMoveLen(1);
    }

    @Override
    public ArrayList<XY> getPossibleMoves(Board board) {
	ArrayList<XY> output = super.getPossibleMoves(board);
	if (this.isTouched() == false) {
	    // left and right castling check:
	    for (int y = 0; y <= 7; y = y + 7) {
		int steps = this.getXY().getY() - y;
		int direction = steps / Math.abs(steps);
		Figure rook = board.getFigure(this.getXY().getX(), y);
		if (rook != null && rook.isTouched() == false) {
		    boolean isAbleToCastle = true;
		    for (int i = 1; i < Math.abs(steps); i++) {
			if (board.getFigure(this.getXY().getX(), this.getXY()
				.getY() + i * direction) == null)
			    continue;
			else
			    isAbleToCastle = false;
		    }
		    if (isAbleToCastle == true)
			output.add(new XY(this.getXY().getX(), this.getXY()
				.getY() + direction * 2));
		}
	    }
	}
	return output;
    }

}
