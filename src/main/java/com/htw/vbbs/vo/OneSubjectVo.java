package com.htw.vbbs.vo;

import com.htw.vbbs.douban.Casts;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OneSubjectVo implements Serializable {
    private double avg;
    private String year;
    private String img;
    private String title;
    private String writers;
    private String pubdates;
    private String genres;
    private List<Casts> casts;
    private String castsNames;
    private String summary;
    private String directors;

}
