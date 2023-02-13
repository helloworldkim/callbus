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
    private final String code;

    public static String getAccountTypeName(AccountType accountType) {
        AccountType matchedType = Arrays.stream(AccountType.values())
                .filter(accountTypes -> accountTypes.getCode().equals(accountType.getCode()))
                .findFirst()
                .orElse(AccountType.OTHER);
        return matchedType.getCode();
    }
}
