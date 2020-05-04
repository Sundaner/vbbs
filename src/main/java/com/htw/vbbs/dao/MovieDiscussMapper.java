package com.htw.vbbs.dao;

import com.htw.vbbs.domain.MovieDiscuss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieDiscussMapper {
    List<MovieDiscuss> getDisList(int id);

    MovieDiscuss getById(int id);

    int inerstNew(MovieDiscuss comment);
}
