package project.ilyagorban.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.ilyagorban.model.Rank;
import project.ilyagorban.model.XY;
import project.ilyagorban.model.figures.Bishop;
import project.ilyagorban.model.figures.Figure;

public class TestBishop {
    XY xy = new XY(0, 0);
    Bishop b = new Bishop(xy, Rank.WHITE_BISHOP);
    Figure[][] board = new Figure[8][8];

    @Before
    public void setUp() throws Exception {
	board[xy.getX()][xy.getY()] = b;
    }

    @After
    public void tearDown() throws Exception {
	board[xy.getX()][xy.getY()] = null;
    }

    @Test
    public void testGetPossibleMoves() throws Exception {
	xy.setXY(0, 0);
	setUp();
	assertEquals(b.getPossibleMoves(board).size(), 7);
	tearDown();

	xy.setXY(3, 3);
	setUp();
	assertEquals(b.getPossibleMoves(board).size(), 13);
	tearDown();

	xy.setXY(2, 3);
	setUp();
	assertEquals(b.getPossibleMoves(board).size(), 11);
	tearDown();

    }

}
