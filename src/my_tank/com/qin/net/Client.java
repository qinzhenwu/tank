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
						System.out.println("���ӳɹ�");
						channel = future.channel();
					} else {
						System.out.println("����ʧ��");
					}

				}
			});

			f.sync();

			f.channel().closeFuture().sync();
			System.out.println("���ӶϿ���");
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
		// ��ʼ��ͨ������pipeline�����encoder��decoder
		ch.pipeline().addLast(new MsgEncoder()).addLast(new MsgDecoder()).addLast(new ClientHandler());

	}

}

/**
 * ����Ϣ����
 * 
 * @author qinzh
 *
 */
class ClientHandler extends SimpleChannelInboundHandler<Msg> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
		// channelRead0 ����ʱnetty4��ģ�5����˷���������5�Ѿ��ϳ���
		System.out.println(msg);
		// ��Ϣ����
		msg.handler();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// channel��ʼ����tank�������Ϣ
		ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getTank()));
	}

	
}
