package com.htw.vbbs.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Zan {
    private int zanId;
    private int userId;
    private int invitationId;
    private Timestamp createTime;
}
