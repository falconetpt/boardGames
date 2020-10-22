package com.rfgomes.boardgames.maze.domain;

public class Maze {
    private int[][] maze;

    public Maze() {

    }

    public Maze(final int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }
}
