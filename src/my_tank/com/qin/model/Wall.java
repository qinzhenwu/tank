package my_tank.com.qin.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 墙体
 * 
 * @author qinzhenwu
 *
 */
public class Wall extends GameObject {

	private int width = 0, height = 0;
	
	private Rectangle rectangle=new Rectangle();

	/**
	 * 构造方法，创建墙
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public Wall(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		rectangle.setBounds(x, y, w, h);
		GameModel.getInstance().add(this);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		
		g.fillRect(x, y, width, height);
		//g.drawRect(x, y, width, height);
		g.setColor(c);
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	

}
