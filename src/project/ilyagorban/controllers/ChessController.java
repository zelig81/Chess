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
	    if (returnMessage == CHECKMATE_TO_WHITE) {
		// TODO make controller check/mate
		cv.getMessageToView("Black wins!!!!!");
		break;
	    } else if (returnMessage == CHECKMATE_TO_BLACK) {
		cv.getMessageToView("White wins!!!!!");
		break;
	    } else if (returnMessage == CHECK_TO_BLACK) {
		cv.getMessageToView("White make check");
	    } else if (returnMessage == CHECK_TO_WHITE) {
		cv.getMessageToView("Black make check");
	    } else {
		switch (returnMessage) {
		case CORRECT_MOVE:
		    currentOwner = Owner.changeOwner(currentOwner);
		    break;
		case CASTLING:
		    currentOwner = Owner.changeOwner(currentOwner);
		    break;
		case PAWN_PROMOTION:
		    boolean success = false;
		    while (success == false) {
			String promotion = cv
				.getInput("Your pawn is ready to be promoted. To which figure you want to promote it (r)ook/k(n)ight/(b)ishop/(q)ueen?");
			success = cm.promotePawn(input, promotion);
		    }
		    currentOwner = Owner.changeOwner(currentOwner);
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
		}
	    }
	}
    }

}