package project.ilyagorban.view;

import java.util.ArrayList;

import project.ilyagorban.model.Owner;
import project.ilyagorban.model.figures.Figure;

public class ChessViewTest implements Visualizable {
    private ArrayList<String> moves = new ArrayList<>();

    private int move;

    public ArrayList<String> getMoves() {
	return moves;
    }

    public void addMove(String str) {
	moves.add(str);
    }

    @Override
    public String getInput(String string) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void getMessageToView(String string) {
	// TODO Auto-generated method stub

    }

    public void setInput(ArrayList<String> input) {
	this.moves = input;
    }

    @Override
    public void setMessage(String message) {
	// TODO Auto-generated method stub

    }

    public void resetMove() {
	this.move = 0;
    }

    @Override
    public String showBoard(Figure[][] figures, Owner currentOwner) {
	String output = moves.get(move);
	move++;
	return output;
    }
}