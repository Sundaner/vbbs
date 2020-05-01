package com.htw.vbbs.controller;

import com.htw.vbbs.music.model.MusicDetail;
import com.htw.vbbs.music.model.Rank;
import com.htw.vbbs.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping("/rank")
    public String getMusicRank (Model model, HttpServletRequest request) {
        List<Rank> ranks = musicService.getRank();
        model.addAttribute("length",ranks.size());
        model.addAttribute("rank", ranks);
        return "musicRank";
    }

    @RequestMapping("/detail")
    public String getMusicDetail (Model model, HttpServletRequest request, @RequestParam long id, @RequestParam String type) {
        MusicDetail detail = musicService.getMusicDetail(type, id);
        model.addAttribute("music", detail);
        return "musicDetail";
    }

    @RequestMapping("/search")
    public String searchMusic (Model model, HttpServletRequest request, @RequestParam(defaultValue = "0", value = "id")int id) {
        model.addAttribute("id",id);
        return "searchMusic";
    }

    @RequestMapping("/searchInfo")
    public String getSearchResult (Model model, HttpServletRequest request, @RequestParam String value,
                                    @RequestParam(defaultValue = "0", value = "start")int start) {
        model.addAttribute("value", value);
        model.addAttribute("start",start);
        return "searchResult";
    }
}
