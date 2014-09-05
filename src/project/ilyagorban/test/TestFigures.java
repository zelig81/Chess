package project.ilyagorban.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.ilyagorban.Main;
import project.ilyagorban.model.Board;
import project.ilyagorban.model.XY;
import project.ilyagorban.model.figures.Figure;
import project.ilyagorban.view.ChessViewTest;

public class TestFigures extends Main {
    ChessViewTest cvt;
    Board b;
    Figure fig;
    ArrayList<XY> pm;

    @Before
    public void setUp() throws Exception {
	prepareGame("test");
	cvt = (ChessViewTest) Main.cv.getView();
    }

    @After
    public void tearDown() throws Exception {
	cvt = null;
	b = null;
	fig = null;
	pm = null;
    }

    public static void main(String[] args) {
	TestFigures tf = new TestFigures();
	try {
	    tf.testKnight();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testBishop() throws Exception {
	setUp();
	cvt.addMove("e2e4");
	cvt.addMove("exit");
	Main.cc.start();
	b = Main.cm.getBoardObject();
	fig = b.getFigure("f1");
	assertEquals(
		"bishop at [f1] should be able to move for 5 squares. its get "
			+ fig.getPossibleMoves(b), fig.getPossibleMoves(b)
			.size(), 5);
	tearDown();

	setUp();
	cvt.addMove("e2e4");
	cvt.addMove("e7e5");
	cvt.addMove("f1e2");
	cvt.addMove("exit");
	Main.cc.start();
	b = Main.cm.getBoardObject();
	fig = b.getFigure("e2");
	assertEquals(
		"bishop at [e2] should be able to move for 8 squares. its get "
			+ fig.getPossibleMoves(b), fig.getPossibleMoves(b)
			.size(), 8);
	tearDown();

    }

    @Test
    public void testKnight() throws Exception {
	setUp();
	cvt.addMove("e2e4");
	cvt.addMove("exit");
	Main.cc.start();
	b = Main.cm.getBoardObject();
	fig = b.getFigure("g1");
	assertEquals(
		"knight at [g1] should be able to move for 3 squares. its get "
			+ fig.getPossibleMoves(b), fig.getPossibleMoves(b)
			.size(), 3);

	fig = b.getFigure("b1");
	assertEquals(
		"knight at [b1] should be able to move for 2 squares. its get "
			+ fig.getPossibleMoves(b), fig.getPossibleMoves(b)
			.size(), 2);
	tearDown();

	setUp();
	cvt.addMove("e2e4");
	cvt.addMove("b8c6");
	cvt.addMove("exit");
	Main.cc.start();
	b = Main.cm.getBoardObject();
	fig = b.getFigure("c6");
	ArrayList<XY> pm = fig.getPossibleMoves(b);
	assertEquals(
		"knight at [c6] should be able to move for 5 squares. its get "
			+ pm, pm.size(), 5);
	tearDown();
    }

    @Test
    public void testQueen() throws Exception {
	setUp();
	cvt.addMove("e2e4");
	cvt.addMove("c7c5");
	cvt.addMove("d1f3");
	cvt.addMove("d8b6");
	cvt.addMove("exit");
	Main.cc.start();
	b = Main.cm.getBoardObject();
	fig = b.getFigure("f3");
	pm = fig.getPossibleMoves(b);
	assertEquals(
		"queen at [f3] should be able to move for 15 squares. its get "
			+ pm, pm.size(), 15);

	fig = b.getFigure("b6");
	pm = fig.getPossibleMoves(b);
	assertEquals(
		"queen at [b6] should be able to move for 14 squares. its get "
			+ pm, pm.size(), 14);
	tearDown();

    }
}
