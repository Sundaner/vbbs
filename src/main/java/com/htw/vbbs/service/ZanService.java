package com.htw.vbbs.service;

import com.htw.vbbs.dao.ZanMapper;
import com.htw.vbbs.domain.Zan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZanService {

    @Autowired
    private ZanMapper zanMapper;

    public int zan(int userId, int invitaitonId){
       Zan zan = new Zan();
       zan.setUserId(userId);
       zan.setInvitationId(invitaitonId);
       return zanMapper.insertZan(zan);
    }

    public int cancelZan(int userId, int invitaitonId){
        Zan zan = new Zan();
        zan.setUserId(userId);
        zan.setInvitationId(invitaitonId);
        return zanMapper.deleteZan(zan);
    }

    public int hasZan(int userId, int invitaitonId){
        Zan zan = new Zan();
        zan.setUserId(userId);
        zan.setInvitationId(invitaitonId);
        if(zanMapper.hasZan(zan) != null){
            return 1;
        }
        return 0;
    }

    public int deleteArt(int id) {
        return zanMapper.deleteArt(id);
    }
}
