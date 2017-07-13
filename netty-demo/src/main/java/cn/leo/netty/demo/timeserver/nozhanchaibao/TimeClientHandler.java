package cn.leo.netty.demo.timeserver.nozhanchaibao;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 100; i++) {
			String str = "what time?" + System.getProperty("line.separator");
			ByteBuf buf = Unpooled.buffer(str.length());
			buf.writeBytes(str.getBytes());
			ctx.writeAndFlush(buf);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// ByteBuf buf = (ByteBuf) msg;
		// byte[] req = new byte[buf.readableBytes()];
		// buf.readBytes(req);
		System.out.println((String) msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
