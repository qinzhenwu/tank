package my_tank.com.qin.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.model.Tank;

/**
 * tank�������Ϣ
 * 
 * @author Administrator
 *
 */
public class TankJoinMsg extends Msg {

	public int x, y;

	public Dir dir;

	public boolean moving;

	public TankGroup group;

	public UUID id;

	public TankJoinMsg(Tank tank) {

		this.x = tank.getX();
		this.y = tank.getY();
		this.dir = tank.getDir();
		this.group = tank.getGroup();
		this.id = tank.getId();
		this.moving = tank.isMove();

	}

	public TankJoinMsg() {
	}

	/**
	 * ���������߼�
	 */
	@Override
	public void handler() {

		if(this.id.equals(TankFrame.INSTANCE.getTank().getId())
				|| TankFrame.INSTANCE.findTankByUUID(this.id)!=null) {
			return ;
		}
		
		Tank t=new Tank(this);
		TankFrame.INSTANCE.addTank(t);
		Client.INSTANCE.send(new TankJoinMsg(TankFrame.INSTANCE.getTank()));
	}

	/**
	 * ���澯������Ϣת��byte����
	 */
	@Override
	public byte[] toBytes() {
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null;
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);

			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(dir.ordinal());
			dos.writeBoolean(moving);
			dos.writeInt(group.ordinal());
			dos.writeLong(id.getMostSignificantBits());
			dos.writeLong(id.getLeastSignificantBits());
			dos.flush();
			bytes = baos.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return bytes;
	}

	@Override
	public void parse(byte[] bytes) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
		try {
			// TODO:�ȶ�TYPE��Ϣ������TYPE��Ϣ����ͬ����Ϣ
			// �Թ���Ϣ����
			// dis.readInt();

			this.x = dis.readInt();
			this.y = dis.readInt();
			this.dir = Dir.values()[dis.readInt()];
			this.moving = dis.readBoolean();
			this.group = TankGroup.values()[dis.readInt()];
			this.id = new UUID(dis.readLong(), dis.readLong());
			// this.name = dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public MsgType getMsgType() {

		return MsgType.TankJoin;
	}

}
