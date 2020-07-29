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

public class TankStopMsg extends Msg {
	
	private int x,y;
	 
	
	private UUID id;
	public TankStopMsg(Tank t) {
		this.id = t.getId();
		this.x = t.getX();
		this.y = t.getY();
	}
	
	public TankStopMsg(UUID id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public TankStopMsg() {}

	@Override
	public void handler() {
		if (this.id.equals(TankFrame.INSTANCE.getTank().getId()))
			return;

		Tank t = TankFrame.INSTANCE.findTankByUUID(this.id);

		if (t != null) {
			t.setMove(false);
			t.setX(this.x);
			t.setY(this.y);
		}

	}

	@Override
	public byte[] toBytes() {
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null; 
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);
			dos.writeLong(id.getMostSignificantBits());
			dos.writeLong(id.getLeastSignificantBits());
			dos.writeInt(x);
			dos.writeInt(y);
			dos.flush();
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(dos != null) {
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
		// TODO Auto-generated method stub
		return MsgType.TankStop;
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

 

	
	
}
