package com.htw.vbbs.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MovieDiscuss {
    private int movieDisId;
    private int movieId;
    private int userId;
    private String content;
    private int replyId;
    private Timestamp createTime;
}
