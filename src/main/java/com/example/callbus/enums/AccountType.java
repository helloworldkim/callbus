package com.example.callbus.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AccountType {
    REALTOR("공인중개사"),LESSOR("임대인"),LESSEE("임차인"),OTHER("외부사용자");

    private final String code;

    AccountType(String code) {
        this.code = code;
    }

    public static String getAccountTypeName(AccountType accountType) {
        AccountType matchedType = Arrays.stream(AccountType.values())
                .filter(accountTypes -> accountTypes.getCode().equals(accountType.getCode()))
                .findFirst()
                .orElse(AccountType.OTHER);
        return matchedType.getCode();
    }
}
