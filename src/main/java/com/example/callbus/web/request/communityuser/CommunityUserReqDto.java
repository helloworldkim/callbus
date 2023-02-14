package com.example.callbus.web.request.communityuser;

import com.example.callbus.entity.CommunityUser;
import com.example.callbus.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommunityUserReqDto {

    @NotBlank(message = "닉네임은 필수값 입니다.")
    private String nickname;
    @NotBlank(message = "계정타입은 필수값 입니다.")
    private String accountType;
    @NotBlank(message = "계정아이디는 필수값 입니다.")
    private String accountId;
    private String quit;

    //================================================================
    // toEntity
    //================================================================

    public CommunityUser toEntity() {
        return CommunityUser.builder()
                .nickname(this.nickname)
                .accountType(AccountType.valueOf(this.accountType))
                .accountId(this.accountId)
                .quit("N")
                .build();
    }


}
