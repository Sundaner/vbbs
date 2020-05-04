package com.htw.vbbs.service;

import com.htw.vbbs.dao.InvitationMapper;
import com.htw.vbbs.domain.Comment;
import com.htw.vbbs.domain.Invitation;
import com.htw.vbbs.vo.InvitationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class InvitationService {

    @Autowired
    private InvitationMapper invitationMapper;

    @Autowired
    private CommentService commentService;

    public int insertInvitation(Invitation invitation){
        return invitationMapper.insert(invitation);
    }

    public int insertVo(InvitationVo vo){
        Invitation invitation = new Invitation();
        invitation.setUserId(101);
        invitation.setTitle(vo.getTitle());
        invitation.setContent(vo.getContent());
        invitation.setZan(0);
        invitation.setStore(0);
        invitation.setType(vo.getType());
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        invitation.setCreateTime(timeStamp);
        invitation.setUpdateTime(timeStamp);
        insertInvitation(invitation);
        return invitation.getInvitationId();
    }

    public Invitation getById(int id) {
        return invitationMapper.getById(id);
    }

    public Invitation findInviComments(int id){
        Invitation invitation = null;
        if(commentService.hasComment(id) == null){
            invitation = invitationMapper.getById(id);
        }else {
            invitation = invitationMapper.findInviComments(id);
            List<Comment> comments = invitation.getCommentList();
            if(!CollectionUtils.isEmpty(comments)){
                comments.forEach(item -> {
                    if(item.getReplyId() != 0){
                        item.setParent(commentService.getById(item.getReplyId()));
                    }
                });
            }
        }
        return invitation;
    }
}
