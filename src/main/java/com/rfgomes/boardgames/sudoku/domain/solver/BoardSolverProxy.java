package com.rfgomes.boardgames.sudoku.domain.solver;

import com.rfgomes.boardgames.sudoku.domain.Board;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BoardSolverProxy implements BoardSolver {
    @Override
    public Set<Board> solve(Board board, int limit) {
        return BoardSolverImpl.solve( board, limit );
    }
}
