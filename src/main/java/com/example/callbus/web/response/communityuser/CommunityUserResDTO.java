package com.example.callbus.web.response.communityuser;

import com.example.callbus.enums.AccountType;
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
        this.accountTypeName = AccountType.getAccountTypeName(accountType);
        this.accountId = accountId;
        this.quit = quit;
    }
}
