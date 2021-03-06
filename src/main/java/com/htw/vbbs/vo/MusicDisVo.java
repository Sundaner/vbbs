package com.htw.vbbs.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MusicDisVo {
    private int commentId;
    private int userId;
    private String userImg;
    private String userName;
    private Timestamp createTime;
    private String content;
    private MusicDisVo parent;
}
