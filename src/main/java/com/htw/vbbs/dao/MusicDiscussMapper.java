package com.htw.vbbs.dao;

import com.htw.vbbs.domain.MusicDiscuss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MusicDiscussMapper {
    List<MusicDiscuss> getMusicDisList(long id);

    MusicDiscuss getById(int replyId);

    int inerstNew(MusicDiscuss comment);
}
