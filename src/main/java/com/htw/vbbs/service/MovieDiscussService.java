package com.htw.vbbs.service;

import com.htw.vbbs.dao.MovieDiscussMapper;
import com.htw.vbbs.domain.GameDiscuss;
import com.htw.vbbs.domain.MovieDiscuss;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.vo.GameDisVo;
import com.htw.vbbs.vo.MovieDisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieDiscussService {

    @Autowired
    private MovieDiscussMapper movieDiscussMapper;
    @Autowired
    private UserService userService;

    public List<MovieDiscuss> getMovieDiscussList(String id) {
        return movieDiscussMapper.getDisList(id);
    }

    public List<MovieDisVo> toDisVo(List<MovieDiscuss> list) {
        List<MovieDisVo> movieDisVos = new ArrayList<>();
        list.forEach(item ->{
            MovieDisVo gvo = new MovieDisVo();
            User user = userService.getSimpInfoById(item.getUserId());
            gvo.setUserId(item.getUserId());
            gvo.setUserImg(user.getPortrait());
            gvo.setUserName(user.getNickname());
            gvo.setCommentId(item.getMovieDisId());
            gvo.setCreateTime(item.getCreateTime());
            gvo.setContent(item.getContent());
            if(item.getReplyId() != 0){
                MovieDisVo par = new MovieDisVo();
                MovieDiscuss discuss = movieDiscussMapper.getById(item.getReplyId());
                User puer = userService.getSimpInfoById(discuss.getUserId());
                par.setUserId(discuss.getUserId());
                par.setUserImg(puer.getPortrait());
                par.setUserName(puer.getNickname());
                par.setCommentId(discuss.getMovieDisId());
                par.setCreateTime(discuss.getCreateTime());
                par.setContent(discuss.getContent());
                gvo.setParent(par);
            }
            movieDisVos.add(gvo);
        });
        return movieDisVos;
    }

    public MovieDisVo insert(int movieId, int disId, int userId, String content) {
        User user = userService.getSimpInfoById(userId);
        MovieDiscuss comment = new MovieDiscuss();
        comment.setMovieId(movieId);
        comment.setUserId(userId);
        comment.setContent(content);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        comment.setCreateTime(timeStamp);
        comment.setReplyId(disId);
        int re = movieDiscussMapper.inerstNew(comment);

        MovieDisVo commentVo = new MovieDisVo();
        commentVo.setUserId(comment.getUserId());
        commentVo.setUserImg(user.getPortrait());
        commentVo.setUserName(user.getNickname());
        commentVo.setCommentId(comment.getMovieDisId());
        commentVo.setCreateTime(comment.getCreateTime());
        commentVo.setContent(comment.getContent());
        MovieDisVo parent = null;
        if(disId != 0){
            MovieDiscuss par_comm = movieDiscussMapper.getById(disId);
            User par_user = userService.getSimpInfoById(par_comm.getUserId());
            parent = new MovieDisVo();
            parent.setCommentId(disId);
            parent.setUserId(par_user.getUserId());
            parent.setUserName(par_user.getNickname());
            parent.setUserImg(par_user.getPortrait());
            parent.setCreateTime(par_comm.getCreateTime());
            parent.setContent(par_comm.getContent());
        }
        commentVo.setParent(parent);
        return commentVo;
    }
}
