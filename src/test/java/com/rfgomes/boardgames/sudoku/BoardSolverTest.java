package com.rfgomes.boardgames.sudoku;

import com.rfgomes.boardgames.sudoku.domain.Board;
import com.rfgomes.boardgames.sudoku.domain.solver.BoardSolverImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardSolverTest {

    @Test
    @DisplayName( "Solve Board of 0x0" )
    public void solve0x0Board() {
        Board board = new Board( 0 );

        Set<Board> result = BoardSolverImpl.solve( board, 10 );
        Board expected = new Board( new int[][]{} );

        assertEquals( 1, result.size() );
        assertTrue( result.stream().anyMatch( e -> e.equals( expected ) ) );
    }

    @Test
    @DisplayName( "Solve Board of 1x1" )
    public void solve1x1Board() {
        Board board = new Board( 1 );

        Set<Board> result = BoardSolverImpl.solve( board, 10 );
        Board expected = new Board( new int[][]{ { 1 }} );

        assertEquals( 1, result.size() );
        assertTrue( result.stream().anyMatch( e -> e.equals( expected ) ) );
    }

    @Test
    @DisplayName( "Solve Board of 2x2" )
    public void solve2x2Board() {
        Board board = new Board( new int[][]{
                { 0, 4, 3, 0 },
                { 0, 0, 0, 0 },
                { 4, 1, 0, 3 },
                { 3, 0, 1, 0 }
        } );

        Set<Board> result = BoardSolverImpl.solve( board, 10 );
        Board expected = new Board( new int[][]{
                { 2, 4, 3, 1 },
                { 1, 3, 4, 2 },
                { 4, 1, 2, 3 },
                { 3, 2, 1, 4 }
        } );

        assertEquals( 2, result.size() );
        assertTrue( result.stream().anyMatch( e -> e.equals( expected ) ) );
    }
}
