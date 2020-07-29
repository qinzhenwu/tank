package my_tank.com.qin.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {
	public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	public void serverStart() {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(2);
		
		try {
			ServerBootstrap b = new ServerBootstrap();
			ChannelFuture f = b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pl = ch.pipeline();
						pl.addLast(new MsgEncoder());
						pl.addLast(new MsgDecoder());
						pl.addLast(new ServerChildHandler());
					}
				})
				.bind(8888)
				.sync();
			
			ServerFrame.INSTANCE.updateServerMsg("server started!");
			
			f.channel().closeFuture().sync(); //close()->ChannelFuture
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	

}

class ServerChildHandler extends ChannelInboundHandlerAdapter { //SimpleChannleInboundHandler Codec
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Server.clients.add(ctx.channel());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//��ȡ���пͻ��˷��͵���Ϣ��Ȼ��ת�������еĿͻ���
		ServerFrame.INSTANCE.updateClientMsg(msg.toString());
		Server.clients.writeAndFlush(msg);
		
		 
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		//ɾ���쳣��channel
		Server.clients.remove(ctx.channel());
		ctx.close();
	}
	
	
}




