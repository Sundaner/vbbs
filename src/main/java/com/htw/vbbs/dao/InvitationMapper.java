package com.htw.vbbs.dao;

import com.htw.vbbs.domain.Invitation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InvitationMapper {

    int insert(Invitation invitation);

    Invitation getById(int id);

    Invitation findInviComments(int id);
}
