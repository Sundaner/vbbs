package com.htw.vbbs.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class User {
    private int userId;
    private String phone;
    private String nickname;
    private String password;
    private String salt;
    private String address;
    private Date birthday;
    private String portrait;
    private String sign;
    private Timestamp createTime;
    private Timestamp updateTime;
}
