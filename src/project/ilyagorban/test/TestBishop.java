package project.ilyagorban.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.ilyagorban.model.Board;
import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;
import project.ilyagorban.model.figures.Bishop;

public class TestBishop {
    XY xy = new XY(0, 0);
    Bishop b = new Bishop(xy, Rank.WHITE_BISHOP);
    Board board = new Board();

    @Before
    public void setUp() throws Exception {
	board.initializeGame();
	board.move(b, xy);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetPossibleMoves() throws Exception {
	xy.setXY(0, 0);
	setUp();
	assertEquals(
		"bishop moves for " + xy + " should be 0 get "
			+ b.getPossibleMoves(board), b.getPossibleMoves(board)
			.size(), 0);
	tearDown();

	xy.setXY(3, 3);
	setUp();
	assertEquals(
		"bishop moves for " + xy + " should be 8 get "
			+ b.getPossibleMoves(board), b.getPossibleMoves(board)
			.size(), 8);
	tearDown();

	xy.setXY(2, 3);
	setUp();
	assertEquals(
		"bishop moves for " + xy + " should be 7 get "
			+ b.getPossibleMoves(board), b.getPossibleMoves(board)
			.size(), 7);
	tearDown();

    }
}
