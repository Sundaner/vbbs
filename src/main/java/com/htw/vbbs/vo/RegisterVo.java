package com.htw.vbbs.vo;

import com.htw.vbbs.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class RegisterVo {

    @NotNull
    @Length(min= 2, max = 16)
    private String nickname;

    @NotNull
    @IsMobile
    private String phone;

    @NotNull
    @Length(min= 32)
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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
        return "RegisterVo [nickname=" + nickname +", phone=" + phone + ", password=" + password + "]";
    }
}