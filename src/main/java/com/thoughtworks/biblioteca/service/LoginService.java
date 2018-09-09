package com.thoughtworks.biblioteca.service;

import com.thoughtworks.biblioteca.model.LoginInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginService {
    private LoginInfo loginInfo;

    public LoginService() {
        initLoginStatus();
    }

    private void initLoginStatus() {
        loginInfo = LoginInfo.builder().build();
    }

    public boolean isLogin() {
        return loginInfo.isLogin();
    }
}
