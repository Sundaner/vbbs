package com.htw.vbbs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htw.vbbs.domain.Invitation;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.service.InterestService;
import com.htw.vbbs.service.InvitationService;
import com.htw.vbbs.vo.InvitationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private InvitationService invitationService;
    @Autowired
    private InterestService interestService;

    @RequestMapping("/home")
    public String getGameList(Model model , User user,
                              @RequestParam(defaultValue = "1",value = "pageNum")int pageNum,
                              @RequestParam(defaultValue = "0",value = "type")int type){
        PageHelper.startPage(pageNum, 20);
        List<Invitation> vo = invitationService.getAll(type);
        PageInfo<Invitation> page = new PageInfo<>(vo);

        List<InvitationVo> voList = invitationService.toVoList(page.getList());
        PageInfo<InvitationVo> pageInfo = new PageInfo<>(voList);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPageNum(page.getPageNum());

        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("user", user);
        model.addAttribute("vos", vo);
        return "home";
    }
}
