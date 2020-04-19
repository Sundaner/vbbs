package com.htw.vbbs.vo;

import com.htw.vbbs.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginVo {

    @NotNull
    @IsMobile
    private String phone;

    @NotNull
    @Length(min=32)
    private String password;

    public String getPhone() {
        return phone;
    }
    public void setPhone(String mobile) {
        this.phone = mobile;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "LoginVo [phone=" + phone + ", password=" + password + "]";
    }
}
