package project.ilyagorban.model.figures;

import java.util.ArrayList;

import project.ilyagorban.model.Board;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

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
		Figure rook = board.getFigure(this.getXY().getX(), x);
		if (rook != null && rook.isTouched() == false) {
		    boolean isAbleToCastle = true;
		    for (int i = 1; i < Math.abs(stepsX); i++) {
			int newX = this.getXY().getX() + i * direction;
			if (board.getFigure(newX, this.getXY().getY()) == null)
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
