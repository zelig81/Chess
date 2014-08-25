package project.ilyagorban;

import java.util.Scanner;

import project.ilyagorban.model.Figure;
import project.ilyagorban.model.Owner;

public class ChessView {
	
	private Scanner sc = new Scanner(System.in);
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

	public String showBoard(Figure[][] figures, Owner currentOwner) {
		System.out.println("---------------------------------------");
		System.out.println("  a  b  c d  e  f  g  h");
		for(int y = 7; y >= 0; y--){
			System.out.print(y + 1);
			for (int x = 0; x <= 7; x++){
				Figure fig = figures[x][y];
				if (fig != null){
					System.out.print(fig);
				}
				else{
					System.out.print(((x+y)% 2 ==1)? "\u25a0" : "\u25a1" );
				}
			}
			System.out.print("\n");
		}
		System.out.println(currentOwner + " your move (for example [e2 e4]) to enter [exit] to quit the game:");
		String input = sc.nextLine();
		return input;
	}

	public void message(String string) {
		System.out.println(string);
		
	}

}
