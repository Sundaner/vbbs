package com.htw.vbbs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htw.vbbs.result.Result;
import com.htw.vbbs.domain.MovieDiscuss;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.service.InterestService;
import com.htw.vbbs.service.MovieDiscussService;
import com.htw.vbbs.service.MovieService;
import com.htw.vbbs.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieDiscussService movieDiscussService;
    @Autowired
    private InterestService interestService;

    @RequestMapping("/coming")
    public String getComingMovie (Model model, User user) {
        List<ComingMovieVo> movies = movieService.getComingMovie();
        if(movies.size()%2 == 1){
            movies.add(null);
        }
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("movies", movies);
        model.addAttribute("length", movies.size());
        return "movie/coming_movie";
    }

    @RequestMapping("/info")
    public String getMovieInfo (Model model, User user, @RequestParam int id,
                                @RequestParam(defaultValue = "1",value = "pageNum")int pageNum) {
        OneSubjectVo subjectVo = movieService.getMovieInfo(id);

        PageHelper.startPage(pageNum, 20);
        List<MovieDiscuss> movieDiscussList = movieDiscussService.getMovieDiscussList(id);
        PageInfo<MovieDiscuss> page = new PageInfo<>(movieDiscussList);

        List<MovieDisVo> movieDisVos = movieDiscussService.toDisVo(page.getList());
        PageInfo<MovieDisVo> pageInfo = new PageInfo<>(movieDisVos);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setTotal(page.getTotal());
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        model.addAttribute("movie", subjectVo);
        model.addAttribute("pageInfo", pageInfo);
        return "movie/movieInfo";
    }

    @RequestMapping("/top")
    public String getTopMovie (Model model, User user,
                               @RequestParam(defaultValue = "1",value = "pageNum")int pageNum) {
        List<ComingMovieVo> movies = movieService.getTopMovie(pageNum);
        if(movies.size()%2 == 1){
            movies.add(null);
        }
        List<User> inter = interestService.getMyInterest(user.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("inter", inter);
        model.addAttribute("movies", movies);
        model.addAttribute("length", movies.size());
        model.addAttribute("pageNum", pageNum);
        return "movie/top_movie";
    }

    @RequestMapping("/usbox")
    public String getAmericanMovie (Model model, User user) {
        List<ComingMovieVo> movies = movieService.getAmericanMovie();
        if(movies.size()%2 == 1){
            movies.add(null);
        }
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("movies", movies);
        model.addAttribute("length", movies.size());
        return "movie/usbox_movie";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public Result<MovieDisVo> doSubmit(ToMivDisVo toMivDisVo, User user){
        int movieId = toMivDisVo.getMovieId();
        int disId = toMivDisVo.getDisId();
        String content = toMivDisVo.getContent();
        int userId = user.getUserId();
        MovieDisVo vo = movieDiscussService.insert(movieId, disId, userId, content);
        return Result.success(vo);
    }
}
