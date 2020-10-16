package com.rfgomes.boardgames.sudoku.dal;

public class BoardSolverDto {
    private int limit;
    private int[][] board;

    public BoardSolverDto() {

    }

    public BoardSolverDto(final int[][] board, final int limit) {
        this.board = board;
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
