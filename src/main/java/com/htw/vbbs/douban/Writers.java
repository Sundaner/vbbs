package com.htw.vbbs.douban;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Writers implements Serializable {
    private String alt;
    private Map avatars;
    private String name;
    private String id;
    private String name_en;
}
