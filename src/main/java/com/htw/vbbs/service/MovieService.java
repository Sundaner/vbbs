package com.htw.vbbs.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.htw.vbbs.douban.*;
import com.htw.vbbs.util.HttpUtil;
import com.htw.vbbs.vo.ComingMovieVo;
import com.htw.vbbs.vo.OneSubjectVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MovieService {
    public static final String DOUBAN_URL = "https://douban.uieee.com";
    public static final String ENCODE = "UTF-8";
    public static final String GET = "GET";

    public List<ComingMovieVo> getComingMovie(){
        String url = DOUBAN_URL + "/v2/movie/coming_soon";
        HashMap<String, String> param = Maps.newHashMap();
        String result = HttpUtil.httpRequst(url, param, ENCODE, GET);
        DoubanMovie movies = JSON.parseObject(result, DoubanMovie.class);
        List<ComingMovieVo> comingMovieVos = new ArrayList<>();
        if(movies  != null){
            List<Subjects> subjects = movies.getSubjects();
            subjects.forEach(item -> {
                ComingMovieVo cm = new ComingMovieVo();
                cm.setTitle(item.getTitle());
                cm.setImg(item.getImages().get("small"));
                cm.setId(item.getId());
                cm.setGenres(formatList(item.getGenres()));
                cm.setPubdate(item.getPubdates().get(0).substring(5));
                comingMovieVos.add(cm);
            });
        }
        return comingMovieVos;
    }

    public OneSubjectVo getMovieInfo(int id){
        String url = DOUBAN_URL + "/v2/movie/subject/" + id;
        HashMap<String, String> param = Maps.newHashMap();
        String result = HttpUtil.httpRequst(url, param, ENCODE, GET);
        OneSubject subject = JSON.parseObject(result, OneSubject.class);
        OneSubjectVo onesub = new OneSubjectVo();
        if(subject != null){
            onesub.setAvg(subject.getRating().getAverage());
            onesub.setYear(subject.getYear());
            onesub.setImg(subject.getImages().get("small"));
            onesub.setTitle(subject.getTitle());
            onesub.setWriters(formatList(subject.getWritersNames()));
            onesub.setPubdates(subject.getPubdates().get(0));
            onesub.setGenres(formatList(subject.getGenres()));
            onesub.setCasts(subject.getOnlyPreFourCasts());
            onesub.setCastsNames(formatList(subject.getCastsName()));
            onesub.setSummary(subject.getSummary());
            onesub.setDirectors(formatList(subject.getdirectorsName()));
        }
        return onesub;
    }

    public List<ComingMovieVo> getTopMovie(){
        String url = DOUBAN_URL + "/v2/movie/top250";
        HashMap<String, String> param = Maps.newHashMap();
        String result = HttpUtil.httpRequst(url, param, ENCODE, GET);
        DoubanMovie movies = JSON.parseObject(result, DoubanMovie.class);
        List<ComingMovieVo> comingMovieVos = new ArrayList<>();
        if(movies  != null){
            List<Subjects> subjects = movies.getSubjects();
            subjects.forEach(item -> {
                ComingMovieVo cm = new ComingMovieVo();
                cm.setTitle(item.getTitle());
                cm.setImg(item.getImages().get("small"));
                cm.setId(item.getId());
                cm.setGenres(formatList(item.getGenres()));
                cm.setPubdate(item.getPubdates().get(0).substring(5));
                comingMovieVos.add(cm);
            });
        }
        return comingMovieVos;
    }

    public List<ComingMovieVo> getAmericanMovie(){
        String url = DOUBAN_URL + "/v2/movie/us_box";
        HashMap<String, String> param = Maps.newHashMap();
        String result = HttpUtil.httpRequst(url, param, ENCODE, GET);
        USBox movies = JSON.parseObject(result, USBox.class);
        List<ComingMovieVo> comingMovieVos = new ArrayList<>();
        if(movies  != null){
            List<UsSubjects> subjects = movies.getSubjects();
            subjects.forEach(sub -> {
                ComingMovieVo cm = new ComingMovieVo();
                OneSubject item = sub.getSubject();
                cm.setTitle(item.getTitle());
                cm.setImg(item.getImages().get("small"));
                cm.setId(item.getId());
                cm.setGenres(formatList(item.getGenres()));
                cm.setPubdate(item.getPubdates().get(0).substring(5));
                comingMovieVos.add(cm);
            });
        }
        return comingMovieVos;
    }

    public String formatList(List<String> list){
        StringBuilder genres = new StringBuilder();
        for(int i = 0; i < list.size()&& i < 4; i++){
            genres.append(list.get(i)).append(" / ");
        }
        if(genres.length() > 2){
            genres.deleteCharAt(genres.length() - 2);
        }
        return genres.toString();
    }

}
