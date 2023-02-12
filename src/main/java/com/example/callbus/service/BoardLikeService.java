package com.example.callbus.service;

import com.example.callbus.entity.Board;
import com.example.callbus.entity.BoardLike;
import com.example.callbus.repository.BoardLikeRepository;
import com.example.callbus.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardLikeService {

    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveBoardLike(Long boardId) {

        Optional<Board> board = boardRepository.findById(boardId);

        if (board.isPresent()) {
            Board boardPs = board.get();
            BoardLike boardLike = BoardLike.builder().board(boardPs).build();
            boardLikeRepository.save(boardLike);

        } else {
            throw new RuntimeException("대상 게시글이 없습니다.");
        }

//        return null;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteBoardLikeById(Long boardId) {

//        boardLikeRepository.deleteBoardLikeByBoardIdAndCommunityUserId(boardId);

    }


}
