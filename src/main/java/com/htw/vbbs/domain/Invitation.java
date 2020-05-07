package com.htw.vbbs.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Invitation {
    private int invitationId;
    private int userId;
    private String title;
    private String content;
    private int zan;
    private int store;
    private int type;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String trueType;
    private List<Comment> commentList;

}
