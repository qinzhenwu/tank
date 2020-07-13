package my_tank.com.qin.factory;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Explode;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.product.BaseBullet;
import my_tank.com.qin.product.BaseExplode;
import my_tank.com.qin.product.BaseTank;
import my_tank.com.qin.utils.Audio;
import sun.security.jca.GetInstance;

/**
 * �ã�RED��tank�Ĵ�������
 * 
 * @author qinzhenwu
 *
 */
public class BadTankFactory extends GameFactory {

	/**
	 * ˽�й��췽������ֹ�����ഴ��
	 */
	private BadTankFactory() {

	}

	/**
	 * ˽�о�̬�ڲ��൥��ģʽ
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
	public BaseTank createTank(int x, int y, Dir dir,TankGroup group, TankFrame tf) {
		return new Tank(x, y, dir, tf, group);
	}

	@Override
	public BaseBullet createBullet(BaseTank tank,Dir dir) {
		int bX = tank.x + tank.WIDTH / 2 - Bullet.width / 2;
		int bY = tank.y + tank.WIDTH / 2 - Bullet.height / 2;// �����ӵ���λ��
		return new Bullet(bX, bY, tank.dir, true, tank.tf, tank.group);// ��frame�����е�list�з����ӵ�����Ȼ�󲻶ϴ�ӡ
	}

	@Override
	public BaseExplode createExplode(BaseTank tank) {
		int eX = tank.x + tank.WIDTH / 2 - Explode.WIDTH / 2;
		int eY = tank.y + tank.HEIGHT / 2 - Explode.HEIGHT / 2;
		return new Explode(eX, eY, tank.tf);
	}

}
