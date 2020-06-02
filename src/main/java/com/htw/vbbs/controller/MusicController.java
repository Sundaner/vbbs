package com.htw.vbbs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htw.vbbs.result.Result;
import com.htw.vbbs.domain.MusicDiscuss;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.music.model.MusicDetail;
import com.htw.vbbs.music.model.Rank;
import com.htw.vbbs.service.InterestService;
import com.htw.vbbs.service.MusicDiscussService;
import com.htw.vbbs.service.MusicService;
import com.htw.vbbs.vo.MusicDisVo;
import com.htw.vbbs.vo.ToMusDisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;
    @Autowired
    private MusicDiscussService musicDiscussService;
    @Autowired
    private InterestService interestService;

    @RequestMapping("/rank")
    public String getMusicRank (Model model, User user) {
        List<Rank> ranks = musicService.getRank();
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("length",ranks.size());
        model.addAttribute("rank", ranks);
        return "music/musicRank";
    }

    @RequestMapping("/detail")
    public String getMusicDetail (Model model, User user, @RequestParam String id, @RequestParam String type,
                                  @RequestParam(defaultValue = "1",value = "pageNum")int pageNum) {
        MusicDetail detail = musicService.getMusicDetail(type, id);

        PageHelper.startPage(pageNum, 20);
        List<MusicDiscuss> musicDiscusses = musicDiscussService.getMusicDiscussList(id);
        PageInfo<MusicDiscuss> page = new PageInfo<>(musicDiscusses);

        List<MusicDisVo> mvo = musicDiscussService.toDisVo(page.getList());
        PageInfo<MusicDisVo> pageInfo = new PageInfo<>(mvo);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setTotal(page.getTotal());
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("id", id);
        model.addAttribute("type", type);
        model.addAttribute("user", user);
        model.addAttribute("music", detail);
        model.addAttribute("pageInfo", pageInfo);
        return "music/musicDetail";
    }

    @RequestMapping("/search")
    public String searchMusic (Model model, User user,
                               @RequestParam(defaultValue = "0", value = "id")int id) {
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("id",id);
        return "music/searchMusic";
    }

    @RequestMapping("/searchInfo")
    public String getSearchResult (Model model, User user, @RequestParam String value,
                                    @RequestParam(defaultValue = "0", value = "start")int start) {
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("value", value);
        model.addAttribute("start",start);
        return "music/searchResult";
    }

    @RequestMapping("/searchDetail")
    public String searchMusicDetail (Model model, User user, @RequestParam String id,
                                     @RequestParam(defaultValue = "1",value = "pageNum")int pageNum) {

        PageHelper.startPage(pageNum, 20);
        List<MusicDiscuss> musicDiscusses = musicDiscussService.getMusicDiscussList("d"+id);
        PageInfo<MusicDiscuss> page = new PageInfo<>(musicDiscusses);

        List<MusicDisVo> mvo = musicDiscussService.toDisVo(page.getList());
        PageInfo<MusicDisVo> pageInfo = new PageInfo<>(mvo);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setTotal(page.getTotal());
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("id",id);
        model.addAttribute("pageInfo", pageInfo);
        return "music/searchMusicDetail";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public Result<MusicDisVo> doSubmit(ToMusDisVo tomusDisVo, User user){
        int type = tomusDisVo.getType();
        String musicId = tomusDisVo.getMusicId();
        if(type == 1){
            musicId = "d" + musicId;
        }
        int disId = tomusDisVo.getDisId();
        String content = tomusDisVo.getContent();
        int userId = user.getUserId();
        MusicDisVo vo = musicDiscussService.insert(musicId, disId, userId, content);
        return Result.success(vo);
    }
}
