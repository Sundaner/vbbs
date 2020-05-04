package com.htw.vbbs.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comment {
    private int commentId;
    private int invitationId;
    private int userId;
    private String content;
    private Timestamp createTime;
    private int replyId;
    private Comment parent;
}
