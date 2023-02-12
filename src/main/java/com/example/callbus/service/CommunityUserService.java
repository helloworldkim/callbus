package com.example.callbus.service;

import com.example.callbus.entity.CommunityUser;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.request.CommunityUserReqDto;
import com.example.callbus.web.response.CommuityUserResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityUserService {

    private final CommunityUserRepository commuityUserRepository;

    /**
     * 회원등록
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public CommuityUserResDTO saveUser(CommunityUserReqDto dto) {

        CommunityUser savedUser = commuityUserRepository.save(dto.toEntity());

        return savedUser.toDTO();

    }

    /**
     * 회원 정보 조회
     * @param accountId
     * @return
     */
    public CommuityUserResDTO findCommunityUserByAccountId(String accountId) {

        Optional<CommunityUser> user = commuityUserRepository.findCommunityUserByAccountId(accountId);
        if (user.isPresent()) {
            return user.get().toDTO();
        } else {
            throw new RuntimeException("등록된 회원이 없습니다.");
        }

    }
}
