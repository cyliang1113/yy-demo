package cn.leo.netty.demo.timeserver.zhanchaibao;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {
	public void connect(String host, int port) {
		EventLoopGroup group = null;
		try {
			group = new NioEventLoopGroup();

			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.TCP_NODELAY, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new TimeClientHandler());
				}
			});

			ChannelFuture f = b.connect(host, port).sync();

			f.channel().closeFuture().sync();
		} catch (Exception e) {

		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		int port = 9999;
		String host = "127.0.0.1";
		new TimeClient().connect(host, port);
	}
}
