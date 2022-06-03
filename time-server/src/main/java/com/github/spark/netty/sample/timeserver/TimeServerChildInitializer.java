package com.github.spark.netty.sample.timeserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeServerChildInitializer extends ChannelInitializer {


    @Override
    protected void initChannel(Channel channel) throws Exception {

        channel.pipeline().addLast(new StringDecoder())
                .addLast(new LineBasedFrameDecoder(1024))
                .addLast(new TimeServerChannelHandler());
    }
}
