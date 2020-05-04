package com.htw.vbbs.controller;

import com.github.pagehelper.PageInfo;
import com.htw.vbbs.Result.Result;
import com.htw.vbbs.domain.GameDiscuss;
import com.htw.vbbs.domain.MusicDiscuss;
import com.htw.vbbs.music.model.MusicDetail;
import com.htw.vbbs.music.model.Rank;
import com.htw.vbbs.service.MusicDiscussService;
import com.htw.vbbs.service.MusicService;
import com.htw.vbbs.vo.GameDisVo;
import com.htw.vbbs.vo.MusicDisVo;
import com.htw.vbbs.vo.ToGaDisVo;
import com.htw.vbbs.vo.ToMusDisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;
    @Autowired
    private MusicDiscussService musicDiscussService;

    @RequestMapping("/rank")
    public String getMusicRank (Model model, HttpServletRequest request) {
        List<Rank> ranks = musicService.getRank();
        model.addAttribute("length",ranks.size());
        model.addAttribute("rank", ranks);
        return "music/musicRank";
    }

    @RequestMapping("/detail")
    public String getMusicDetail (Model model, HttpServletRequest request, @RequestParam long id, @RequestParam String type,
                                  @RequestParam(defaultValue = "1",value = "pageNum")int pageNum) {
        MusicDetail detail = musicService.getMusicDetail(type, id);

        List<MusicDiscuss> musicDiscusses = musicDiscussService.getMusicDiscussList(id);
        PageInfo<MusicDiscuss> page = new PageInfo<>(musicDiscusses);
        List<MusicDisVo> mvo = musicDiscussService.toDisVo(page.getList());
        PageInfo<MusicDisVo> pageInfo = new PageInfo<>(mvo);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setTotal(page.getTotal());

        model.addAttribute("music", detail);
        model.addAttribute("pageInfo", pageInfo);
        return "music/musicDetail";
    }

    @RequestMapping("/search")
    public String searchMusic (Model model, HttpServletRequest request, @RequestParam(defaultValue = "0", value = "id")int id) {
        model.addAttribute("id",id);
        return "music/searchMusic";
    }

    @RequestMapping("/searchInfo")
    public String getSearchResult (Model model, HttpServletRequest request, @RequestParam String value,
                                    @RequestParam(defaultValue = "0", value = "start")int start) {
        model.addAttribute("value", value);
        model.addAttribute("start",start);
        return "music/searchResult";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public Result<MusicDisVo> doSubmit(ToMusDisVo tomusDisVo){
        int musicId = tomusDisVo.getMusicId();
        int disId = tomusDisVo.getDisId();
        String content = tomusDisVo.getContent();
        int userId = 101;
        MusicDisVo vo = musicDiscussService.insert(musicId, disId, userId, content);
        return Result.success(vo);
    }
}
