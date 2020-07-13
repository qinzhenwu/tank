package my_tank.com.qin.product;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.manager.SourceManager;
import my_tank.com.qin.strategy.FireStrategy;

/**
 * 抽象的tank对象，具体的tank子类实现，此类中可以定义些公共的属性
 * 
 * @author qinzhenwu
 *
 */
public abstract class BaseTank {
	public int x;// 坐标

	public int y;// 坐标

	public Dir dir = Dir.DOWN;// 方向

	protected int speed = 5;

	protected boolean isMove = false;// 设定弹框是否可移动

	public int WIDTH = SourceManager.GoodTankUp.getWidth();

	public int HEIGHT = SourceManager.GoodTankUp.getHeight();

	protected boolean isAlive = true;

	public TankFrame tf;// 持有frame对象

	public TankGroup group = TankGroup.RED;// 默认红队

	protected Random random = new Random();

	public Rectangle rectangle = new Rectangle();// tank形成的矩形

	protected FireStrategy fireStrategy;// 发射策略

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public boolean isMove() {
		return isMove;
	}

	public void setMove(boolean isMove) {
		this.isMove = isMove;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public abstract void fire();

	public abstract void paint(Graphics g);

	public abstract void die();

}
