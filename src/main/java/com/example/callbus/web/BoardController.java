package com.example.callbus.web;

import com.example.callbus.service.BoardService;
import com.example.callbus.web.request.BoardReqDto;
import com.example.callbus.web.response.BoardResDto;
import com.example.callbus.web.response.CommonResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/v1/board/list")
    private ResponseEntity<?> findBoardList() throws Exception{
        return null;
    }

    @PostMapping("/api/v1/board")
    private ResponseEntity<?> saveBoard(BoardReqDto dto) throws Exception {
        dto.setTitle("제목");
        dto.setContent("내용");
        BoardResDto boardResDto = boardService.saveBoard(dto);

        CommonResponseDto<?> data = CommonResponseDto.builder().code(HttpStatus.OK.value()).msg("게시글 등록 완료").body(boardResDto).build();
        return new ResponseEntity<>(data, HttpStatus.OK);
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
