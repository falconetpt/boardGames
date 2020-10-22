package com.rfgomes.boardgames.maze.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeSolverImplTest {
    @Test
    @DisplayName( "solve basic maze" )
    public void solveBasicMaze() {
        final int[][] maze2D = new int[][] {
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0}
        };

        final Maze maze = new Maze( maze2D );

        final MazeSolverImpl mazeSolver = new MazeSolverImpl();

        mazeSolver.solveMaze( maze, new Point( 0, 0 ), new Point( 5, 5 ) );

    }
}
