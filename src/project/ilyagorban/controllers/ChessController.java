package project.ilyagorban.controllers;

import project.ilyagorban.model.ChessModel;
import project.ilyagorban.model.Owner;
import project.ilyagorban.view.Visualizable;
import static project.ilyagorban.model.ChessModel.*;

public class ChessController {

    private ChessModel cm;
    private Visualizable cv;

    public ChessController(ChessModel cm, Visualizable cv) {
	this.cm = cm;
	this.cv = cv;
    }

    public void start() {
	cm.initializeGame();
	Owner currentOwner = Owner.WHITE;
	while (true) {
	    String input = cv.showBoard(cm.getBoard(), currentOwner);

	    if ("exit".equals(input))
		break;

	    int returnMessage = cm.move(input, currentOwner);
	    if (returnMessage == DRAW) {
		cv.getMessageToView("Draw!!!!");
		break;
	    } else if (returnMessage == CHECKMATE_TO_WHITE) {
		cv.getMessageToView("Black wins!!!!!");
		break;
	    } else if (returnMessage == CHECKMATE_TO_BLACK) {
		cv.getMessageToView("White wins!!!!!");
		break;
	    } else {
		switch (returnMessage) {
		case CHECK_TO_AWAITING_SIDE:
		    if (currentOwner == Owner.BLACK) {
			cv.getMessageToView("White make check");
		    } else {
			cv.getMessageToView("Black make check");
		    }
		    currentOwner = currentOwner.oppositeOwner();
		    break;
		case CORRECT_MOVE:
		    currentOwner = currentOwner.oppositeOwner();
		    break;
		case CASTLING:
		    currentOwner = currentOwner.oppositeOwner();
		    break;
		case PAWN_PROMOTION:
		    boolean success = false;
		    while (success == false) {
			String promotion = cv
				.getInput("Your pawn is ready to be promoted. To which figure you want to promote it (r)ook/k(n)ight/(b)ishop/(q)ueen?");
			success = cm.promotePawn(input, promotion);
		    }
		    currentOwner = currentOwner.oppositeOwner();
		    break;
		case CHECK_TO_CURRENT_SIDE:
		    cv.getMessageToView("Incorrect move - you are under check");
		    break;
		case INCORRECT_INPUT:
		    cv.setMessage("incorrect input string");
		    break;
		case DONT_TOUCH_NOT_YOUR_FIGURE_TO_MOVE:
		    cv.setMessage("there is no " + currentOwner.name()
			    + "'s figure on the start coordinate");
		    break;
		case INCORRECT_MOVE:
		    cv.setMessage("incorrect move for this figure");
		    break;
		default:
		    cv.setMessage("Should not come to here");
		    break;
		}
	    }
	}
    }

}
