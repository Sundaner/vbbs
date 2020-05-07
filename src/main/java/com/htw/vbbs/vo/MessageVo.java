package com.htw.vbbs.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageVo {
    private int messageId;
    private int senderId;
    private String userImg;
    private String userName;
    private Timestamp createTime;
    private String content;
    private int status;
}
