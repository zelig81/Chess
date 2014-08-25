package project.ilyagorban.controllers;

import project.ilyagorban.model.ChessModel;
import project.ilyagorban.model.Owner;
import project.ilyagorban.view.ChessView;

public class ChessController {

	private ChessModel cm;
	private ChessView cv;

	public ChessController(ChessModel cm, ChessView cv) {
		this.cm = cm;
		this.cv = cv;
	}
	
	public void start(){
		cm.initializeGame();
		Owner currentOwner = Owner.WHITE;
		cycle: while(true){
			String input = cv.showBoard(cm.getBoard(), currentOwner);
			if ("exit".equals(input))
				break;
			switch (cm.move(input, currentOwner)){
			case "done":
				currentOwner = Owner.changeOwner(currentOwner);
				break;
			case "incorrect input":
				cv.setMessage("incorrect input string");
				break;
			case "there is not owner's figure":
				cv.setMessage("no owner's figure");
				break;
			case "incorrect move for this figure":
				cv.setMessage("incorrect move for this figure");
				break;
			case "king removing":
				cv.setMessage(currentOwner + " wins!!!!!");
				break cycle;
			}
		}
	}

}
