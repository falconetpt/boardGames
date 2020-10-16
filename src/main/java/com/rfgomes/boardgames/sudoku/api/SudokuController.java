package com.rfgomes.boardgames.sudoku.api;

import com.rfgomes.boardgames.sudoku.dal.BoardSolverDto;
import com.rfgomes.boardgames.sudoku.domain.BoardServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {
    private BoardServiceImpl boardService;

    public SudokuController(final BoardServiceImpl boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/solve")
    public ResponseEntity<BoardSolverDto> solve(@RequestBody BoardSolverDto board) {
        return ResponseEntity.ok( boardService.solve(board) );
    }

    @GetMapping("/generate/{size}/{limit}")
    public ResponseEntity<Set<BoardSolverDto>> solve(@PathVariable("size") int boardSize,
                                                     @PathVariable("limit") int limit) {
        return ResponseEntity.ok( boardService.generate(boardSize, limit) );
    }
}
