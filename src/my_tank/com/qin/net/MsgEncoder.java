package my_tank.com.qin.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<Msg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf out) throws Exception {
		//写入消息类型，枚举值的下标
		out.writeInt(msg.getMsgType().ordinal());
		byte [] bytes=msg.toBytes();
		//数组的长度
		out.writeInt(bytes.length);
		//写出数组
		out.writeBytes(bytes);
	}

}
