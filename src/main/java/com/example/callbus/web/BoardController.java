package com.example.callbus.web;

import com.example.callbus.service.BoardService;
import com.example.callbus.web.request.board.BoardReqDto;
import com.example.callbus.web.response.boardlike.BoardListResDto;
import com.example.callbus.web.response.board.BoardResDto;
import com.example.callbus.web.response.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 목록 조회
     * @return
     * @throws Exception
     */
    @GetMapping("/api/v1/board/list")
    private ResponseEntity<?> findBoardList(HttpServletRequest request) throws Exception{
        String accountId = (String) request.getAttribute("accountId");


        BoardListResDto BoardListResDto = boardService.findBoardList(accountId);

        CommonResponseDto<?> data = CommonResponseDto.builder()
                .code(HttpStatus.OK.value())
                .msg("게시글 목록 조회")
                .body(BoardListResDto)
                .build();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/api/v1/board/{boardId}")
    private ResponseEntity<?> findBoardById(@PathVariable Long boardId, HttpServletRequest request) throws Exception{
        String accountId = (String) request.getAttribute("accountId");

        BoardResDto boardResDto = boardService.findBoard(boardId, accountId);

        CommonResponseDto<?> data = CommonResponseDto.builder()
                .code(HttpStatus.OK.value())
                .msg("게시글 단건 조회")
                .body(boardResDto)
                .build();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * 게시글 등록
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/api/v1/board")
    private ResponseEntity<?> saveBoard(@RequestBody @Valid BoardReqDto dto, HttpServletRequest request) throws Exception {
        String accountId = (String) request.getAttribute("accountId");

        BoardResDto boardResDto = boardService.saveBoard(dto, accountId);

        CommonResponseDto<?> data = CommonResponseDto.builder().code(HttpStatus.OK.value()).msg("게시글 등록 완료").body(boardResDto).build();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


    /**
     * 게시글 수정
     * @param dto
     * @param boardId
     * @return
     */
    @PutMapping("/api/v1/board/{boardId}")
    private ResponseEntity<?> updateBoard(@RequestBody @Valid BoardReqDto dto, @PathVariable Long boardId) throws Exception {
        BoardResDto boardResDto = boardService.updateBoard(boardId, dto);

        CommonResponseDto<?> data = CommonResponseDto.builder()
                .code(HttpStatus.OK.value())
                .msg("게시글 수정 완료")
                .body(boardResDto).build();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * 게시글 삭제
     * @param boardId
     * @return
     */
    @DeleteMapping("/api/v1/board/{boardId}")
    private ResponseEntity<?> deleteBoard(@PathVariable Long boardId) throws Exception {
        boardService.deleteBoard(boardId);

        CommonResponseDto<?> data = CommonResponseDto.builder()
                .code(HttpStatus.OK.value())
                .msg("게시글 삭제 완료")
                .build();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }


}
