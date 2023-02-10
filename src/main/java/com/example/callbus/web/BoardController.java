package com.example.callbus.web;

import com.example.callbus.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/v1/board/list")
    private ResponseEntity<?> findBoardList() {
        return null;
    }
    @PostMapping("/api/v1/board")
    private ResponseEntity<?> saveBoard() {
        return null;
    }
    @PutMapping("/api/v1/board/{boardId}")
    private ResponseEntity<?> updateBoard(@PathVariable Long boardId) {
        return null;
    }

    @DeleteMapping("/api/v1/board/{boardId}")
    private ResponseEntity<?> deleteBoard(@PathVariable Long boardId) {
        return null;
    }


}
