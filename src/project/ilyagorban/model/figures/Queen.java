package project.ilyagorban.model.figures;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;

// ♛♕ figures
public class Queen extends Figure {

    public Queen(XY p, Rank r) {
	super(p, r);
	setDirections(directionsOfQueen);
	setMoveLen(8);
    }

}
