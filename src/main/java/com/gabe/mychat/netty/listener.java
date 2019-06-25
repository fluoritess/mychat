package com.gabe.mychat.netty;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.gabe.mychat.mapper.messageUtilMapper;
import com.gabe.mychat.mapper.normalUserUtilMapper;
import com.gabe.mychat.pojo.message;
import com.gabe.mychat.pojo.normalUser;
import com.gabe.mychat.pojo.user;
import com.gabe.mychat.service.MsgService;
import com.gabe.mychat.service.UserService;
import com.gabe.mychat.util.Constant;
import com.gabe.mychat.util.ControlStatus;
import com.gabe.mychat.util.FormatData;
import com.gabe.mychat.util.UserUtil;
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
    @Autowired
    UserService userService;
    @Autowired
    normalUserUtilMapper normalUserUtilMapper;
    @Autowired
    MsgService msgService;

    private static ChannelHandlerContext ctx = null;//这个暂时没用

    //维护每个客户端的SocketIOClient
    public static Map<String, SocketIOClient> clients = new ConcurrentHashMap<>();
    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.err.println("建立连接");
    }

    @OnEvent("token")
    public void onToken(SocketIOClient client,String userid) {
        System.out.println(userid);
        System.out.println("onToken"+client.getSessionId().toString());
        if (null ==  clients.get(client.getSessionId().toString()) ) {
            clients.put(userid, client);
        }
        System.err.println("get token Message is " + client.getSessionId());
    }

    @OnEvent("send")
    public void updateControlStatus(SocketIOClient client, Map message) throws UnsupportedEncodingException {
        //获取信息
        String UserID=(String) message.get("clientuserid");
        String friendID=(String)message.get("userid");
        //messages为消息内容
        String messages=(String)message.get("message");
        Date date=(Date)message.get("date");
        SocketIOClient client1=clients.get(UserID);
        //判断是否是同一用户
        if(client1.equals(client)){
            user user=userService.selectUserById(UserID);
            normalUser normalUser= normalUserUtilMapper.selectUserById(user.getUserId());
            Map map1=new HashMap();
            map1= UserUtil.completeUser(user,normalUser);
            map1.remove("password");
            map1.put("message",messages);
            map1.put("date",date);
            Constant.aotoControlMap.put("Active",UserID);
            //把消息存入数据库
            String message_id=date.getTime()+UserID;
            com.gabe.mychat.pojo.message message1 = new message(message_id, messages, 1, friendID, UserID, 0, date);
            msgService.addFriendMsg(message1);
            client1.sendEvent("receive",map1);
   /*         ctx.writeAndFlush("hello");*/
        }

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
