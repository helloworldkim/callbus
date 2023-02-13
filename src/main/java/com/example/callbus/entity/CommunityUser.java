package com.example.callbus.entity;

import com.example.callbus.enums.AccountType;
import com.example.callbus.web.response.communityuser.CommunityUserResDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_user_id")
    private Long id;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "account_id", unique = true)
    private String accountId;
    private String quit;

    @Builder
    public CommunityUser(Long id, String nickname, AccountType accountType, String accountId, String quit) {
        this.id = id;
        this.nickname = nickname;
        this.accountType = accountType;
        this.accountId = accountId;
        this.quit = quit;
    }

    //================================================================
    // DTO변환
    //================================================================

    public CommunityUserResDTO toDTO() {
        return CommunityUserResDTO.builder()
                .id(this.id)
                .nickname(this.nickname)
                .accountType(this.accountType)
                .accountId(this.accountId)
                .quit(this.quit)
                .build();
    }


}
