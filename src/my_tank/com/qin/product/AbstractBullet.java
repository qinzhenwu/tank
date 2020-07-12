package my_tank.com.qin.product;

import java.awt.Rectangle;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.manager.SourceManager;

/**
 * 抽象的bullet对象，具体的bullet子类实现，此类中可以定义些公共的属性
 * 
 * @author qinzhenwu
 *
 */
public abstract class AbstractBullet {

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
}
