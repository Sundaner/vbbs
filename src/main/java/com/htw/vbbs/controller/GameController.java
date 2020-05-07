package com.htw.vbbs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htw.vbbs.Result.Result;
import com.htw.vbbs.domain.Game;
import com.htw.vbbs.domain.GameDiscuss;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.service.GameDiscussService;
import com.htw.vbbs.service.GameService;
import com.htw.vbbs.service.InterestService;
import com.htw.vbbs.vo.CommentView;
import com.htw.vbbs.vo.GameDisVo;
import com.htw.vbbs.vo.ToCommentVo;
import com.htw.vbbs.vo.ToGaDisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameservice;
    @Autowired
    private GameDiscussService gameDiscussService;
    @Autowired
    private InterestService interestService;

    @RequestMapping("/list")
    public String getGameList(Model model, User user,
                              @RequestParam(defaultValue = "1",value = "pageNum")int pageNum){
        PageHelper.startPage(pageNum, 10);
        List<Game> gameList = gameservice.getGameList();
        PageInfo<Game> pageInfo = new PageInfo<>(gameList);
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("pageInfo", pageInfo);
        return "game/gameList";
    }

    @RequestMapping("/info")
    public String getGameInfo(Model model, User user,
                              @RequestParam int id, @RequestParam(defaultValue = "1",value = "pageNum")int pageNum){
        Game game = gameservice.getById(id);
        PageHelper.startPage(pageNum, 20);
        List<GameDiscuss> gameDiscussList = gameDiscussService.getGameDiscussList(id);
        PageInfo<GameDiscuss> page = new PageInfo<>(gameDiscussList);

        List<GameDisVo> gameDisVos = gameDiscussService.toDisVo(page.getList());
        PageInfo<GameDisVo> pageInfo = new PageInfo<>(gameDisVos);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setTotal(page.getTotal());
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("game", game);
        model.addAttribute("pageInfo",pageInfo);
        return "game/gameInfo";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public Result<GameDisVo> doSubmit(ToGaDisVo toGaDisVo, User user){
        int gameId = toGaDisVo.getGameId();
        int disId = toGaDisVo.getDisId();
        String content = toGaDisVo.getContent();
        int userId = user.getUserId();
        GameDisVo vo = gameDiscussService.insert(gameId, disId, userId, content);
        return Result.success(vo);
    }
}
