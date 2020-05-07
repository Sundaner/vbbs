package com.htw.vbbs.service;

import com.htw.vbbs.dao.MessageMapper;
import com.htw.vbbs.domain.Message;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserService userService;

    public int send(int userId, int id, String content) {
        Message message = new Message();
        message.setContent(content);
        message.setSenderId(userId);
        message.setReceiverId(id);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        message.setCreateTime(timeStamp);
        message.setStatus(0);
        return messageMapper.sendMessage(message);
    }

    public int updateStatus(int id){
        return messageMapper.updateStatus(id);
    }

    public List<Message> getMessageList(int userId) {
        return messageMapper.getMessageList(userId);
    }

    public MessageVo toMessageVo(Message message){
        MessageVo vo = new MessageVo();
        User user = userService.getSimpInfoById(message.getSenderId());
        vo.setMessageId(message.getMessageId());
        vo.setSenderId(message.getSenderId());
        vo.setUserName(user.getNickname());
        vo.setUserImg(user.getPortrait());
        vo.setCreateTime(message.getCreateTime());
        vo.setStatus(message.getStatus());
        vo.setContent(message.getContent());
        return vo;
    }

    public int deleteMessage(int id) {
        return messageMapper.deleteMessage(id);
    }
}
