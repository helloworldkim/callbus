package com.example.callbus.service;

import com.example.callbus.entity.Board;
import com.example.callbus.entity.BoardLike;
import com.example.callbus.entity.CommunityUser;
import com.example.callbus.repository.BoardLikeRepository;
import com.example.callbus.repository.BoardRepository;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.response.BoardLikeResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardLikeService {

    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;
    private final CommunityUserRepository communityUserRepository;

    /**
     * 게시글 좋아요 등록
     * @param boardId
     * @param accountId
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public BoardLikeResDto saveBoardLike(Long boardId, String accountId) {

        Optional<BoardLike> boardLikePs = boardLikeRepository.findBoardLike(boardId, accountId);

        if (!boardLikePs.isPresent()) {

            Optional<CommunityUser> user = communityUserRepository.findCommunityUserByAccountId(accountId);
            Optional<Board> board = boardRepository.findById(boardId);
            Board boardPs = board.get();
            CommunityUser userPs = user.get();

            BoardLike boardLike = BoardLike.builder().board(boardPs).communityUser(userPs).build();
            BoardLike save = boardLikeRepository.save(boardLike);

            return save.toDTO();
        } else {
            throw new RuntimeException("좋아요를 이미 누르셨습니다.");
        }

    }

    /**
     * 게시글 좋아요 삭제
     * @param boardId
     * @param accountId
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteBoardLike(Long boardId, String accountId) {

        Optional<BoardLike> boardLike = boardLikeRepository.findBoardLike(boardId, accountId);

        if (boardLike.isPresent()) {
            BoardLike boardLikePs = boardLike.get();
            boardLikeRepository.deleteById(boardLikePs.getId());
        } else {
            throw new RuntimeException("삭제할 좋아요가 없습니다.");
        }


    }


}
