package com.example.callbus.service;

import com.example.callbus.entity.CommunityUser;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.request.communityuser.CommunityUserReqDto;
import com.example.callbus.web.response.communityuser.CommunityUserResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityUserService {

    private final CommunityUserRepository commuityUserRepository;

    /**
     * 회원등록
     * @param dto
     * @return
     */
    @Transactional
    public CommunityUserResDTO saveUser(CommunityUserReqDto dto) {

        CommunityUser savedUser = commuityUserRepository.save(dto.toEntity());
        return savedUser.toDTO();

    }

    /**
     * 회원 정보 조회
     * @param accountId
     * @return
     */
    @Transactional
    public CommunityUserResDTO findCommunityUserByAccountId(String accountId) {

        CommunityUser communityUser = commuityUserRepository.findCommunityUserByAccountId(accountId).orElseThrow(() -> new RuntimeException("등록된 회원이 없습니다."));
        return communityUser.toDTO();

    }
}
