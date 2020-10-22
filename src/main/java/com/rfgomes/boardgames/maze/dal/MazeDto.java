package com.rfgomes.boardgames.maze.dal;

public class MazeDto {
    private int[][] maze;

    public MazeDto() {

    }

    public MazeDto(final int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }
}
