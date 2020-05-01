package com.htw.vbbs.music.model;

import lombok.Data;

@Data
public class Content {
    private long song_id;
    private int rank_change;
    private String author;
    private String pic_small;
    private String title;
    private String album_title;
}
