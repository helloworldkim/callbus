package com.example.callbus.web.controller;

import com.example.callbus.consts.enums.AccountType;
import com.example.callbus.consts.responsecode.SuccessCode;
import com.example.callbus.service.MenuService;
import com.example.callbus.web.annotation.PreAuthorize;
import com.example.callbus.web.response.ApiResponseDTO;
import com.example.callbus.web.response.boardlike.BoardListResDto;
import com.example.callbus.web.response.menu.MenuResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;


    @PreAuthorize(hasRole = {AccountType.OTHER})
    @GetMapping("/v1/menus")
    public ApiResponseDTO findMenuListV1() throws Exception{
        List<MenuResDTO> menuResDTO = menuService.getV1Menus();
        return new ApiResponseDTO(SuccessCode.SUCCESS, "메뉴 조회", menuResDTO);
    }

    @PreAuthorize(hasRole = {AccountType.OTHER})
    @GetMapping("/v2/menus")
    public ApiResponseDTO findMenuListV2() throws Exception{
        List<MenuResDTO> menuResDTO = menuService.getV2Menus();
        return new ApiResponseDTO(SuccessCode.SUCCESS, "메뉴 조회", menuResDTO);
    }

    @PreAuthorize(hasRole = {AccountType.OTHER})
    @GetMapping("/v3/menus")
    public ApiResponseDTO findMenuListV3() throws Exception{
        List<MenuResDTO> menuResDTO = menuService.getV3Menus();
        return new ApiResponseDTO(SuccessCode.SUCCESS, "메뉴 조회", menuResDTO);
    }

    @PreAuthorize(hasRole = {AccountType.OTHER})
    @GetMapping("/v4/menus")
    public ApiResponseDTO findMenuListV4() throws Exception{
        List<MenuResDTO> menuResDTO = menuService.getV4Menus();
        return new ApiResponseDTO(SuccessCode.SUCCESS, "메뉴 조회", menuResDTO);
    }


    @PreAuthorize(hasRole = {AccountType.OTHER})
    @GetMapping("/v5/menus")
    public ApiResponseDTO findMenuListV5() throws Exception{
        List<MenuResDTO> menuResDTO = menuService.getV5Menus();
        return new ApiResponseDTO(SuccessCode.SUCCESS, "메뉴 조회", menuResDTO);
    }



}
