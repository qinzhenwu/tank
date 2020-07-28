package my_tank.com.qin.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<Msg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf out) throws Exception {
		//д����Ϣ���ͣ�ö��ֵ���±�
		out.writeInt(msg.getMsgType().ordinal());
		byte [] bytes=msg.toBytes();
		//����ĳ���
		out.writeInt(bytes.length);
		//д������
		out.writeBytes(bytes);
	}

}
