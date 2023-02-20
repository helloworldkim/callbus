package com.example.callbus.web;

import com.example.callbus.consts.enums.AccountType;
import com.example.callbus.consts.responsecode.SuccessCode;
import com.example.callbus.service.BoardService;
import com.example.callbus.web.annotation.PreAuthorize;
import com.example.callbus.web.request.board.BoardReqDto;
import com.example.callbus.web.response.ApiResponseDTO;
import com.example.callbus.web.response.boardlike.BoardListResDto;
import com.example.callbus.web.response.board.BoardResDto;
import lombok.RequiredArgsConstructor;
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
    @PreAuthorize(hasRole = {AccountType.LESSEE, AccountType.REALTOR, AccountType.LESSOR, AccountType.OTHER})
    @GetMapping("/api/v1/board/list")
    private ApiResponseDTO findBoardList(HttpServletRequest request) throws Exception{
        String accountId = (String) request.getAttribute("accountId");
//        BoardListResDto BoardListResDto = boardService.findBoardList();
        BoardListResDto BoardListResDto = boardService.findBoardListByQureydsl(accountId);
        return new ApiResponseDTO(SuccessCode.SUCCESS, "게시글 목록 조회", BoardListResDto);
    }

    /**
     * 게시글 단건 조회
     * @param boardId
     * @param request
     * @return
     * @throws Exception
     */
    @PreAuthorize(hasRole = {AccountType.LESSEE, AccountType.REALTOR, AccountType.LESSOR, AccountType.OTHER})
    @GetMapping("/api/v1/board/{boardId}")
    private ApiResponseDTO findBoardById(@PathVariable Long boardId, HttpServletRequest request) throws Exception{
        String accountId = (String) request.getAttribute("accountId");

        BoardResDto boardResDto = boardService.findBoard(boardId);
        return new ApiResponseDTO(SuccessCode.SUCCESS, "게시글 단건 조회", boardResDto);
    }

    /**
     * 게시글 등록
     * @param dto
     * @return
     * @throws Exception
     */
    @PreAuthorize(hasRole = {AccountType.LESSEE, AccountType.REALTOR, AccountType.LESSOR})
    @PostMapping("/api/v1/board")
    private ApiResponseDTO saveBoard(@RequestBody @Valid BoardReqDto dto, HttpServletRequest request) throws Exception {
        String accountId = (String) request.getAttribute("accountId");

        BoardResDto boardResDto = boardService.saveBoard(dto, accountId);
        return new ApiResponseDTO(SuccessCode.SUCCESS, "게시글 등록 완료", boardResDto);
    }


    /**
     * 게시글 수정
     * @param dto
     * @param boardId
     * @return
     */
    @PreAuthorize(hasRole = {AccountType.LESSEE, AccountType.REALTOR, AccountType.LESSOR})
    @PutMapping("/api/v1/board/{boardId}")
    private ApiResponseDTO updateBoard(@RequestBody @Valid BoardReqDto dto, @PathVariable Long boardId) throws Exception {
        BoardResDto boardResDto = boardService.updateBoard(boardId, dto);

        return new ApiResponseDTO(SuccessCode.SUCCESS, "게시글 수정 완료", boardResDto);
    }

    /**
     * 게시글 삭제
     * @param boardId
     * @return
     */
    @PreAuthorize(hasRole = {AccountType.LESSEE, AccountType.REALTOR, AccountType.LESSOR})
    @DeleteMapping("/api/v1/board/{boardId}")
    private ApiResponseDTO deleteBoard(@PathVariable Long boardId) throws Exception {
        boardService.deleteBoard(boardId);
        return new ApiResponseDTO(SuccessCode.SUCCESS, "게시글 삭제 완료");
    }


}
