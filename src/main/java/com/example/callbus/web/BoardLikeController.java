package com.example.callbus.web;

import com.example.callbus.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;


    @PostMapping("/api/v1/like/{boardId}")
    private ResponseEntity<?> boardLikeSave(@PathVariable Long boardId) {
        //특정 게시글 좋아요 누를때 필요한 값
        // 게시글 번호, 유저번호
        boardLikeService.saveBoardLike(boardId);

        return null;
    }

    @DeleteMapping("/api/v1/like/{boardId}")
    private ResponseEntity<?> boardLikeDelete(@PathVariable Long boardId) {
        //특정 게시글 좋아요 삭제할때 필요한 값
        // 게시글 번호, 유저번호
        boardLikeService.deleteBoardLikeById(boardId);
        return null;
    }

}
