package com.htw.vbbs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htw.vbbs.domain.Game;
import com.htw.vbbs.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameservice;

    @RequestMapping("/list")
    public String getGameList(Model model, @RequestParam(defaultValue = "1",value = "pageNum")int pageNum){
        PageHelper.startPage(pageNum, 10);
        List<Game> gameList = gameservice.getGameList();
        PageInfo<Game> pageInfo = new PageInfo<>(gameList);
        model.addAttribute("pageInfo", pageInfo);
        return "gameList";
    }

    @RequestMapping("/info")
    public String getGameInfo(Model model, HttpServletRequest request, @RequestParam int id){
        Game game = gameservice.getById(id);
        model.addAttribute("game", game);
        return "gameInfo";
    }

}
