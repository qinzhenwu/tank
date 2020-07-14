package my_tank.com.qin.model;

import java.awt.Graphics;

/**
 * 游戏对象，所有具体的游戏对象都需要集成他<br>
 * 抽离出公共的属性和方法
 * 
 * @author qinzhenwu
 *
 */
public abstract class GameObject {

	public int x;

	public int y;

	/**
	 * 绘画方法
	 * 
	 * @param g
	 */
	public abstract void paint(Graphics g);
	
	
	
	
}
