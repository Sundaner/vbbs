package com.htw.vbbs.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MusicDiscuss {
    private int musicDisId;
    private String musicId;
    private int userId;
    private String content;
    private int replyId;
    private Timestamp createTime;
}
