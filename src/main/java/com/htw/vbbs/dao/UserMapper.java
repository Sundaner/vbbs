package com.htw.vbbs.dao;

import com.htw.vbbs.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    User getById(int userId	);

    User getByPhone(long phone);

    int insert(User user);

    User getSimpInfoById(int id);
}
