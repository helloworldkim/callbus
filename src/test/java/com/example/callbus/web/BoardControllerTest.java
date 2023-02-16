package com.example.callbus.web;

import com.example.callbus.consts.GlobalConst;
import com.example.callbus.consts.responsecode.SuccessCode;
import com.example.callbus.entity.Board;
import com.example.callbus.entity.CommunityUser;
import com.example.callbus.consts.enums.AccountType;
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
import org.springframework.http.*;
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
class BoardControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommunityUserRepository communityUserRepository;
    private static ObjectMapper om;
    private static HttpHeaders headers;
    private CommunityUser initUser;
    private Board initBoard;

    @BeforeEach
    public void setup() {
        //회원가입
        CommunityUserReqDto dto = new CommunityUserReqDto();
        dto.setAccountId("1");
        dto.setNickname("john");
        dto.setAccountType(AccountType.REALTOR.name());
        dto.setQuit("N");
        this.initUser = communityUserRepository.save(dto.toEntity());

        //책 목록 조회용 게시글 등록
        BoardReqDto boardReqDto = new BoardReqDto();
        boardReqDto.setTitle("제목");
        boardReqDto.setContent("내용");
        boardReqDto.setCommunityUser(initUser);
        this.initBoard = boardRepository.save(boardReqDto.toEntity());

        //사용자
        om = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(GlobalConst.REQUEST_HEADER, AccountType.REALTOR + " " + dto.getAccountId());


    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("게시글 목록 조회 테스트")
    public void findBoardList() throws Exception {

        //given
        String title = "제목";
        String content = "내용";

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/board/list")
                                      .headers(headers));

        MvcResult mvcResult = result.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        DocumentContext dc = JsonPath.parse(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))); // jway
        String code = dc.read("$.code");
        String message = dc.read("$.message");
        String findedTitle = dc.read("$.data.items[0].title");
        String findedContent = dc.read("$.data.items[0].content");

        assertThat(code).isEqualTo(SuccessCode.SUCCESS.getCode());
        assertThat(findedTitle).isEqualTo(title);
        assertThat(findedContent).isEqualTo(content);
    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("게시글 단건 조회 테스트")
    public void findBoardById() throws Exception {
        //given
        Long boardId = 1L;
        String title = "제목";
        String content = "내용";

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/board/" + boardId)
                .headers(headers));

        MvcResult mvcResult = result.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        DocumentContext dc = JsonPath.parse(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))); // jway
        String findedTitle = dc.read("$.data.title");
        String findedContent = dc.read("$.data.content");

        assertThat(findedTitle).isEqualTo(title);
        assertThat(findedContent).isEqualTo(content);

    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("게시글 저장 테스트")
    public void saveBoard() throws Exception {

        //given
        BoardReqDto dto = new BoardReqDto();
        dto.setTitle("제목2");
        dto.setContent("내용2");
        String body = om.writeValueAsString(dto);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/board")
                .headers(headers)
                .content(body));

        MvcResult mvcResult = result.andDo(print())
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andReturn();

        //then
        DocumentContext dc = JsonPath.parse(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))); // jway
        String findedTitle = dc.read("$.data.title");
        String findedContent = dc.read("$.data.content");

        assertThat(findedTitle).isEqualTo(dto.getTitle());
        assertThat(findedContent).isEqualTo(dto.getContent());

    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("게시글 수정 테스트")
    public void updateBoard() throws Exception {
        //given
        Long boardId = 1L;
        BoardReqDto dto = new BoardReqDto();
        dto.setTitle("수정제목");
        dto.setContent("수정내용");
        String body = om.writeValueAsString(dto);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/board/" + boardId)
                .headers(headers)
                .content(body));

        MvcResult mvcResult = result.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        DocumentContext dc = JsonPath.parse(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))); // jway
        String findedTitle = dc.read("$.data.title");
        String findedContent = dc.read("$.data.content");

        assertThat(findedTitle).isEqualTo(dto.getTitle());
        assertThat(findedContent).isEqualTo(dto.getContent());
    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    @DisplayName("게시글 삭제 테스트")
    public void deleteBoard() throws Exception {

        //given
        Long boardId = 1L;

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/board/" + boardId)
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