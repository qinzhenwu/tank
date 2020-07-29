package my_tank.com.qin.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import my_tank.com.qin.frame.TankFrame;

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
					.connect("localhost", 8888);

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
		// 初始化通道后在pipeline中添加encoder和decoder
		ch.pipeline().addLast(new MsgEncoder()).addLast(new MsgDecoder()).addLast(new ClientHandler());

	}

}

/**
 * 简单消息处理
 * 
 * @author qinzh
 *
 */
class ClientHandler extends SimpleChannelInboundHandler<Msg> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
		// channelRead0 方法时netty4里的，5里改了方法，但是5已经废除了
		System.out.println(msg);
		// 消息处理
		msg.handler();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// channel初始后发送tank加入的消息
		ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getTank()));
	}

	
}
