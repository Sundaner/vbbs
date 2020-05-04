package com.htw.vbbs.dao;

import com.htw.vbbs.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    Comment queryCommAndReply(int id);

    Comment getById(int id);

    Comment hasComment(int id);

    int inerstNew(Comment comment);

    List<Comment> getComments(int invitationId);
}
