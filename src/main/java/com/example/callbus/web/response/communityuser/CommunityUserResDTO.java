package com.example.callbus.web.response.communityuser;

import com.example.callbus.consts.enums.AccountType;
import com.example.callbus.entity.CommunityUser;
import lombok.*;

@Getter
@Setter
public class CommunityUserResDTO {

    private Long id;
    private String nickname;
    private AccountType accountType;
    private String accountTypeName;
    private String accountId;
    private String quit;

    @Builder
    public CommunityUserResDTO(Long id, String nickname, AccountType accountType, String accountTypeName, String accountId, String quit) {
        this.id = id;
        this.nickname = nickname;
        this.accountType = accountType;
        this.accountTypeName = accountType.getDescription();
        this.accountId = accountId;
        this.quit = quit;
    }

    @Builder
    public CommunityUserResDTO(CommunityUser communityUser) {
        this.id = communityUser.getId();
        this.nickname = communityUser.getNickname();
        this.accountType = communityUser.getAccountType();
        this.accountTypeName = communityUser.getAccountType().getDescription();
        this.accountId = communityUser.getAccountId();
        this.quit = communityUser.getQuit();
    }
}
