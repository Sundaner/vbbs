package com.htw.vbbs.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ToMusDisVo implements Serializable {
    private String musicId;
    private int disId;
    private String content;
    private int type;
}