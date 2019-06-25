package com.gabe.mychat.netty;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.gabe.mychat.util.Constant;
import com.gabe.mychat.util.ControlStatus;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wsw
 * @Package com.gabe.mychat.netty
 * @Description:netty listener
 * @date 2019年6月25日 09:25:12
 */
@Component
public class listener implements Constant {
    //维护每个客户端的SocketIOClient
    public static Map<String, SocketIOClient> clients = new ConcurrentHashMap<>();
    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.err.println("建立连接");
    }

    @OnEvent("token")
    public void onToken(SocketIOClient client,String s) {
        System.out.println(s);
        System.out.println("onToken"+client.getSessionId().toString());

        if (null ==  clients.get(client.getSessionId().toString()) ) {

            clients.put(client.getSessionId().toString(), client);
        }
        System.err.println("get token Message is " + client.getSessionId());
    }

    @OnEvent("updateControlStatus")
    public void updateControlStatus(SocketIOClient client, ControlStatus controlStatus) throws UnsupportedEncodingException {
        DatagramPacket packet=null;
        ChannelHandlerContext ctx=null;
        System.out.println(controlStatus.getBulbactive1());
        System.out.println(controlStatus.getUpdate().getBulbactive1());
        if(ctx!=null&&packet!=null){
            ctx.writeAndFlush("xxx");//向客户端发送消息
        }
    }
    @OnEvent("getNowCollectValue")
    public void getNowCollectValue(SocketIOClient client, String message)  {
        System.out.println(message);

    }
    @OnEvent("updateControlTypeStatus")
    public void updateControlStatus(SocketIOClient client, String message)  {
        Constant.aotoControlMap.put("ControlStatus",message);

    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {

        System.err.println("关闭连接");
    }

}
