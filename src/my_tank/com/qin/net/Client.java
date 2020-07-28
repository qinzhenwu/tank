package my_tank.com.qin.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

	public static final Client INSTANCE = new Client();

	private Channel channel = null;

	private Client() {
	}

	public void connect() {
		EventLoopGroup group = new NioEventLoopGroup(1);
		Bootstrap b = new Bootstrap();
		try {
			ChannelFuture f = b.group(group).channel(NioSocketChannel.class).handler(new ClientHandlerInitializer())
					.connect("http://localhost", 8888);

			f.addListener(new ChannelFutureListener() {

				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (future.isSuccess()) {
						System.out.println("链接成功");
						channel = future.channel();
					} else {
						System.out.println("链接失败");
					}

				}
			});

			f.sync();

			f.channel().closeFuture().sync();
			System.out.println("链接断开！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

	}

	public void send(Msg msg) {
		System.out.println("send Msg " + msg);
		channel.writeAndFlush(msg);
	}

	public void closeConnect() {

	}

}

class ClientHandlerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub

	}

}
