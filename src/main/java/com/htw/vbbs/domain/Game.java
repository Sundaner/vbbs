package com.htw.vbbs.domain;

import lombok.Data;

@Data
public class Game {
    private long id;
    private String imgurl;
    private String title;
    private String type;
    private String platforms;
    private String alias;
    private String company;
    private String pubdates;
    private String avg;
    private String summary;
}
