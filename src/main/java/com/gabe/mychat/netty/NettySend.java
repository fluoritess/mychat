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
        if (ctx != null && packet != null) {

            DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), packet.sender());
            ctx.writeAndFlush(data);//向客户端发送消息
        }

    }

}*/
