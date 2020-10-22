package com.rfgomes.boardgames.maze.api;

import com.rfgomes.boardgames.maze.domain.Point;

public class SolveMazeDto {
    private int[][] maze;
    private PointDto start;
    private PointDto end;

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public PointDto getStart() {
        return start;
    }

    public void setStart(PointDto start) {
        this.start = start;
    }

    public PointDto getEnd() {
        return end;
    }

    public void setEnd(PointDto end) {
        this.end = end;
    }
}
