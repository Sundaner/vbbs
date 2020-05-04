package com.htw.vbbs.service;

import com.htw.vbbs.dao.CommentMapper;
import com.htw.vbbs.domain.Comment;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.vo.CommentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    public Comment queryCommAndReply(int id){
        return commentMapper.queryCommAndReply(id);
    }

    public Comment getById(int id){
        return commentMapper.getById(id);
    }

    public Comment hasComment(int id){
        return commentMapper.hasComment(id);
    }

    public CommentView insert(int inv_id, int com_id, int userId, String content) {
        User user = userService.getSimpInfoById(userId);
        Comment comment = new Comment();
        comment.setInvitationId(inv_id);
        comment.setUserId(userId);
        comment.setContent(content);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        comment.setCreateTime(timeStamp);
        comment.setReplyId(com_id);
        int re = commentMapper.inerstNew(comment);

        CommentView commentVo = new CommentView();
        commentVo.setUserId(comment.getUserId());
        commentVo.setUserImg(user.getPortrait());
        commentVo.setUserName(user.getNickname());
        commentVo.setCommentId(comment.getCommentId());
        commentVo.setCreateTime(comment.getCreateTime());
        commentVo.setContent(comment.getContent());
        CommentView parent = null;
        if(com_id != 0){
            Comment par_comm = commentService.getById(com_id);
            User par_user = userService.getSimpInfoById(par_comm.getUserId());
            parent = new CommentView();
            parent.setCommentId(com_id);
            parent.setUserId(par_user.getUserId());
            parent.setUserName(par_user.getNickname());
            parent.setUserImg(par_user.getPortrait());
            parent.setCreateTime(par_comm.getCreateTime());
            parent.setContent(par_comm.getContent());
        }
        commentVo.setParent(parent);
        return commentVo;
    }

    public List<Comment> getComments(int invitationId){
        List<Comment> comments = commentMapper.getComments(invitationId);
        return comments;
    }

    public List<CommentView> toCommentView(List<Comment> comments){
        List<CommentView> commentViews = new ArrayList<>();
        comments.forEach(item -> {
            CommentView cv = new CommentView();
            User user = userService.getSimpInfoById(item.getUserId());
            cv.setUserId(item.getUserId());
            cv.setUserImg(user.getPortrait());
            cv.setUserName(user.getNickname());
            cv.setCommentId(item.getCommentId());
            cv.setCreateTime(item.getCreateTime());
            cv.setContent(item.getContent());
            if(item.getReplyId() != 0){
                CommentView par = new CommentView();
                Comment comment = commentService.getById(item.getReplyId());
                User puer = userService.getSimpInfoById(comment.getUserId());
                par.setUserId(comment.getUserId());
                par.setUserImg(puer.getPortrait());
                par.setUserName(puer.getNickname());
                par.setCommentId(comment.getCommentId());
                par.setCreateTime(comment.getCreateTime());
                par.setContent(comment.getContent());
                cv.setParent(par);
            }
            commentViews.add(cv);
        });
        return commentViews;
    }
}
