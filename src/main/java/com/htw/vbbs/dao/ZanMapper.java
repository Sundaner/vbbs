package com.htw.vbbs.dao;

import com.htw.vbbs.domain.Zan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZanMapper {
    int insertZan(Zan zan);

    int deleteZan(Zan zan);

    Zan hasZan(Zan Zan);

    int deleteArt(int id);
}
