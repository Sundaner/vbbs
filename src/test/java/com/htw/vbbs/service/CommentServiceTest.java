package com.htw.vbbs.service;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.htw.vbbs.dao.CommentMapper;
import com.htw.vbbs.domain.Comment;
import com.htw.vbbs.vo.CommentView;
import com.htw.vbbs.vo.ToCommentVo;
import javafx.scene.control.ContextMenu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;


    @Test
    public void testReply(){
        Comment comment = commentService.queryCommAndReply(210);
        System.out.println(comment);
    }

    @Test
    public void testGetId(){
        Comment comment = commentService.getById(211);
        System.out.println(comment);
    }

    @Test
    public void testHasComment(){
        Comment o = commentService.hasComment(219);
        System.out.println(o);
    }

    @Test
    public void insert(){
        Comment comment = new Comment();
        comment.setInvitationId(210);
        comment.setUserId(101);
        comment.setContent("hello");
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        comment.setCreateTime(timeStamp);
        comment.setReplyId(0);
        commentMapper.inerstNew(comment);
        System.out.println(comment.getCommentId());
    }

    @Test
    public void getCommetList(){
        List<Comment> c = commentService.getComments(210);
        System.out.println(c);
    }

}
