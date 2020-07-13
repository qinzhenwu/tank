package my_tank.com.qin.factory;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Explode;
import my_tank.com.qin.model.RectBullet;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.product.BaseBullet;
import my_tank.com.qin.product.BaseExplode;
import my_tank.com.qin.product.BaseTank;
import my_tank.com.qin.utils.Audio;

/**
 * 好（RED）tank的创建工厂
 * 
 * @author qinzhenwu
 *
 */
public class RectBulletFactory extends GameFactory {

	private RectBulletFactory() {

	}

	private static class GoodTF {
		private static final RectBulletFactory instance = new RectBulletFactory();
	}

	public static RectBulletFactory getInstance() {
		return GoodTF.instance;
	}

	@Override
	public BaseTank createTank(int x, int y, Dir dir,TankGroup group, TankFrame tf) {
		return new Tank(x, y, dir, tf, group);
	}

	@Override
	public BaseBullet createBullet(BaseTank tank, Dir dir) {
		int bX = tank.x + tank.WIDTH / 2 - Bullet.width / 2;
		int bY = tank.y + tank.WIDTH / 2 - Bullet.height / 2;// 计算子弹的位置
		return new RectBullet(bX, bY, dir, true, tank.tf, tank.group);// 向frame对象中的list中放入子弹对象，然后不断打印
	}

	@Override
	public BaseExplode createExplode(BaseTank tank) {
		int eX = tank.x + tank.WIDTH / 2 - Explode.WIDTH / 2;
		int eY = tank.y + tank.HEIGHT / 2 - Explode.HEIGHT / 2;
		return new Explode(eX, eY, tank.tf);
	}

}
