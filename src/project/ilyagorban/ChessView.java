package project.ilyagorban;

public class ChessView {
	
	public ChessView (){
		this("console");
	}

	public ChessView(String typeOfView) {
		if (typeOfView == null)
			throw new IllegalArgumentException("Wrong argument for ChessView constructor");
		switch (typeOfView){
		case "console":
			break;
		case "gui":
			break;
		default:
				break;
		}
	}

}
