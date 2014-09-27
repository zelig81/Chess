package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♘♞ figures
public class Knight extends Figure {

    public Knight(XY p, Rank r) {
	super(p, r);
	setDirections(directionsOfKnight);
	setMoveLen(1);
    }

}
