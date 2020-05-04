package com.htw.vbbs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htw.vbbs.Result.Result;
import com.htw.vbbs.domain.Comment;
import com.htw.vbbs.domain.Game;
import com.htw.vbbs.domain.Invitation;
import com.htw.vbbs.service.CommentService;
import com.htw.vbbs.service.InvitationService;
import com.htw.vbbs.vo.CommentView;
import com.htw.vbbs.vo.InvitationVo;
import com.htw.vbbs.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/create")
    public String createArticle () {
        return "editor";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public Result<Integer> doSubmit(InvitationVo invitation){
        int vid = invitationService.insertVo(invitation);
        return Result.success(vid);
    }

//    @RequestMapping("/info")
//    public String getArticle(Model model, @RequestParam int id){
//        Invitation invitation = invitationService.findInviComments(id);
//        model.addAttribute("article", invitation);
//        return "article";
//    }

    @RequestMapping("/info")
    public String getArticle(Model model, @RequestParam int id, @RequestParam(defaultValue = "1",value = "pageNum")int pageNum){
        Invitation invitation = invitationService.getById(id);
        PageHelper.startPage(pageNum, 20);
        List<Comment> commentList = commentService.getComments(id);
        PageInfo<Comment> page = new PageInfo<>(commentList);
        List<CommentView> commentViews = commentService.toCommentView(page.getList());
        PageInfo<CommentView> pageInfo = new PageInfo<>(commentViews);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPageNum(page.getPageNum());
        model.addAttribute("article", invitation);
        model.addAttribute("pageInfo", pageInfo);
        return "article";
    }
}