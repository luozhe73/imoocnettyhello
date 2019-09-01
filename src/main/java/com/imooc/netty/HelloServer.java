package com.imooc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {

    public static void main(String[] args) throws Exception {

        //定义一对线程组
        //主线程组，用于接收客户端连接，不做任何处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //从线程组，主线程组会把任务给从线程组做任务
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //netty服务器的创建，serverBootstrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //设置主从线程组
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)//设置nio双向通道
                    .childHandler(new HelloServerInitalizer());//子处理器

            //启动server，设置8088端口号，启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();

            //监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();
        } finally {

            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
