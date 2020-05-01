package com.htw.vbbs.douban;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class OneSubject implements Serializable {
    // 评分分布
    private Rating rating;
    //2020
    private String year;
    //samll img
    private HashMap<String,String> images;
    //豆瓣url地址
    private String alt;
    //豆瓣电影id : 34768418
    private String id;
    //金禅降魔
    private String title;
    //编剧 ：吴军平
    private List<Writers> writers;
    // ["2020-05-08(中国大陆)"]
    private List<String> pubdates;
    //["动作","奇幻","冒险"],
    private List<String> genres;
    //主演: 释小龙 / 胡军 / 姚星彤 / 李子雄
    private List<Casts> casts;
    //简介 ：除妖组织金蝉司首领南宫昼（邹兆龙饰）决战妖王夜白冥
    private String summary;
    //导演: 彭发 / 王凯 / 程中豪
    private List<Directors> directors;
    
    public List<String> getdirectorsName(){
        List<String> names = new ArrayList<>();
        directors.forEach(item -> {
            names.add(item.getName());
        });
        return names;
    }

    public List<String> getCastsName(){
        List<String> names = new ArrayList<>();
        casts.forEach(item -> {
            names.add(item.getName());
        });
        return names;
    }

    public List<String> getWritersNames(){
        List<String> names = new ArrayList<>();
        writers.forEach(item -> {
            names.add(item.getName());
        });
        return names;
    }

    public List<Casts> getOnlyPreFourCasts(){
        List<Casts> preFour = new ArrayList<>();
        int i = 0;
        while (preFour.size() < 4 && i < casts.size()){
            if(casts.get(i).getAvatars() != null){
                preFour.add(casts.get(i));
            }
            i++;
        }
        return preFour;
    }
}
