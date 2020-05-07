package com.htw.vbbs.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Store {
    private int storeId;
    private int userId;
    private int invitationId;
    private Timestamp updateTime;
}
