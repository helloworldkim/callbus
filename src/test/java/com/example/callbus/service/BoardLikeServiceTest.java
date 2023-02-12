package com.example.callbus.service;

import com.example.callbus.entity.Board;
import com.example.callbus.entity.BoardLike;
import com.example.callbus.entity.CommunityUser;
import com.example.callbus.enums.AccountType;
import com.example.callbus.repository.BoardLikeRepository;
import com.example.callbus.repository.BoardRepository;
import com.example.callbus.web.request.BoardReqDto;
import com.example.callbus.web.request.CommunityUserReqDto;
import com.example.callbus.web.response.BoardLikeResDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardLikeServiceTest {

    @InjectMocks
    private BoardLikeService boardLikeService;

    @Mock
    private BoardLikeRepository boardLikeRepository;
    @Mock
    private BoardLikeRepository communityUserRepository;
    @Mock
    private BoardLikeRepository boardRepository;

    @Test
    @DisplayName("게시글 좋아요 등록 테스트")
    void saveBoardLike() {

        //given
//        CommunityUserReqDto communityUserReqDto = new CommunityUserReqDto();
//        communityUserReqDto.setAccountId("1");
//        communityUserReqDto.setNickname("abc");
//        communityUserReqDto.setAccountType(AccountType.REALTOR.name());
//        communityUserReqDto.setQuit("N");
//        CommunityUser communityUser = communityUserReqDto.toEntity();
//
//        BoardReqDto boardDto = new BoardReqDto();
//        boardDto.setTitle("abc");
//        boardDto.setContent("1");
//        boardDto.setCommunityUser(communityUserReqDto.toEntity());
//        Board board = boardDto.toEntity();
//
//        BoardLike boardLike = BoardLike.builder()
//                .board(board)
//                .communityUser(communityUser)
//                .build();
//
//        Optional<BoardLike> optional = Optional.ofNullable(boardLike);
//        Optional<Board> boardOptional = Optional.ofNullable(board);
//        Optional<CommunityUser> userOptional = Optional.ofNullable(communityUser);
        //stub
//        when(boardLikeRepository.findBoardLike(1L, "1")).thenReturn(optional);

        //when
//        BoardLikeResDto boardLikeResDto = boardLikeService.saveBoardLike(1L, "1");

        //then
//        assertThat(boardLikeResDto.getBoard()).isEqualTo(optional.get().getBoard());
//        assertThat(boardLikeResDto.getCommunityUser()).isEqualTo(optional.get().getCommunityUser());

    }

    @Test
    @DisplayName("게시글 좋아요 삭제 테스트")
    void deleteBoardLike() {

        //given
//        CommunityUserReqDto communityUserReqDto = new CommunityUserReqDto();
//        communityUserReqDto.setAccountId("1");
//        communityUserReqDto.setNickname("abc");
//        communityUserReqDto.setAccountType(AccountType.REALTOR.name());
//        communityUserReqDto.setQuit("N");
//
//        BoardReqDto boardDto = new BoardReqDto();
//        boardDto.setTitle("abc");
//        boardDto.setContent("1");
//        boardDto.setCommunityUser(communityUserReqDto.toEntity());
//
//        BoardLike boardLike = BoardLike.builder()
//                .board(boardDto.toEntity())
//                .communityUser(communityUserReqDto.toEntity())
//                .build();
//
//        Optional<BoardLike> optional = Optional.ofNullable(boardLike);

        //stub
//        when(boardLikeRepository.findBoardLike(1L, "1")).thenReturn(optional);

        //when
//        boardLikeService.deleteBoardLike(1L, "1");

        //then


    }
}