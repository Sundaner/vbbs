package com.htw.vbbs.service;

import com.htw.vbbs.dao.StoreMapper;
import com.htw.vbbs.domain.Invitation;
import com.htw.vbbs.domain.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private InvitationService invitationService;

    public int store(int userId, int invitationId){
        Invitation invitation = invitationService.getUpdateTimeByid(invitationId);
        Store store = new Store();
        store.setInvitationId(invitationId);
        store.setUserId(userId);
        store.setUpdateTime(invitation.getUpdateTime());
        return storeMapper.insertStore(store);
    }

    public int cancelStore(int userId, int invitationId){
        Store store = new Store();
        store.setInvitationId(invitationId);
        store.setUserId(userId);
        return storeMapper.deleteStore(store);
    }

    public int hasStore(int userId, int invitationId){
        Store store = new Store();
        store.setInvitationId(invitationId);
        store.setUserId(userId);
        if(storeMapper.hasStore(store) != null){
            return 1;
        }
        return 0;
    }

    public List<Store> getMyStore(int userId) {
        return storeMapper.getMyStore(userId);
    }

    public int updateTime(int invitationId, Timestamp timestamp){
        Store store = new Store();
        store.setUpdateTime(timestamp);
        store.setInvitationId(invitationId);
        return storeMapper.updateTime(store);
    }

    public int deleteArt(int id) {
        return storeMapper.deleteArt(id);
    }
}
