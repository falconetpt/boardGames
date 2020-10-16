package com.rfgomes.boardgames.sudoku.domain;

import java.util.Arrays;
import java.util.Objects;

public class Board {
    private int dimension;
    private int[][] board;


    private Board() {

    }

    public Board(final int dimension) {
        this.dimension = dimension;
        this.board = new int[dimension * dimension][dimension * dimension];
    }

    public Board(final int[][] board) {
        this.dimension = (int) Math.sqrt( board.length );
        this.board = board;
    }

    public int[][] getBoard() {
        return board.clone();
    }

    public int getDimension() {
        return dimension;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return dimension == board1.dimension &&
                Arrays.deepEquals( board, board1.board );
    }

    @Override
    public int hashCode() {
        int result = Objects.hash( dimension );
        result = 31 * result + Arrays.deepHashCode( board );
        return result;
    }
}
