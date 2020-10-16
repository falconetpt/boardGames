package com.rfgomes.boardgames.sudoku.domain.solver;

import com.rfgomes.boardgames.sudoku.domain.Board;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BoardSolver {
    Set<Board> solve(Board board, int limit);
}
