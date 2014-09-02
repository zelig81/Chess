package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♜♖
public class Rook extends Figure {

    public Rook(XY p, Rank r) {
	super(p, r);
	setDirections(new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } });
	setMoveLen(8);
    }

}
