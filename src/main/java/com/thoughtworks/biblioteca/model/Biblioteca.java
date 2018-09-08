package com.thoughtworks.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Biblioteca {
    private LoginInfo loginInfo;

    public Biblioteca() {
        initLoginStatus();
    }

    private void initLoginStatus() {
        loginInfo = LoginInfo.builder().build();
    }

    public boolean isLogin() {
        return loginInfo.isLogin();
    }
}
