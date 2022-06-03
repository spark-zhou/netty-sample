package com.github.spark.netty.sample.timeserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class TimeServerChildInitializer extends ChannelInitializer {


    @Override
    protected void initChannel(Channel channel) throws Exception {

        channel.pipeline().addLast(new TimeServerChannelHandler());
    }
}
