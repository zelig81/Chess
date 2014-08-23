package project.ilyagorban;

import project.ilyagorban.model.Owner;

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
				cv.message("incorrect input");
				break;
			case "no owner's figure":
				cv.message("no owner's figure");
				break;
			case "incorrect move":
				cv.message("incorrect move");
				break;
			case "king removing":
				cv.message(currentOwner + " wins!!!!!");
				break cycle;
			}
		}
	}

}
