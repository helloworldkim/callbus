package com.example.callbus.web.response.communityuser;

import com.example.callbus.enums.AccountType;
import lombok.*;

@Getter
@Setter
@Builder
public class CommunityUserResDTO {

    private Long id;
    private String nickname;
    private AccountType accountType;
    private String accountId;
    private String quit;

}
