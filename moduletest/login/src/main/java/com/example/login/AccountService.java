package com.example.login;

import com.example.common.Service.LoginService;

public class AccountService implements LoginService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
