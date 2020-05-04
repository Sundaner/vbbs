package com.htw.vbbs.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Message {
    private int messageId;
    private int senderId;
    private int receiverId;
    private String content;
    private Timestamp createTime;
}
