package my_tank.com.qin.model;

import java.awt.Graphics;

/**
 * ��Ϸ�������о������Ϸ������Ҫ������<br>
 * ��������������Ժͷ���
 * 
 * @author qinzhenwu
 *
 */
public abstract class GameObject {

	public int x;

	public int y;

	/**
	 * �滭����
	 * 
	 * @param g
	 */
	public abstract void paint(Graphics g);
	
	
	
	
}
