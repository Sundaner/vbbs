package com.htw.vbbs.service;

import com.htw.vbbs.domain.Game;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {
    @Autowired
    private GameService gameService;

    @Test
    public void testGameById(){
        int id = 26817171;
       Game game = gameService.getById(id);
        System.out.println(game);
    }

    @Test
    public void testPage(){
        int start = 0;
        int pageSize = 10;
        List<Game> list = gameService.getGamePage(start, pageSize);
        System.out.println(list);
    }

    @Test
    public void test(){
        int start = 0;
        int pageSize = 10;
        List<Game> list = gameService.getGamePage(start, pageSize);
        System.out.println(list);
    }
}
