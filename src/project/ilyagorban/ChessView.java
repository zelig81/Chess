package project.ilyagorban;

import java.util.Scanner;

import project.ilyagorban.model.CoordX;
import project.ilyagorban.model.Figure;

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

	public String showBoard(Figure[][] figures) {
		System.out.println("  a  b  c  d  e  f  g  h");
		for(int y = 7; y >= 0; y--){
			System.out.print(y + 1);
			for (int x = 7; x >= 0; x--){
				if (figures[x][y] != null){
					System.out.print(figures[x][y]);
				}
				else{
					System.out.print("\u2680");
				}
			}
			System.out.print("\n");
		}
		System.out.println("move your figure:");
		String input = (new Scanner(System.in)).nextLine();
		return input;
	}

}
