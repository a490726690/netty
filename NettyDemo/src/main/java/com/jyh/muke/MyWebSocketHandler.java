package com.jyh.muke;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 接收/处理/响应客户端websocket请求的核心业务处理类
 * Created by jyh-mac on 2018/10/9.
 */
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    public static void main(String[] args) {

    }

}