package com.htw.vbbs.dao;

import com.htw.vbbs.domain.GameDiscuss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameDiscussMapper {
    List<GameDiscuss> getGameDiscussList(int id);

    GameDiscuss getById(int id);

    int inerstNew(GameDiscuss comment);
}
