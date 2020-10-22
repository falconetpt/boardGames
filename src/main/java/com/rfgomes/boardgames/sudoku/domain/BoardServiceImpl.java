package com.rfgomes.boardgames.sudoku.domain;

import com.rfgomes.boardgames.sudoku.dal.BoardSolverDto;
import com.rfgomes.boardgames.sudoku.domain.solver.BoardSolver;
import org.springframework.stereotype.Service;

import java.awt.Point;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardSolver boardSolver;

    public BoardServiceImpl(final BoardSolver boardSolver) {
        this.boardSolver = boardSolver;
    }

    @Override
    public BoardSolverDto solve(final BoardSolverDto boardSolverDto) {
        Board board = new Board( boardSolverDto.getBoard() );
        return boardSolver.solve( board, 1 )
                .stream()
                .map( b -> new BoardSolverDto( b.getBoard(), 1 ) )
                .findFirst()
                .orElse( null );
    }

    @Override
    public Set<BoardSolverDto> generate(int size, int limit) {
        Board board = new Board( size );
        int length = board.getBoard().length;

        List<Point> pointList = IntStream.range( 0,  length ).boxed()
                .flatMap( x -> IntStream.range( 0, length ).mapToObj( y -> new Point( x, y ) ) )
                .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
                    Collections.shuffle(collected);
                    return collected.stream();
                }))
                .limit( (int) (length * length / 1.2) )
                .collect( Collectors.toList());

        return boardSolver.solve( board, limit )
                .stream()
                .map( Board::getBoard )
                .peek( b -> createPlayableBoard(b, pointList) )
                .map( b -> new BoardSolverDto( b, limit ) )
                .collect( Collectors.toSet());
    }

    private void createPlayableBoard(int[][] b, List<Point> pointList) {
        pointList.stream().forEach( p -> b[p.x][p.y] = 0 );
    }
}
