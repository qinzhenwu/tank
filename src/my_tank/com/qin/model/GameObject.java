package my_tank.com.qin.model;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * 游戏对象，所有具体的游戏对象都需要集成他<br>
 * 抽离出公共的属性和方法
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
	 * 绘画方法
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
