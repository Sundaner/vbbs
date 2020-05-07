package com.htw.vbbs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htw.vbbs.Result.Result;
import com.htw.vbbs.domain.Comment;
import com.htw.vbbs.domain.Invitation;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.service.*;
import com.htw.vbbs.vo.CommentView;
import com.htw.vbbs.vo.ToInvitationVo;
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
    @Autowired
    private ZanService zanService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private InterestService interestService;

    @RequestMapping("/create")
    public String createArticle (Model model, User user) {

        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        return "create";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public Result<Integer> doSubmit(ToInvitationVo invitation, User user){
        int vid = invitationService.insertVo(invitation, user.getUserId());
        return Result.success(vid);
    }

//    @RequestMapping("/info")
//    public String getArticle(Model model, @RequestParam int id){
//        Invitation invitation = invitationService.findInviComments(id);
//        model.addAttribute("article", invitation);
//        return "article";
//    }

    @RequestMapping("/info")
    public String getArticle(Model model, @RequestParam int id, User user,
                             @RequestParam(defaultValue = "1",value = "pageNum")int pageNum){
        Invitation invitation = invitationService.getById(id);
        PageHelper.startPage(pageNum, 20);
        List<Comment> commentList = commentService.getComments(id);
        PageInfo<Comment> page = new PageInfo<>(commentList);

        List<CommentView> commentViews = commentService.toCommentView(page.getList());
        PageInfo<CommentView> pageInfo = new PageInfo<>(commentViews);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPageNum(page.getPageNum());

        int zan = zanService.hasZan(user.getUserId(), id);
        int store = storeService.hasStore(user.getUserId(), id);
        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("zan", zan);
        model.addAttribute("store",store);
        model.addAttribute("user", user);
        model.addAttribute("article", invitation);
        model.addAttribute("pageInfo", pageInfo);
        return "article";
    }

    @RequestMapping("/edit")
    public String toeditArticle(Model model, @RequestParam int id, User user){
        Invitation invitation = invitationService.getById(id);
        List<User> inter = interestService.getMyInterest(user.getUserId());
        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("article", invitation);
        return "edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public  Result<Boolean> updateArticle(Model model, @RequestParam int id, User user,ToInvitationVo invitation){
        int  re = invitationService.update(id, invitation);
        return Result.success(true);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public  Result<Boolean> updateArticle(int id){
        int re = invitationService.delete(id);
        return Result.success(true);
    }

    @RequestMapping("/zan")
    @ResponseBody
    public Result<Boolean> doZan(int id, int flag, User user){
        if(flag == 0){
            invitationService.zan(user.getUserId(), id);
        }else {
            invitationService.cancelZan(user.getUserId(), id);
        }
        return Result.success(true);
    }

    @RequestMapping("/store")
    @ResponseBody
    public Result<Boolean> doStore(int id,int flag, User user){
        if(flag == 0){
            invitationService.store(user.getUserId(), id);
        }else {
            invitationService.cancelStore(user.getUserId(), id);
        }
        return Result.success(true);
    }

}