package my_tank.com.qin.net;

/**
 * 抽象的消息类
 * 
 * @author Administrator
 *
 */
public abstract class Msg {
	
	public abstract void handler();
	
	public abstract byte [] toBytes();
	
	public abstract void parse(byte [] bytes);

	public abstract MsgType getMsgType();
}
