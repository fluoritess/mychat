/*
package com.gabe.mychat.netty;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public class NettySend {
    private static DatagramPacket packet = null;
    private static ChannelHandlerContext ctx = null;
    public static void sendMesage(String json ) throws UnsupportedEncodingException {
        String json1="A9a33#S02D#0123456789#"+json;
        System.out.println(json1);
        byte[] bytes = json1.getBytes("UTF-8");
        if (DiscardServer.clients.get("0123456789") != null) {
            for (Map.Entry<String, Object> entry : DiscardServer.clients.get("0123456789").entrySet()) {
                if (entry.getKey().equals("packet")) {
                    packet = (DatagramPacket) entry.getValue();
                }
                if (entry.getKey().equals("ctx")) {
                    ctx = (ChannelHandlerContext) entry.getValue();
                }
            }
        }
        if (ctx != null && packet != null) {
            System.out.println("##############");
            DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), packet.sender());
            ctx.writeAndFlush(data);//向客户端发送消息
        }

    }

}*/
