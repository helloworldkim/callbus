package com.example.callbus.web;


import com.example.callbus.entity.CommunityUser;
import com.example.callbus.service.CommunityUserService;
import com.example.callbus.web.request.CommunityUserReqDto;
import com.example.callbus.web.response.CommonResponseDto;
import com.example.callbus.web.response.CommuityUserResDTO;
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


    @PostMapping("/api/v1/test")
    public ResponseEntity<?> test(@RequestBody @Valid CommunityUserReqDto communityUserReqDto, BindingResult bindingResult) {

        /* Validation 에러확인 */
        if (bindingResult.hasErrors()) {
            HashMap<String, String> errorMap = new HashMap<>();

            for (FieldError fieldFieldError : bindingResult.getFieldErrors()) {
                errorMap.put(fieldFieldError.getField(),fieldFieldError.getDefaultMessage());
            }

            throw new RuntimeException(errorMap.toString());
        }

        CommuityUserResDTO commuityUserResDTO = communityUserService.userSave(communityUserReqDto);


        CommonResponseDto<?> data = CommonResponseDto.builder().code(HttpStatus.OK.value()).msg("가입테스트").body(null).build();
        return new ResponseEntity<>(data, HttpStatus.OK);

    }


}
