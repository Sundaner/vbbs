package com.htw.vbbs.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ComingMovieVo implements Serializable {
    private String img;
    private String title;
    private String pubdate;
    private String genres;
    private String url;
}
