package com.example.callbus.service;

import com.example.callbus.enums.AccountType;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.request.communityuser.CommunityUserReqDto;
import com.example.callbus.web.response.communityuser.CommunityUserResDTO;
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
        CommunityUserResDTO communityUserResDTO = communityUserService.saveUser(dto);

        //then
        assertThat(communityUserResDTO.getNickname()).isEqualTo(dto.getNickname());
        assertThat(communityUserResDTO.getAccountId()).isEqualTo(dto.getAccountId());
        assertThat(communityUserResDTO.getAccountType().name()).isEqualTo(dto.getAccountType());
        assertThat(communityUserResDTO.getQuit()).isEqualTo(dto.getQuit());

    }
}