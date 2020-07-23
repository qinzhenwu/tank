package my_tank.com.qin.model;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * ��Ϸ�������о������Ϸ������Ҫ������<br>
 * ��������������Ժͷ���
 * 
 * @author qinzhenwu
 *
 */
public abstract class GameObject implements Serializable{

	protected int x;

	protected int y;

	protected int width;

	protected int height;

	/**
	 * �滭����
	 * 
	 * @param g
	 */
	public abstract void paint(Graphics g);

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
