package com.example.callbus.web;

import com.example.callbus.consts.enums.AccountType;
import com.example.callbus.consts.responsecode.SuccessCode;
import com.example.callbus.service.BoardLikeService;
import com.example.callbus.web.annotation.PreAuthorize;
import com.example.callbus.web.response.ApiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;


    /**
     * 게시글 좋아요 등록
     * @param boardId
     * @param request
     * @return
     * @throws Exception
     */
    @PreAuthorize(hasRole = {AccountType.LESSEE, AccountType.REALTOR, AccountType.LESSOR})
    @PostMapping("/api/v1/like/{boardId}")
    private ApiResponseDTO boardLikeSave(@PathVariable Long boardId, HttpServletRequest request) throws Exception{
        String accountId = (String) request.getAttribute("accountId");
        boardLikeService.saveBoardLike(boardId, accountId);
        return new ApiResponseDTO(SuccessCode.SUCCESS, "좋아요 등록 완료");
    }

    /**
     * 게시글 좋아요 삭제
     * @param boardId
     * @param request
     * @return
     */
    @PreAuthorize(hasRole = {AccountType.LESSEE, AccountType.REALTOR, AccountType.LESSOR})
    @DeleteMapping("/api/v1/like/{boardId}")
    private ApiResponseDTO boardLikeDelete(@PathVariable Long boardId, HttpServletRequest request) {
        String accountId = (String) request.getAttribute("accountId");
        boardLikeService.deleteBoardLike(boardId, accountId);
        return new ApiResponseDTO(SuccessCode.SUCCESS, "좋아요 삭제 완료");
    }

}
