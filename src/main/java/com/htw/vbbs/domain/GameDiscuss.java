package com.htw.vbbs.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GameDiscuss {
    private int gameDisId;
    private int gameId;
    private int userId;
    private String content;
    private int replyId;
    private Timestamp createTime;
}
