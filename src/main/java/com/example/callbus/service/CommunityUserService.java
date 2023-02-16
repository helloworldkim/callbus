package com.example.callbus.service;

import com.example.callbus.entity.CommunityUser;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.request.communityuser.CommunityUserReqDto;
import com.example.callbus.web.response.communityuser.CommunityUserResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityUserService {

    private final CommunityUserRepository commuityUserRepository;

    /**
     * 회원등록
     * @param dto
     * @return
     */
    @Transactional
    public CommunityUserResDTO saveUser(CommunityUserReqDto dto) {

        validateDuplicateMember(dto.toEntity());
        CommunityUser savedUser = commuityUserRepository.save(dto.toEntity());
        return savedUser.toDTO();

    }

    /**
     * 아이디 중복회원 체크
     * @param communityUser
     */
    private void validateDuplicateMember(CommunityUser communityUser) {
        commuityUserRepository.findCommunityUserByAccountId(communityUser.getAccountId()).ifPresent( user-> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

    }

    /**
     * 회원 정보 조회
     * @param accountId
     * @return
     */
    public CommunityUserResDTO findCommunityUserByAccountId(String accountId) {

        CommunityUser communityUser = commuityUserRepository.findCommunityUserByAccountId(accountId).orElseThrow(() -> new RuntimeException("등록된 회원이 없습니다."));
        return communityUser.toDTO();

    }
}
