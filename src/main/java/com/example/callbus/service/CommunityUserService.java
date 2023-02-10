package com.example.callbus.service;

import com.example.callbus.entity.CommunityUser;
import com.example.callbus.repository.CommunityUserRepository;
import com.example.callbus.web.request.CommunityUserReqDto;
import com.example.callbus.web.response.CommuityUserResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityUserService {

    private final CommunityUserRepository commuityUserRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public CommuityUserResDTO userSave(CommunityUserReqDto dto) {

        CommunityUser savedUser = commuityUserRepository.save(dto.toEntity());

        return savedUser.toDTO();

    }
}
