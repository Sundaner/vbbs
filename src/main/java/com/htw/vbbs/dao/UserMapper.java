package com.htw.vbbs.dao;

import com.htw.vbbs.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    User getById(int userId	);

    User getByPhone(long phone);

    int insert(User user);

    User getSimpInfoById(int id);

    int updateImg(User user);

    int updateInfo(User user);

    int updatePwd(User user);
}
