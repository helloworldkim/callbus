package com.example.callbus.web;


import com.example.callbus.consts.enums.AccountType;
import com.example.callbus.consts.responsecode.SuccessCode;
import com.example.callbus.service.CommunityUserService;
import com.example.callbus.web.annotation.PreAuthorize;
import com.example.callbus.web.request.communityuser.CommunityUserReqDto;
import com.example.callbus.web.response.ApiResponseDTO;
import com.example.callbus.web.response.communityuser.CommunityUserResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CommunityUserService communityUserService;

    /**
     * 회원등록
     * @param communityUserReqDto
     * @return
     */
    @PreAuthorize(hasRole = {AccountType.OTHER})
    @PostMapping("/api/v1/regist")
    public ApiResponseDTO regist(@RequestBody @Valid CommunityUserReqDto communityUserReqDto) {
        CommunityUserResDTO communityUserResDTO = communityUserService.saveUser(communityUserReqDto);
        return new ApiResponseDTO(SuccessCode.SUCCESS, "가입 완료", communityUserResDTO);

    }


}
