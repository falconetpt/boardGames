package com.rfgomes.boardgames.maze.api;

import com.rfgomes.boardgames.maze.domain.Maze;
import com.rfgomes.boardgames.maze.domain.MazeSolver;
import com.rfgomes.boardgames.maze.domain.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

@RestController
@RequestMapping("/api/maze")
@CrossOrigin(origins = "*")
public class MazeController {
    private MazeSolver mazeSolver;

    public MazeController(final MazeSolver mazeSolver) {
        this.mazeSolver = mazeSolver;
    }

    @PostMapping
    public ResponseEntity<Queue<Point>> solveMaze(@RequestBody SolveMazeDto maze) {
        return ResponseEntity.ok(
                    mazeSolver.solveMaze(
                            new Maze( maze.getMaze() ),
                            new Point( maze.getStart().getX(), maze.getStart().getY() ),
                            new Point( maze.getEnd().getX(), maze.getEnd().getY() )
                    ).orElse( null )
        );
    }

    @GetMapping("/generate/{size}")
    public ResponseEntity<Maze> solveMaze(@PathVariable("size") int size) {
        return ResponseEntity.ok(
                mazeSolver.generateMaze(size)
        );
    }
}
