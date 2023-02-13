package com.example.callbus.service;

import com.example.callbus.entity.Board;
import com.example.callbus.entity.BoardLike;
import com.example.callbus.entity.CommunityUser;
import com.example.callbus.repository.BoardLikeRepository;
import com.example.callbus.repository.BoardRepository;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.response.boardlike.BoardLikeResDto;
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
    @Transactional
    public BoardLikeResDto saveBoardLike(Long boardId, String accountId) {

        CommunityUser user = communityUserRepository.findCommunityUserByAccountId(accountId).orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        Board board = boardRepository.findBoardAndUser(boardId).orElseThrow(() -> new RuntimeException("대상 게시글이 없습니다."));

        /* 좋아요 여부 확인 */
        boardLikeRepository.findBoardLikeByCommunityUserAndBoard(user, board).ifPresent((boardLike) -> {
                                                                                throw new RuntimeException("좋아요를 이미 누르셨습니다.");
                                                                             });

        BoardLike boardLike = BoardLike.builder().board(board).communityUser(user).build();
        BoardLike save = boardLikeRepository.save(boardLike);

        return save.toDTO();

    }

    /**
     * 게시글 좋아요 삭제
     * @param boardId
     * @param accountId
     */
    @Transactional
    public void deleteBoardLike(Long boardId, String accountId) {
        CommunityUser user = communityUserRepository.findCommunityUserByAccountId(accountId).orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        Board board = boardRepository.findBoardAndUser(boardId).orElseThrow(() -> new RuntimeException("대상 게시글이 없습니다."));

        BoardLike boardLike = boardLikeRepository.findBoardLikeByCommunityUserAndBoard(user, board).orElseThrow(() -> new RuntimeException("삭제할 좋아요가 없습니다."));
        boardLikeRepository.deleteById(boardLike.getId());

    }


}
