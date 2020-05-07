package com.htw.vbbs.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ToMusDisVo implements Serializable {
    private int musicId;
    private int disId;
    private String content;
}