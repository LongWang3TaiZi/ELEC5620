package com.elec5620.intelligentfinancial.utils;

import lombok.Getter;

@Getter
public enum ResponseCode {
    F0("User already exists", "F0"),
    F1("The user does not exist", "F1"),
    F2("The password is incorrect", "F2"),
    F3("The type of user is not exist", "F3"),
    F4("The user name already exists", "F4"),
    A0("User created successfully", "A0"),
    A1("User login successfully", "A1"),
    A2("User update information successfully", "A2"),
    A3("Delete user successfully", "A3");

    private final String message;
    private final String responseCode;

    ResponseCode(String message, String responseCode) {
        this.message = message;
        this.responseCode = responseCode;
    }
}