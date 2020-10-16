package com.rfgomes.boardgames.sudoku.domain.solver;

import com.rfgomes.boardgames.sudoku.domain.Board;
import org.springframework.stereotype.Service;

import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardSolverImpl {
    public static Set<Board> solve(Board board, int limit) {
        int[][] newBoard = board.getBoard();
        Queue<Point> queue = IntStream.range( 0, newBoard.length ).boxed()
                .flatMap( x -> IntStream.range( 0, newBoard.length ).boxed()
                                .filter( y -> newBoard[x][y] == 0 )
                                .map( y -> new Point( x, y ) )
                ).collect( Collectors.toCollection( LinkedList::new ) );

        return solve(newBoard, queue, board.getDimension(), new HashSet<>(), limit );
    }

    private static Set<Board> solve(int[][] newBoard, Queue<Point> queue, int dimension, Set<Board> result, int limit) {
        if (queue.isEmpty() || result.size() >= limit) {
            boolean isValid = isValid( newBoard, dimension );

            if (isValid) result.add( new Board( Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new) ) );
            return result;

        } else {
            Point next = queue.poll();
            int row = next.x / dimension * dimension;
            int col = next.y / dimension * dimension;

            Set<Integer> validNumbers = IntStream.rangeClosed( 1, newBoard.length ).boxed()
                    .collect( Collectors.toSet() );

            IntStream.range( 0, newBoard.length ).boxed()
                    .map( y -> newBoard[next.x][y] )
                    .forEach( validNumbers::remove );

            IntStream.range( 0, newBoard.length ).boxed()
                    .map( x -> newBoard[x][next.y] )
                    .forEach( validNumbers::remove );

            IntStream.range( row, row + dimension ).forEach(
                    x -> IntStream.range( col, col + dimension )
                            .map( y ->  newBoard[x][y])
                            .forEach( validNumbers::remove )
            );


            int[][] clone = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            for (int validNumber: validNumbers) {
                clone[next.x][next.y] = validNumber;
                solve(clone, new LinkedList<>( queue ),  dimension, result, limit);
            }

            return queue.isEmpty() ? solve( clone, queue, dimension, result, limit ) : result;
        }
    }

    private static boolean isValid(int[][] newBoard, int dimension) {
        int numbers = newBoard.length;
        boolean validRow = IntStream.range( 0, newBoard.length )
                .allMatch( x ->
                        IntStream.range( 0, newBoard.length )
                                .map( y -> newBoard[x][y] )
                                .distinct()
                                .count() == numbers
                );
        boolean validCol = IntStream.range( 0, newBoard.length )
                .allMatch( x ->
                        IntStream.range( 0, newBoard.length )
                                .map( y -> newBoard[y][x] )
                                .distinct()
                                .count() == numbers
                );
        boolean validSquare = Stream.iterate( 0, a -> a + dimension )
                    .limit( dimension )
                    .allMatch( x ->
                            Stream.iterate( 0, a -> a + dimension )
                                    .limit( dimension )
                                    .allMatch( y ->
                                            IntStream.range( x, x + dimension ).flatMap(
                                                    x1 -> IntStream.range( y, y + dimension )
                                                            .map( y1 ->  newBoard[x1][y1])
                                            ).distinct().count() == numbers
                                    )
                    );

        return validSquare && validCol && validRow;
    }
}
