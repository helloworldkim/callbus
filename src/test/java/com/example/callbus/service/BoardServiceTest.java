package com.example.callbus.service;

import com.example.callbus.entity.CommunityUser;
import com.example.callbus.enums.AccountType;
import com.example.callbus.repository.BoardRepository;
import com.example.callbus.web.request.BoardReqDto;
import com.example.callbus.web.request.CommunityUserReqDto;
import com.example.callbus.web.response.BoardResDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;




@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 등록 테스트")
    void saveBoard() {

        //given
        BoardReqDto dto = new BoardReqDto();
        dto.setTitle("제목");
        dto.setContent("내용");

        //stub
        when(boardRepository.save(any())).thenReturn(dto.toEntity());

        //when
        BoardResDto boardResDto = boardService.saveBoard(dto);

        //then
        assertThat(boardResDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(boardResDto.getContent()).isEqualTo(dto.getContent());


    }

    @Test
    @DisplayName("게시글 단건 조회 테스트")
    void findBoard() {

        //given

        //stub

        //when

        //then


    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void updateBoard() {

        //given

        //stub

        //when

        //then
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void deleteBoard() {

        //given
        BoardReqDto dto = new BoardReqDto();
        dto.setTitle("제목");
        dto.setContent("내용");
//        CommunityUserReqDto user = new CommunityUserReqDto();
//        user.setAccountId("abc");
//        user.setAccountType(AccountType.REALTOR.name());
//        user.setNickname("john");
//        user.setQuit("N");

        BoardResDto boardResDto = boardService.saveBoard(dto);

        //stub

        //when
        boardService.deleteBoard(boardResDto.getId());

        //then


    }
}