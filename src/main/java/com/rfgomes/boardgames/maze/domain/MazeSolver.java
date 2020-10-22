package com.rfgomes.boardgames.maze.domain;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Queue;

@Component
public interface MazeSolver {
    Optional<Queue<Point>> solveMaze(final Maze maze,
                                     final Point start,
                                     final Point end);

    Maze generateMaze(final int size);
}
