package com.htw.vbbs.dao;

import com.htw.vbbs.domain.Invitation;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface InvitationMapper {

    int insert(Invitation invitation);

    Invitation getById(int id);

    Invitation findInviComments(int id);

    List<Invitation> getAll(int type);

    List<Invitation> getUserInvis(int id);

    int zan(int invitationId);

    int cancelZan(int invitationId);

    int store(int invitationId);

    int cancelStore(int invitationId);

    List<Invitation> getMyStore(int userId);

    Invitation getSimpInvById(int id);

    Invitation getUpdateTimeByid(int id);

    int updateTime(Invitation invitation);

    int deleteArt(int id);
}
