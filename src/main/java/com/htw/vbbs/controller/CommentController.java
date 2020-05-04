package com.htw.vbbs.controller;

import com.htw.vbbs.Result.Result;
import com.htw.vbbs.service.CommentService;
import com.htw.vbbs.vo.CommentView;
import com.htw.vbbs.vo.ToCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/submit")
    @ResponseBody
    public Result<CommentView> doSubmit(ToCommentVo toCommentVo){
        int inv_id = toCommentVo.getInvitationId();
        int com_id = toCommentVo.getCommentId();
        String content = toCommentVo.getContent();
        int userId = 101;
        CommentView vo = commentService.insert(inv_id, com_id, userId, content);
        return Result.success(vo);
    }
}
