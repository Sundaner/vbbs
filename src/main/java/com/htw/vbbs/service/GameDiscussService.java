package com.htw.vbbs.service;

import com.htw.vbbs.dao.GameDiscussMapper;
import com.htw.vbbs.domain.Comment;
import com.htw.vbbs.domain.GameDiscuss;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.vo.CommentView;
import com.htw.vbbs.vo.GameDisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameDiscussService {

    @Autowired
    private GameDiscussMapper gameDiscussMapper;
    @Autowired
    private UserService userService;

    public List<GameDiscuss> getGameDiscussList(int id) {
        return gameDiscussMapper.getGameDiscussList(id);
    }

    public List<GameDisVo> toDisVo(List<GameDiscuss> list) {
        List<GameDisVo> gameDisVos = new ArrayList<>();
        list.forEach(item ->{
            GameDisVo gvo = new GameDisVo();
            User user = userService.getSimpInfoById(item.getUserId());
            gvo.setUserId(item.getUserId());
            gvo.setUserImg(user.getPortrait());
            gvo.setUserName(user.getNickname());
            gvo.setCommentId(item.getGameDisId());
            gvo.setCreateTime(item.getCreateTime());
            gvo.setContent(item.getContent());
            if(item.getReplyId() != 0){
                GameDisVo par = new GameDisVo();
                GameDiscuss discuss = gameDiscussMapper.getById(item.getReplyId());
                User puer = userService.getSimpInfoById(discuss.getUserId());
                par.setUserId(discuss.getUserId());
                par.setUserImg(puer.getPortrait());
                par.setUserName(puer.getNickname());
                par.setCommentId(discuss.getGameDisId());
                par.setCreateTime(discuss.getCreateTime());
                par.setContent(discuss.getContent());
                gvo.setParent(par);
            }
            gameDisVos.add(gvo);
        });
        return gameDisVos;
    }

    public GameDisVo insert(int gameId, int disId, int userId, String content) {
        User user = userService.getSimpInfoById(userId);
        GameDiscuss comment = new GameDiscuss();
        comment.setGameId(gameId);
        comment.setUserId(userId);
        comment.setContent(content);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        comment.setCreateTime(timeStamp);
        comment.setReplyId(disId);
        int re = gameDiscussMapper.inerstNew(comment);

        GameDisVo commentVo = new GameDisVo();
        commentVo.setUserId(comment.getUserId());
        commentVo.setUserImg(user.getPortrait());
        commentVo.setUserName(user.getNickname());
        commentVo.setCommentId(comment.getGameDisId());
        commentVo.setCreateTime(comment.getCreateTime());
        commentVo.setContent(comment.getContent());
        GameDisVo parent = null;
        if(disId != 0){
            GameDiscuss par_comm = gameDiscussMapper.getById(disId);
            User par_user = userService.getSimpInfoById(par_comm.getUserId());
            parent = new GameDisVo();
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
