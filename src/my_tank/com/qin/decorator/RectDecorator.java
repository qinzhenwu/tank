package my_tank.com.qin.decorator;

import java.awt.Color;
import java.awt.Graphics;

import my_tank.com.qin.model.GameObject;

/**
 * 外壳方形装饰器
 * 
 * @author qinzhenwu
 *
 */
public class RectDecorator extends GameObjectDecorator {

	public RectDecorator(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawRect(gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight());
		g.setColor(c);
	}

}
