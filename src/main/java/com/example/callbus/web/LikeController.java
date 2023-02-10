package com.example.callbus.web;

import com.example.callbus.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;


    @PostMapping("/api/v1/like/{boardId}")
    private ResponseEntity<?> likeSave(@PathVariable Long boardId) {
        //특정 게시글 좋아요 누를때 필요한 값
        // 게시글 번호, 유저번호
        return null;
    }

    @DeleteMapping("/api/v1/like/{boardId}")
    private ResponseEntity<?> likeDelete(@PathVariable Long boardId) {
        //특정 게시글 좋아요 삭제할때 필요한 값
        // 게시글 번호, 유저번호
        return null;
    }

}
