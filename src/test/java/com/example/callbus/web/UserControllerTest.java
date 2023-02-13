package com.example.callbus.web;

import com.example.callbus.consts.GlobalConst;
import com.example.callbus.entity.BoardLike;
import com.example.callbus.enums.AccountType;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static ObjectMapper om;
    private static HttpHeaders headers;

    @BeforeEach
    public void setup() {

        //사용자
        om = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


    }
    @Test
    @DisplayName("회원가입 테스트")
    void saveUser() throws Exception {

        //given
        CommunityUserReqDto communityUserReqDto = new CommunityUserReqDto();
        communityUserReqDto.setAccountId("1");
        communityUserReqDto.setNickname("john");
        communityUserReqDto.setAccountType(AccountType.REALTOR.name());
        communityUserReqDto.setQuit("N");
        String body = om.writeValueAsString(communityUserReqDto);

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/regist")
                .headers(headers)
                .content(body));

        MvcResult mvcResult = result.andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        DocumentContext dc = JsonPath.parse(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))); // jway
        int code = dc.read("$.code");
        String nickname = dc.read("$.body.nickname");
        String accountType = dc.read("$.body.accountType");
        String accountId = dc.read("$.body.accountId");
        String quit = dc.read("$.body.quit");

        assertThat(code).isEqualTo(HttpStatus.OK.value());
        assertThat(nickname).isEqualTo(communityUserReqDto.getNickname());
        assertThat(accountType).isEqualTo(communityUserReqDto.getAccountType());
        assertThat(accountId).isEqualTo(communityUserReqDto.getAccountId());
        assertThat(quit).isEqualTo(communityUserReqDto.getQuit());


    }
}