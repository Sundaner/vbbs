package com.htw.vbbs.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.htw.vbbs.douban.DoubanMovie;
import com.htw.vbbs.douban.Subjects;
import com.htw.vbbs.util.HttpUtils;
import com.htw.vbbs.vo.ComingMovieVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    public static final String DOUBAN_URL = "https://douban.uieee.com";
    public static final String ENCODE = "UTF-8";
    public static final String GET = "GET";

    public List<ComingMovieVo> getComingMovie(){
        String url = DOUBAN_URL + "/v2/movie/coming_soon";
        HashMap<String, String> param = Maps.newHashMap();
        DoubanMovie movies = getMovieInfo(url, param, ENCODE, GET);
        List<ComingMovieVo> comingMovieVos = new ArrayList<>();
        if(movies  != null){
            List<Subjects> subjects = movies.getSubjects();
            subjects.forEach(item -> {
                ComingMovieVo cm = new ComingMovieVo();
                cm.setTitle(item.getTitle());
                cm.setImg(item.getImages().get("small"));
                cm.setUrl(item.getAlt());
                StringBuilder genres = new StringBuilder();
                item.getGenres().forEach(type ->{
                    genres.append(type).append("/");
                });
                genres.deleteCharAt(genres.length() - 1);
                cm.setGenres(genres.toString());
                cm.setPubdate(item.getMainland_pubdate().substring(5));
                comingMovieVos.add(cm);
            });
        }
        return comingMovieVos;
    }

    private DoubanMovie getMovieInfo(String url, Map params, String encode, String action) {
        DoubanMovie info = new DoubanMovie();
        try {
            info = JSON.parseObject(HttpUtils.httpRequst(url, params, encode, action), DoubanMovie.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
