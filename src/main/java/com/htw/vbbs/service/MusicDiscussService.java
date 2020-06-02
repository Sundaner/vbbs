package com.htw.vbbs.service;

import com.htw.vbbs.dao.MusicDiscussMapper;
import com.htw.vbbs.domain.MusicDiscuss;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.vo.MusicDisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MusicDiscussService {

    @Autowired
    private MusicDiscussMapper musicDiscussMapper;
    @Autowired
    private UserService userService;

    public List<MusicDiscuss> getMusicDiscussList(String id) {
        return musicDiscussMapper.getMusicDisList(id);
    }

    public List<MusicDisVo> toDisVo(List<MusicDiscuss> list) {
        List<MusicDisVo> musicDisVos = new ArrayList<>();
        list.forEach(item ->{
            MusicDisVo gvo = new MusicDisVo();
            User user = userService.getSimpInfoById(item.getUserId());
            gvo.setUserId(item.getUserId());
            gvo.setUserImg(user.getPortrait());
            gvo.setUserName(user.getNickname());
            gvo.setCommentId(item.getMusicDisId());
            gvo.setCreateTime(item.getCreateTime());
            gvo.setContent(item.getContent());
            if(item.getReplyId() != 0){
                MusicDisVo par = new MusicDisVo();
                MusicDiscuss discuss = musicDiscussMapper.getById(item.getReplyId());
                User puer = userService.getSimpInfoById(discuss.getUserId());
                par.setUserId(discuss.getUserId());
                par.setUserImg(puer.getPortrait());
                par.setUserName(puer.getNickname());
                par.setCommentId(discuss.getMusicDisId());
                par.setCreateTime(discuss.getCreateTime());
                par.setContent(discuss.getContent());
                gvo.setParent(par);
            }
            musicDisVos.add(gvo);
        });
        return musicDisVos;
    }

    public MusicDisVo insert(String musicId, int disId, int userId, String content) {
        User user = userService.getSimpInfoById(userId);
        MusicDiscuss comment = new MusicDiscuss();
        comment.setMusicId(musicId);
        comment.setUserId(userId);
        comment.setContent(content);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        comment.setCreateTime(timeStamp);
        comment.setReplyId(disId);
        int re = musicDiscussMapper.inerstNew(comment);

        MusicDisVo commentVo = new MusicDisVo();
        commentVo.setUserId(comment.getUserId());
        commentVo.setUserImg(user.getPortrait());
        commentVo.setUserName(user.getNickname());
        commentVo.setCommentId(comment.getMusicDisId());
        commentVo.setCreateTime(comment.getCreateTime());
        commentVo.setContent(comment.getContent());
        MusicDisVo parent = null;
        if(disId != 0){
            MusicDiscuss par_comm = musicDiscussMapper.getById(disId);
            User par_user = userService.getSimpInfoById(par_comm.getUserId());
            parent = new MusicDisVo();
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
