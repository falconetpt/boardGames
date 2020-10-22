package com.rfgomes.boardgames.maze.domain;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class MazeSolverImpl implements MazeSolver {
    private Random random = new Random();

    @Override
    public Optional<Queue<Point>> solveMaze(final Maze maze,
                                            final Point start,
                                            final Point end) {

        final PriorityQueue<Point> queue = new PriorityQueue<>(  );
        queue.add( start );

        return solveMaze( maze, start, end, queue, new LinkedList<>(  ) );
    }

    @Override
    public Maze generateMaze(final int size) {
        int[][] mazeRep = new int[size][size];

        IntStream.range( 0, size ).boxed()
                .flatMap( x -> IntStream.range( 0, size ).boxed().map( y -> new Point( x, y ) ) )
                .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
                    Collections.shuffle(collected);
                    return collected.stream();
                })).limit( size * size / 2 )
                .forEach( p -> mazeRep[p.getX()][p.getY()] = 1 );

        return new Maze( mazeRep );
    }

    private Optional<Queue<Point>> solveMaze(final Maze maze,
                                            final Point start,
                                            final Point end,
                                            final PriorityQueue<Point> queue,
                                            final Queue<Point> result) {
        if (queue.isEmpty()) {
            return Optional.empty();
        } else if (start.equals( end )) {
            return Optional.ofNullable( result );
        } else {
            Point next = queue.poll();
            expandQueue( queue, maze, next, end );
            Queue<Point> newResult = new LinkedList<>( result );
            newResult.add( next );

            return solveMaze( maze, next,  end, queue, newResult);
        }

    }


    private void expandQueue(final PriorityQueue<Point> queue,
                             final Maze maze,
                             final Point start,
                             final Point end) {
        final Set<Point> moves = Stream.of(
                new Point(0, 1),
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, -1),
                new Point(-1, 1),
                new Point(-1, -1),
                new Point(-1, 0),
                new Point(0, -1)
        ).collect( Collectors.toSet() );

        Function<Point, Point> newPoint = m -> new Point(
                start.getX() + m.getX(),
                start.getY() + m.getY()
        );

        Function<Point, Point> calculateWeight = m -> new Point(
                        m.getX(), m.getY(),
                        m.getWeight() + 1 + calculateDistance(m, end)
                );

        Predicate<Point> validPoint = m ->  {
            try {
                return maze.getMaze()[m.getX()][m.getY()] == 0;
            } catch (Exception e) {
                return false;
            }
        };

        moves.stream().map( newPoint )
                .filter( validPoint )
                .map( calculateWeight )
                .forEach( queue::add );
    }

    private Double calculateDistance(final Point m, final Point end) {
        return Math.sqrt(
                Math.pow( m.getX() - end.getX(), 2 )
                        + Math.pow( m.getY() - end.getY(), 2 )
        );
    }
}
