package com.htw.vbbs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htw.vbbs.result.Result;
import com.htw.vbbs.domain.Message;
import com.htw.vbbs.domain.User;

import com.htw.vbbs.service.InterestService;
import com.htw.vbbs.service.MessageService;
import com.htw.vbbs.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private InterestService interestService;

    @RequestMapping("/send")
    @ResponseBody
    public Result<Boolean> sendMessage(User user, @RequestParam int id,
                                       @RequestParam String content){
        int re = messageService.send(user.getUserId(), id, content);
        return Result.success(true);
    }

    @RequestMapping("/notice")
    public String notice(User user, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum")int pageNum){
        PageHelper.startPage(pageNum, 20);
        List<Message> messageLsit = messageService.getMessageList(user.getUserId());
        PageInfo<Message> page = new PageInfo<>(messageLsit);

        List<MessageVo> messageVos = new ArrayList<>();
        page.getList().forEach(item->{
            MessageVo vo = messageService.toMessageVo(item);
            messageVos.add(vo);
        });
        PageInfo<MessageVo> pageInfo = new  PageInfo<>(messageVos);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPageNum(page.getPageNum());

        List<User> inter = interestService.getMyInterest(user.getUserId());

        model.addAttribute("inter", inter);
        model.addAttribute("user", user);
        model.addAttribute("pageInfo", pageInfo);

        return "notice";
    }

    @RequestMapping("/read")
    @ResponseBody
    public Result<Boolean> hasRead(User user, @RequestParam int id){
        int re = messageService.updateStatus(id);
        return Result.success(true);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteMessage(User user, @RequestParam int id){
        int re = messageService.deleteMessage(id);
        return Result.success(true);
    }
}
