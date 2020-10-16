package com.rfgomes.boardgames.sudoku.domain;

import com.rfgomes.boardgames.sudoku.dal.BoardSolverDto;

import java.util.Set;

public interface BoardService {
    BoardSolverDto solve(final BoardSolverDto board);
    Set<BoardSolverDto> generate(final int size, final int limit);
}
