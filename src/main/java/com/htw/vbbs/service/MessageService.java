package com.htw.vbbs.service;

import com.htw.vbbs.dao.MessageMapper;
import com.htw.vbbs.domain.Message;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.vo.MessageVo;
import com.htw.vbbs.websocket.SocketServer;
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

    public int send(int userId, String name, int id, String content) {
        Message message = new Message();
        message.setContent(content);
        message.setSenderId(userId);
        message.setReceiverId(id);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        message.setCreateTime(timeStamp);
        message.setStatus(0);
        if(SocketServer.hasOnline(id)){
            String notify = "用户" + name + "给你发送一一条私信，看去看看吧！";
            SocketServer.sendMessage(notify, id);
        }
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
