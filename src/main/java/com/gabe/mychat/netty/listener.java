package com.gabe.mychat.netty;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.gabe.mychat.util.Constant;
import com.gabe.mychat.util.ControlStatus;
import com.gabe.mychat.util.FormatData;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
        String s= FormatData.webtoMysqlFormat(controlStatus);
        Constant.aotoControlMap.put("Active",s);

//        System.out.println(json);
//  String json=FormatData.webtoDianPianJi(s);
//        String a= "###fe0b9090d82c21010001020000ff###fe0b9090d82c21010001030000ff#fe0b9090d82c21010001040000ff###";
//        String json="A9a33#S02D#0123456789#fe0b9090d82c21010001020000ff";
        // 由于数据报的数据是以字符数组传的形式存储的，所以、a传转数据

    }
    @OnEvent("getNowCollectValue")
    public void getNowCollectValue(SocketIOClient client, String message)  {
        System.out.println(message);

    }
    @OnEvent("updateControlTypeStatus")
    public void updateControlStatus(SocketIOClient client, String message)  {
        System.out.println(message);
        Constant.aotoControlMap.put("ControlStatus",message);

    }
//    /**
//     * 新事务
//     * @param client 客户端
//     * @param message 消息
//     */
//    @OnEvent("newAlert")
//    public void onAlert(SocketIOClient client, SocketIOMessage message) {
//        //send to all users
//        Collection<List<SocketIOClient>> clientsList = clients.values();
//        for (List<SocketIOClient> list : clientsList) {
//            for (SocketIOClient socketIOClient : list) {
//                socketIOClient.sendEvent("newAlert", message);
//            }
//        }
//    }

//    /**
//     * 通知所有在线客户端
//     */
//    public void sendAllUser() {
//        Set<Map.Entry<String,List<SocketIOClient>>> entrySet = clients.entrySet();
//        for (Map.Entry<String, List<SocketIOClient>> entry : entrySet) {
//            String key = entry.getKey();
//            List<SocketIOClient> value = entry.getValue();
//            for (SocketIOClient socketIOClient : value) {
//                SocketIOMessage message = new SocketIOMessage();
//                message.setMessage("send All user Msg" + key);
//                socketIOClient.sendEvent("newAlert", message);
//            }
//        }
//    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {

        System.err.println("关闭连接");
    }

}
