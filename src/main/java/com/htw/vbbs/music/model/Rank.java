package com.htw.vbbs.music.model;

import lombok.Data;

import java.util.List;

@Data
public class Rank {
    private int count;
    private int type;
    private String name;
    private List<Content> content;
}
