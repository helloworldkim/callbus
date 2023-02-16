package com.example.callbus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum AccountType {
    REALTOR("REALTOR", "공인중개사"),
    LESSOR("LESSOR", "임대인"),
    LESSEE("LESSEE", "임차인"),
    OTHER("OTHER", "외부사용자");

    private final String role;
    private final String description;


    /**
     * role을 받아서 해당 AccountType을 반환하는 메서드
     * @param role
     * @return
     */
    public static AccountType getAccountType(String role) {
        return Arrays.stream(AccountType.values())
                .filter(accountTypes -> accountTypes.getRole().equals(role))
                .findFirst()
                .orElse(AccountType.OTHER);
    }
}
