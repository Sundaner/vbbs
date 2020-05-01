package com.htw.vbbs.controller;


import com.htw.vbbs.service.MovieService;
import com.htw.vbbs.vo.ComingMovieVo;
import com.htw.vbbs.vo.OneSubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

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
}
