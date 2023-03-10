package com.example.callbus.web;

import com.example.callbus.consts.GlobalConst;
import com.example.callbus.consts.responsecode.SuccessCode;
import com.example.callbus.entity.Board;
import com.example.callbus.entity.BoardLike;
import com.example.callbus.entity.CommunityUser;
import com.example.callbus.consts.enums.AccountType;
import com.example.callbus.repository.BoardLikeRepository;
import com.example.callbus.repository.BoardRepository;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.request.board.BoardReqDto;
import com.example.callbus.web.request.communityuser.CommunityUserReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BoardLikeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommunityUserRepository communityUserRepository;
    @Autowired
    private BoardLikeRepository boardLikeRepository;
    private static ObjectMapper om;
    private static HttpHeaders headers;
    private CommunityUser initUser;
    private Board initBoard;

    @BeforeEach
    public void setup() {
        //????????????
        CommunityUserReqDto dto = new CommunityUserReqDto();
        dto.setAccountId("1");
        dto.setNickname("john");
        dto.setAccountType(AccountType.REALTOR.name());
        dto.setQuit("N");
        this.initUser = communityUserRepository.save(dto.toEntity());

        //??? ?????? ????????? ????????? ??????
        BoardReqDto boardReqDto = new BoardReqDto();
        boardReqDto.setTitle("??????");
        boardReqDto.setContent("??????");
        boardReqDto.setCommunityUser(initUser);
        this.initBoard = boardRepository.save(boardReqDto.toEntity());

        //?????????
        om = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(GlobalConst.REQUEST_HEADER, AccountType.REALTOR + " " + dto.getAccountId());

    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("????????? ?????? ?????????")
    void boardLikeSave() throws Exception {

        //given
        Long boardId = 1L;

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/like/" + boardId)
                .headers(headers));

        MvcResult mvcResult = result.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        DocumentContext dc = JsonPath.parse(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))); // jway
        String code = dc.read("$.code");

        assertThat(code).isEqualTo(SuccessCode.SUCCESS.getCode());

    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("????????? ?????? ?????????")
    void boardLikeDelete() throws Exception {

        //given
        Long boardId = 1L;
        BoardLike boardLike = BoardLike.builder().board(this.initBoard).communityUser(this.initUser).build();
        boardLikeRepository.save(boardLike);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/like/" + boardId)
                .headers(headers));

        MvcResult mvcResult = result.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        DocumentContext dc = JsonPath.parse(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))); // jway
        String code = dc.read("$.code");

        assertThat(code).isEqualTo(SuccessCode.SUCCESS.getCode());

    }
}