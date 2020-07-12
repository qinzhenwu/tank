package my_tank.com.qin.strategy;

import my_tank.com.qin.factory.AbstractFactory;
import my_tank.com.qin.factory.GoodTankFactory;
import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.utils.Audio;

/**
 * �ķ��������
 * 
 * @author qinzhenwu
 *
 */
public class FourFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank tank) {
		int bX = tank.getX() + tank.WIDTH / 2 - Bullet.width / 2;
		int bY = tank.getY() + tank.WIDTH / 2 - Bullet.height / 2;// �����ӵ���λ��
		Dir[] dirs = Dir.values();
		AbstractFactory factory=GoodTankFactory.getInstance();
		
		for (Dir dir : dirs) {// �ĸ������
			factory.createBullet(tank,dir);// ��frame�����е�list�з����ӵ�����Ȼ�󲻶ϴ�ӡ
		}
		new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();

	}

}
