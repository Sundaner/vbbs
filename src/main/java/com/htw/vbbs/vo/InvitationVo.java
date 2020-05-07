package com.htw.vbbs.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InvitationVo {

    private int invitationId;
    private int userId;
    private String userName;
    private String userImg;
    private String title;
    private String type;
    private Timestamp updateTime;
    private int zan;


}
