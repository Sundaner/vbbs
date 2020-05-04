package com.htw.vbbs.controller;


import com.github.pagehelper.PageInfo;
import com.htw.vbbs.Result.Result;
import com.htw.vbbs.domain.GameDiscuss;
import com.htw.vbbs.domain.MovieDiscuss;
import com.htw.vbbs.service.MovieDiscussService;
import com.htw.vbbs.service.MovieService;
import com.htw.vbbs.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieDiscussService movieDiscussService;

    @RequestMapping("/coming")
    public String getComingMovie (Model model, HttpServletRequest request) {
        List<ComingMovieVo> movies = movieService.getComingMovie();
        if(movies.size()%2 == 1){
            movies.add(null);
        }
        model.addAttribute("movies", movies);
        model.addAttribute("length", movies.size());
        return "coming_movie";
    }

    @RequestMapping("/info")
    public String getMovieInfo (Model model, HttpServletRequest request, @RequestParam String id) {
        OneSubjectVo subjectVo = movieService.getMovieInfo(id);

        List<MovieDiscuss> movieDiscussList = movieDiscussService.getMovieDiscussList(id);
        PageInfo<MovieDiscuss> page = new PageInfo<>(movieDiscussList);
        List<MovieDisVo> movieDisVos = movieDiscussService.toDisVo(page.getList());
        PageInfo<MovieDisVo> pageInfo = new PageInfo<>(movieDisVos);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setTotal(page.getTotal());

        model.addAttribute("movie", subjectVo);
        return "movieInfo";
    }

    @RequestMapping("/top")
    public String getTopMovie (Model model, HttpServletRequest request) {
        List<ComingMovieVo> movies = movieService.getTopMovie();
        if(movies.size()%2 == 1){
            movies.add(null);
        }
        model.addAttribute("movies", movies);
        model.addAttribute("length", movies.size());
        return "top_movie";
    }

    @RequestMapping("/usbox")
    public String getAmericanMovie (Model model, HttpServletRequest request) {
        List<ComingMovieVo> movies = movieService.getAmericanMovie();
        if(movies.size()%2 == 1){
            movies.add(null);
        }
        model.addAttribute("movies", movies);
        model.addAttribute("length", movies.size());
        return "usbox_movie";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public Result<MovieDisVo> doSubmit(ToMivDisVo toMivDisVo){
        int movieId = toMivDisVo.getMvovieId();
        int disId = toMivDisVo.getDisId();
        String content = toMivDisVo.getContent();
        int userId = 101;
        MovieDisVo vo = movieDiscussService.insert(movieId, disId, userId, content);
        return Result.success(vo);
    }
}
