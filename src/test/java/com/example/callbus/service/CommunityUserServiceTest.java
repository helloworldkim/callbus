package com.example.callbus.service;

import com.example.callbus.enums.AccountType;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.request.CommunityUserReqDto;
import com.example.callbus.web.response.CommuityUserResDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommunityUserServiceTest {

    @InjectMocks
    private CommunityUserService communityUserService;

    @Mock
    private CommunityUserRepository communityUserRepository;

    @Test
    @DisplayName("회원가입 테스트")
    void userSave() {

        //given
        CommunityUserReqDto dto = new CommunityUserReqDto();
        dto.setNickname("john");
        dto.setAccountId("123");
        dto.setAccountType(AccountType.REALTOR.name());
        dto.setQuit("N");

        //stub
        when(communityUserRepository.save(any())).thenReturn(dto.toEntity());

        //when
        CommuityUserResDTO commuityUserResDTO = communityUserService.saveUser(dto);

        //then
        assertThat(commuityUserResDTO.getNickname()).isEqualTo(dto.getNickname());
        assertThat(commuityUserResDTO.getAccountId()).isEqualTo(dto.getAccountId());
        assertThat(commuityUserResDTO.getAccountType().name()).isEqualTo(dto.getAccountType());
        assertThat(commuityUserResDTO.getQuit()).isEqualTo(dto.getQuit());

    }
}