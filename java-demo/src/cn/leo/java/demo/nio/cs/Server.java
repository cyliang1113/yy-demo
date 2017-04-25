package cn.leo.java.demo.nio.cs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Date;
import java.util.Iterator;

public class Server {
	public static void main(String[] args) {
		InetSocketAddress inetSocketAddress = null;
		ServerSocketChannel serverSocketChannel = null;
		Selector selector = null;
		try {
			inetSocketAddress = new InetSocketAddress("10.112.1.15", 9999);
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(inetSocketAddress);
			serverSocketChannel.configureBlocking(false);

			selector = Selector.open();

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				selector.select();

				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();

					if (key.isAcceptable()) {
						ServerSocketChannel serverSocketChannel2 = (ServerSocketChannel) key.channel();
						SocketChannel socketChannel = serverSocketChannel2.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
						System.out.println("有客户端连接----" + (new Date()).toLocaleString());
					} else if (key.isReadable()) {
						SocketChannel socketChannel = (SocketChannel) key.channel();

						ByteBuffer bb = ByteBuffer.allocate(50);
						socketChannel.read(bb);
						bb.flip();
						CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();

						System.out.println("from client: " + decoder.decode(bb).toString());

						CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();

						socketChannel.write(encoder.encode(CharBuffer.wrap("ok")));
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (selector != null) {
				try {
					selector.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (serverSocketChannel != null) {
				try {
					serverSocketChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
