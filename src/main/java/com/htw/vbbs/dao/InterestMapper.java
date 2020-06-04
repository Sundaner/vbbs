package com.htw.vbbs.dao;

import com.htw.vbbs.domain.Interest;
import com.htw.vbbs.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterestMapper {
    List<Interest> getMyInterest(int userId);

    int interest(Interest interest);

    int cancelInterest(Interest interest);

    Interest hasInterest(Interest interest);

    List<Interest> getInterestList(int master);
}
