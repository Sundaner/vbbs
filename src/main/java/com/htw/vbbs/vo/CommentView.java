package com.htw.vbbs.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class CommentView implements Serializable {
    private int userId;
    private String userImg;
    private String userName;
    private int commentId;
    private Timestamp createTime;
    private String content;
    private CommentView parent;
}
