package com.htw.vbbs.dao;

import com.htw.vbbs.domain.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    int sendMessage(Message message);

    List<Message> getMessageList(int userId);

    int updateStatus(int id);

    int deleteMessage(int id);
}
