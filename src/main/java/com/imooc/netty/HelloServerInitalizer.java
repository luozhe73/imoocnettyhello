package com.imooc.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HelloServerInitalizer extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel channel) throws Exception {

        //通过channel获取对应的管道
        ChannelPipeline channelPipeline = channel.pipeline();

        //通过管道添加handler
        //HttpServerCodec是netty提供的助手类，理解为拦截器
        //当请求到服务器，我们需要解码，相应到客户端编码
        channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());

        //添加自定义助手类，返回hellonetty
        channelPipeline.addLast("customHandler",new CustomHandler());

    }
}
