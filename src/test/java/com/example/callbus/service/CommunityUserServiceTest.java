package com.example.callbus.service;

import com.example.callbus.consts.enums.AccountType;
import com.example.callbus.web.request.communityuser.CommunityUserReqDto;
import com.example.callbus.web.response.communityuser.CommunityUserResDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Transactional
class CommunityUserServiceTest {

    @Autowired
    private CommunityUserService communityUserService;

    @Test
    @DisplayName("회원가입 테스트")
    void saveUser() {

        //given
        CommunityUserReqDto dto = new CommunityUserReqDto();
        dto.setNickname("john");
        dto.setAccountId("123");
        dto.setAccountType(AccountType.REALTOR.name());
        dto.setQuit("N");

        //when
        CommunityUserResDTO communityUserResDTO = communityUserService.saveUser(dto);

        //then
        assertThat(communityUserResDTO.getNickname()).isEqualTo(dto.getNickname());
        assertThat(communityUserResDTO.getAccountId()).isEqualTo(dto.getAccountId());
        assertThat(communityUserResDTO.getAccountType().name()).isEqualTo(dto.getAccountType());
        assertThat(communityUserResDTO.getQuit()).isEqualTo(dto.getQuit());

    }

    @Test
    @DisplayName("중복 회원가입 테스트")
    void duplicatedSaveUser() {

        //given
        CommunityUserReqDto dto1 = new CommunityUserReqDto();
        dto1.setNickname("john");
        dto1.setAccountId("123");
        dto1.setAccountType(AccountType.REALTOR.name());
        dto1.setQuit("N");
        CommunityUserReqDto dto2 = new CommunityUserReqDto();
        dto2.setNickname("john");
        dto2.setAccountId("123");
        dto2.setAccountType(AccountType.REALTOR.name());
        dto2.setQuit("N");


        //when
        CommunityUserResDTO communityUserResDTO = communityUserService.saveUser(dto1);
        //then
        assertThrows(IllegalStateException.class, () -> communityUserService.saveUser(dto2));



    }

    @Test
    @DisplayName("회원 조회 테스트")
    void findCommunityUserByAccountId() {

        //given
        final String accountId = "123";
        CommunityUserReqDto dto = new CommunityUserReqDto();
        dto.setNickname("john");
        dto.setAccountId("123");
        dto.setAccountType(AccountType.REALTOR.name());
        dto.setQuit("N");
        communityUserService.saveUser(dto);

        //when
        CommunityUserResDTO communityUserResDTO = communityUserService.findCommunityUserByAccountId(accountId);

        //then
        assertThat(communityUserResDTO.getNickname()).isEqualTo(dto.getNickname());
        assertThat(communityUserResDTO.getAccountId()).isEqualTo(dto.getAccountId());
        assertThat(communityUserResDTO.getAccountType().name()).isEqualTo(dto.getAccountType());
        assertThat(communityUserResDTO.getQuit()).isEqualTo(dto.getQuit());

    }
}