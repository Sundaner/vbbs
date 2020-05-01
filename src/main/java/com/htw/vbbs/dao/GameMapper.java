package com.htw.vbbs.dao;

import com.htw.vbbs.domain.Game;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameMapper {

    List<Game> queryGamePage(@Param("offset")int offset, @Param("pageSize")int pageSize);

    Game queryGameById(int id);

    List<Game> getGameList();
}
