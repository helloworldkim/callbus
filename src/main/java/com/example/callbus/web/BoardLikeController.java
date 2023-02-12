package com.example.callbus.web;

import com.example.callbus.service.BoardLikeService;
import com.example.callbus.web.response.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;


    @PostMapping("/api/v1/like/{boardId}")
    private ResponseEntity<?> boardLikeSave(@PathVariable Long boardId, HttpServletRequest request) {
        //특정 게시글 좋아요 누를때 필요한 값
        // 게시글 번호, 유저번호
        String accountId = (String) request.getAttribute("accountId");
        System.out.println("accountId = " + accountId);

        boardLikeService.saveBoardLike(boardId, accountId);

        CommonResponseDto<?> data = CommonResponseDto.builder().code(HttpStatus.OK.value()).msg("좋아요 등록 완료").build();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/like/{boardId}")
    private ResponseEntity<?> boardLikeDelete(@PathVariable Long boardId, HttpServletRequest request) {
        //특정 게시글 좋아요 삭제할때 필요한 값
        // 게시글 번호, 유저번호
        String accountId = (String) request.getAttribute("accountId");
        System.out.println("accountId = " + accountId);

        boardLikeService.deleteBoardLike(boardId, accountId);

        CommonResponseDto<?> data = CommonResponseDto.builder().code(HttpStatus.OK.value()).msg("좋아요 삭제 완료").build();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
