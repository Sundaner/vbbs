package com.htw.vbbs.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Invitation {
    private int invitationId;
    private int userId;
    private String title;
    private String context;
    private int zan;
    private int type;
    private Timestamp createTime;
    private Timestamp updateTime;
}
