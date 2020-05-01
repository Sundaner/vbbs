package com.htw.vbbs.service;

import com.htw.vbbs.dao.GameMapper;
import com.htw.vbbs.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameMapper gameMapper;

    public List<Game> getGamePage(int start, int pageSize){
        int offset = start * pageSize;
        List<Game> gameList = gameMapper.queryGamePage(offset, pageSize);
        return gameList;
    }

    public Game getById(int id){
        return gameMapper.queryGameById(id);
    }

    public List<Game> getGameList(){
        return gameMapper.getGameList();
    }
}
