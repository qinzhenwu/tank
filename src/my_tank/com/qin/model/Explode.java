package my_tank.com.qin.model;

import java.awt.Graphics;

import com.sun.deploy.uitoolkit.impl.fx.ui.resources.ResourceManager;

import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.manager.SourceManager;
import my_tank.com.qin.product.BaseExplode;

/**
 * 爆炸效果
 * 
 * @author qinzhenwu
 *
 */
public class Explode extends BaseExplode {
	private int x;// 坐标

	private int y;// 坐标

	private int step = 0;// 当前效果刷了多少次，一共16张图片，超过16，重置为0

	private TankFrame tf;// 持有frame对象

	public static int WIDTH = SourceManager.explode[0].getWidth();
	public static int HEIGHT = SourceManager.explode[0].getHeight();

	private boolean isOver = true;// 爆炸结束

	public Explode(int x, int y, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
		this.isOver = false;
	}

	@Override
	public void paint(Graphics g) {
		if (step >= 0 && step < 16) {// 开始展示爆炸效果
			g.drawImage(SourceManager.explode[step++], x, y, null);
		} else if (step == 16) {// 刷了16次后，停止爆炸
			step = 0;
			isOver = true;
		}
		if (isOver) {
			tf.explodes.remove(this);
		}

	}

}
