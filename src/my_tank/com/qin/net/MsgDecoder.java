package my_tank.com.qin.net;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 消息解码
 * 
 * @author Administrator
 *
 */
public class MsgDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// 解码操作,消息头保护包含消息类型和消息长度，都是int类型，占用八个字节
		if (in.readableBytes() < 8) {
			return;
		}
		in.markReaderIndex();

		MsgType msgType = MsgType.values()[in.readInt()];// 读取消息类型
		// 消息的长度
		int length = in.readInt();
		// 如果读到的消息的长度，小于实际的长度，重新读
		if (in.readableBytes() < length) {
			// 重置位置
			in.resetReaderIndex();
			return;
		}
		//设置数组
		byte [] bytes=new byte[length];
		//将消息读到bytes
		in.readBytes(bytes);

		Msg msg = null;
		msg = (Msg)Class.forName("com.mashibing.tank.net." + msgType.toString() + "Msg").getDeclaredConstructor().newInstance();

		msg.parse(bytes);
		out.add(msg);
		
		
	}

}
