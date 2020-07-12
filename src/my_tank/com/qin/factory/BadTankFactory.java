package my_tank.com.qin.factory;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.product.AbstractBullet;
import my_tank.com.qin.product.AbstractTank;
import my_tank.com.qin.utils.Audio;
import sun.security.jca.GetInstance;

/**
 * 好（RED）tank的创建工厂
 * 
 * @author qinzhenwu
 *
 */
public class BadTankFactory extends AbstractFactory {

	/**
	 * 私有构造方法，防止其他类创建
	 */
	private BadTankFactory() {

	}

	/**
	 * 私有静态内部类单例模式
	 * 
	 * @author qinzhenwu
	 *
	 */
	private static class BadFac {
		private final static BadTankFactory instance = new BadTankFactory();
	}

	public static BadTankFactory getInstance() {
		return BadFac.instance;
	}

	@Override
	public AbstractTank createTank(int x, int y, Dir dir, TankFrame tf) {
		return new Tank(x, y, dir, tf, TankGroup.BLUE);
	}

	@Override
	public AbstractBullet createBullet(AbstractTank tank,Dir dir) {
		int bX = tank.x + tank.WIDTH / 2 - Bullet.width / 2;
		int bY = tank.y + tank.WIDTH / 2 - Bullet.height / 2;// 计算子弹的位置
		return new Bullet(bX, bY, tank.dir, true, tank.tf, tank.group);// 向frame对象中的list中放入子弹对象，然后不断打印
	}

}
