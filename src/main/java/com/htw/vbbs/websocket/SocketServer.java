package com.htw.vbbs.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value = "/socketServer/{userId}")
@Component
public class SocketServer {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    /**
     *
     * 用线程安全的CopyOnWriteArraySet来存放客户端连接的信息
     */
    private static Map<Integer, Session> socketServers = new ConcurrentHashMap<>();

    /**
     *
     * websocket封装的session,信息推送，就是通过它来信息推送
     */
    private Session session;

    /**
     *
     * 用户连接时触发，我们将其添加到
     * 保存客户端连接信息的socketServers中
     *
     * @param session
     * @param userId
     */
    @OnOpen
    public void open(Session session, @PathParam(value="userId")int userId){
        this.session = session;
        socketServers.put(userId, session);
        logger.info("客户端:【{}】连接成功",userId);
    }

    /**
     *
     * 收到客户端发送信息时触发
     * 我们将其推送给客户端(niezhiliang9595)
     * 其实也就是服务端本身，为了达到前端聊天效果才这么做的
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message){

        logger.info("客户端:【{}】发送信息:{}", message);
    }

    /**
     *
     * 连接关闭触发，通过sessionId来移除
     * socketServers中客户端连接信息
     */
    @OnClose
    public void onClose(){
        socketServers.forEach((k, v) ->{
            if (v.getId().equals(session.getId())) {
                logger.info("客户端:【{}】断开连接", k);
                socketServers.remove(k);

            }
        });
    }

    /**
     *
     * 发生错误时触发
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        socketServers.forEach((k, v) ->{
            if (v.getId().equals(session.getId())) {
                socketServers.remove(k);
                logger.error("客户端:【{}】发生异常", k);
                error.printStackTrace();
            }
        });
    }

    /**
     *
     * 信息发送的方法，通过客户端的userName
     * 拿到其对应的session，调用信息推送的方法
     * @param message
     * @param userId
     */
    public synchronized static void sendMessage(String message, int userId){
        try {
            socketServers.get(userId).getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 多个人发送给指定的几个用户
     * @param message
     * @param users
     */
    public synchronized static void SendMany(String message,int [] users) {
        for (int userId : users) {
            sendMessage(message,userId);
        }
    }

    public synchronized static boolean hasOnline(int userId) {
        if(socketServers.containsKey(userId)){
            return true;
        }
        return false;
    }
}
