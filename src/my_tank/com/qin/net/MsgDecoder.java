package my_tank.com.qin.net;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * ��Ϣ����
 * 
 * @author Administrator
 *
 */
public class MsgDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// �������,��Ϣͷ����������Ϣ���ͺ���Ϣ���ȣ�����int���ͣ�ռ�ð˸��ֽ�
		if (in.readableBytes() < 8) {
			return;
		}
		in.markReaderIndex();

		MsgType msgType = MsgType.values()[in.readInt()];// ��ȡ��Ϣ����
		// ��Ϣ�ĳ���
		int length = in.readInt();
		// �����������Ϣ�ĳ��ȣ�С��ʵ�ʵĳ��ȣ����¶�
		if (in.readableBytes() < length) {
			// ����λ��
			in.resetReaderIndex();
			return;
		}
		//��������
		byte [] bytes=new byte[length];
		//����Ϣ����bytes
		in.readBytes(bytes);

		Msg msg = null;
		msg = (Msg)Class.forName("com.mashibing.tank.net." + msgType.toString() + "Msg").getDeclaredConstructor().newInstance();

		msg.parse(bytes);
		out.add(msg);
		
		
	}

}
