package com.github.spark.netty.sample.timeserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TimeServerChannelHandler extends ChannelInboundHandlerAdapter {

    private static SimpleDateFormat format = new SimpleDateFormat();

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        byte [] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);

        String body = new String(req,"UTF-8");
        log.info("Receive msg as :{}",body);

        String result = null;
        if (body.equals("GETTIME")) {
            result = format.format(new Date());
        } else {
            result = "BAD REQUEST";
        }

        ByteBuf resp = Unpooled.copiedBuffer(result.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channel active, name = {}.",ctx.name());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("channel readCompletely, name = {}.",ctx.name());
    }
}
