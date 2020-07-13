package my_tank.com.qin.product;

import java.awt.Graphics;
import java.awt.Rectangle;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.manager.SourceManager;
import my_tank.com.qin.utils.Audio;

/**
 * 抽象的bullet对象，具体的bullet子类实现，此类中可以定义些公共的属性
 * 
 * @author qinzhenwu
 *
 */
public abstract class BaseBullet {

	protected int speed = 10;// 速度

	protected int x;// 坐标

	protected int y;// 坐标

	public static int width = SourceManager.bulletDn.getWidth();

	public static int height = SourceManager.bulletDn.getHeight();

	protected Dir dir = Dir.DOWN;// 方向

	protected TankFrame tankFrame;// 持有的面板

	protected Rectangle rectangle = new Rectangle();// 子弹形成的矩形

	protected boolean isAlive = false;// 默认子弹是失效的

	protected TankGroup group = TankGroup.RED;// 默认红队

	public abstract void paint(Graphics g);

	public abstract void crash(BaseTank enemyTank);
	
	protected void die() {
		this.isAlive = false;
		new Thread(() -> new Audio("audio/explode.wav").play()).start();// 新建个子线程处理声音，在主线程中会引起卡顿
	}
}
