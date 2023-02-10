package com.example.callbus.web.response;

import com.example.callbus.enums.AccountType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
public class CommuityUserResDTO {

    private Long id;
    private String nickname;
    private AccountType accountType;
    private String accountId;
    private String quit;

}
