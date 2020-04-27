package com.htw.vbbs.controller;


import com.htw.vbbs.service.MovieService;
import com.htw.vbbs.vo.ComingMovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/top")
    public String getTopMovie () {

        return "top_movie";
    }

    @RequestMapping("/american")
    public String getAmericanMovie () {

        return "american_movie";
    }
}
