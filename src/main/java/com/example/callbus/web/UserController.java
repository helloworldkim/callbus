package com.example.callbus.web;


import com.example.callbus.service.CommunityUserService;
import com.example.callbus.web.request.communityuser.CommunityUserReqDto;
import com.example.callbus.web.response.CommonResponseDto;
import com.example.callbus.web.response.communityuser.CommunityUserResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CommunityUserService communityUserService;

    /**
     * 회원등록
     * @param communityUserReqDto
     * @param bindingResult
     * @return
     */
    @PostMapping("/api/v1/regist")
    public ResponseEntity<?> test(@RequestBody @Valid CommunityUserReqDto communityUserReqDto, BindingResult bindingResult) {

        /* Validation 에러확인 */
        if (bindingResult.hasErrors()) {
            HashMap<String, String> errorMap = new HashMap<>();

            for (FieldError fieldFieldError : bindingResult.getFieldErrors()) {
                errorMap.put(fieldFieldError.getField(),fieldFieldError.getDefaultMessage());
            }

            throw new RuntimeException(errorMap.toString());
        }

        CommunityUserResDTO communityUserResDTO = communityUserService.saveUser(communityUserReqDto);


        CommonResponseDto<?> data = CommonResponseDto.builder().code(HttpStatus.OK.value()).msg("가입테스트").body(communityUserResDTO).build();
        return new ResponseEntity<>(data, HttpStatus.OK);

    }


}
