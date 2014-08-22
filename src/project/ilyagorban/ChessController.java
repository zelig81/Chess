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
		while(true){
			String input = cv.showBoard(cm.getBoard());
			cm.move(input, currentOwner);
			currentOwner = Owner.changeOwner(currentOwner);
		}
	}

}
