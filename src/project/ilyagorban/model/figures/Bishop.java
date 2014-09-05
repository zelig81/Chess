package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♗♝figures
public class Bishop extends Figure {

    public Bishop(XY p, Rank r) {
	super(p, r);
	setDirections(directionsOfBishop);
	setMoveLen(8);
    }

}
