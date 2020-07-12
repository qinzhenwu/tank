package my_tank.com.qin.factory;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.product.AbstractBullet;
import my_tank.com.qin.product.AbstractTank;
import my_tank.com.qin.utils.Audio;

/**
 * 好（RED）tank的创建工厂
 * 
 * @author qinzhenwu
 *
 */
public class GoodTankFactory extends AbstractFactory {

	private GoodTankFactory() {

	}

	private static class GoodTF {
		private static final GoodTankFactory instance = new GoodTankFactory();
	}

	public static GoodTankFactory getInstance() {
		return GoodTF.instance;
	}

	@Override
	public AbstractTank createTank(int x, int y, Dir dir, TankFrame tf) {
		return new Tank(x, y, dir, tf, TankGroup.RED);
	}

	@Override
	public AbstractBullet createBullet(AbstractTank tank, Dir dir) {
		int bX = tank.x + tank.WIDTH / 2 - Bullet.width / 2;
		int bY = tank.y + tank.WIDTH / 2 - Bullet.height / 2;// 计算子弹的位置
		return new Bullet(bX, bY, dir, true, tank.tf, tank.group);// 向frame对象中的list中放入子弹对象，然后不断打印
	}

}
