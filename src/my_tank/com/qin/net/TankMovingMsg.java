package my_tank.com.qin.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.model.Tank;

/**
 * tank�ƶ�����Ϣ
 * 
 * @author qinzh
 *
 */
public class TankMovingMsg extends Msg {

	private int x, y;// ����
	private UUID id;// Ψһ��ʶ
	private Dir dir;// ����

	public TankMovingMsg() {

	}

	public TankMovingMsg(int x, int y, UUID id, Dir dir) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.dir = dir;
	}

	public TankMovingMsg(Tank tank) {
		this.x = tank.getX();
		this.y = tank.getY();
		this.id = tank.getId();
		this.dir = tank.getDir();
	}

	@Override
	public void handler() {
		// ��������tank����Ϣ�����յ���Ϣת��tank���󣬻�ȡ�����ֵ���������»���
		// ����ǵ�ǰtank��������Ϣ��������
		if (this.id.equals(TankFrame.INSTANCE.getTank().getId())) {
			return;
		}
		Tank tank = TankFrame.INSTANCE.findTankByUUID(this.id);
		if (tank != null) {
			tank.setDir(this.dir);
			tank.setX(this.x);
			tank.setY(this.y);
			tank.setMove(true);
		}

	}

	@Override
	public byte[] toBytes() {

		// ����Ϣ����ת��byte
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null;
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);
			dos.writeLong(id.getMostSignificantBits());//UUIDռ16���ֽڣ��ֳ�����long
			dos.writeLong(id.getLeastSignificantBits());
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(dir.ordinal());
			dos.flush();
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (dos != null) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return bytes;
	}

	@Override
	public void parse(byte[] bytes) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
		try {
			
			this.id = new UUID(dis.readLong(), dis.readLong());
			this.x = dis.readInt();
			this.y = dis.readInt();
			this.dir = Dir.values()[dis.readInt()];
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
		return MsgType.TankMoving;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

}
