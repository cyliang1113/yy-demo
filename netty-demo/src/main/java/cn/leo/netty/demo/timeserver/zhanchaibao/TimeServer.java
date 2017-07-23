package cn.leo.netty.demo.timeserver.zhanchaibao;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {

	public static void main(String[] args) {
		int port = 9999;

		EventLoopGroup bossGroup = null;
		EventLoopGroup workerGroup = null;
		try {
			bossGroup = new NioEventLoopGroup();
			workerGroup = new NioEventLoopGroup();

			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.option(ChannelOption.SO_BACKLOG, 1024);
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					ch.pipeline().addLast(new TimeServerHandler());
				}
			});

			ChannelFuture f = b.bind(port).sync();

			f.channel().closeFuture().sync();
		} catch (Exception e) {

		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}
}
