package com.gabe.mychat.netty;


import com.corundumstudio.socketio.Configuration;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author wsw
 * @Package com.gabe.mychat.netty
 * @Description:netty WebServer
 * @date 2019年6月25日 09:25:27
 */
@Service
public class WebServer implements InitializingBean{
    @Autowired
    private listener listeners;


    public void startServer(){
        Configuration config = new Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(8086);

        config.setMaxFramePayloadLength(1024*1024);
        //设置最大HTTP内容长度限制
        config.setMaxHttpContentLength(1024 * 1024);
        SocketIOServer server = new SocketIOServer(config);
        // 添加客户端连接监听器
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println(client.getRemoteAddress() + " web客户端接入");
            }
        });
        server.addListeners(listeners);
        server.start();

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.startServer();
        System.out.println("start websocket");
    }
}
