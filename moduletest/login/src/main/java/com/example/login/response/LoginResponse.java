package com.example.login.response;

public class LoginResponse {

    /**
     * status : 0
     * msg :
     * data :
     */

    private int status;
    private String msg;
    private boolean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }
}