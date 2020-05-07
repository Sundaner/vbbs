package com.htw.vbbs.service;

import com.htw.vbbs.dao.InvitationMapper;
import com.htw.vbbs.domain.*;
import com.htw.vbbs.vo.InvitationVo;
import com.htw.vbbs.vo.ToInvitationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.*;

@Service
public class InvitationService {

    @Autowired
    private InvitationMapper invitationMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ZanService zanService;
    @Autowired
    private StoreService storeService;


    public int insertInvitation(Invitation invitation){
        return invitationMapper.insert(invitation);
    }

    public int insertVo(ToInvitationVo vo, int userId){
        Invitation invitation = new Invitation();
        invitation.setUserId(userId);
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

    public Invitation getSimpInvById(int id){
        return invitationMapper.getSimpInvById(id);
    }

    public List<Invitation> getAll(int type){
        return invitationMapper.getAll(type);
    }

    public List<InvitationVo> toVoList(List<Invitation> list) {
        List<InvitationVo> voList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            list.forEach(item -> {
                voList.add(toVo(item));
            });
        }
        return voList;
    }

    public InvitationVo toVo(Invitation item){
        InvitationVo vo = new InvitationVo();
        User user = userService.getSimpInfoById(item.getUserId());
        vo.setUserId(item.getUserId());
        vo.setInvitationId(item.getInvitationId());
        vo.setUserName(user.getNickname());
        vo.setUserImg(user.getPortrait());
        vo.setTitle(item.getTitle());
        vo.setType(getTypeIfno(item.getType()));
        vo.setUpdateTime(item.getUpdateTime());
        vo.setZan(item.getZan());
        return vo;
    }

    public String getTypeIfno(int type){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "交流");
        map.put(2, "电影说");
        map.put(3, "音乐说");
        map.put(4, "游戏说");
        map.put(5, "其他");
        return map.get(type);
    }

    public List<Invitation> getUserInvis(int id) {
        return invitationMapper.getUserInvis(id);
    }

    @Transactional
    public boolean cancelZan(int userId, int invitationId) {
        zanService.cancelZan(userId,invitationId);
        invitationMapper.cancelZan(invitationId);
        return true;
    }

    @Transactional
    public boolean zan(int userId, int invitationId) {
        zanService.zan(userId, invitationId);
        invitationMapper.zan(invitationId);
        return true;
    }


    @Transactional
    public boolean store(int userId, int invitationId) {
        storeService.store(userId, invitationId);
        invitationMapper.store(invitationId);
        return true;
    }

    @Transactional
    public boolean cancelStore(int userId, int invitationId) {
        storeService.cancelStore(userId, invitationId);
        invitationMapper.cancelStore(invitationId);
        return true;
    }

    public Invitation getUpdateTimeByid(int id){
        return invitationMapper.getUpdateTimeByid(id);
    }

    @Transactional
    public int update(int id, ToInvitationVo vo) {
        Invitation invi = new Invitation();
        invi.setInvitationId(id);
        invi.setTitle(vo.getTitle());
        invi.setType(vo.getType());
        invi.setContent(vo.getContent());
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        invi.setUpdateTime(timeStamp);
        storeService.updateTime(id, timeStamp);

        return invitationMapper.updateTime(invi);
    }

    @Transactional
    public int delete(int id) {
        invitationMapper.deleteArt(id);
        commentService.deleteArt(id);
        storeService.deleteArt(id);
        zanService.deleteArt(id);
        return 1;
    }
}
