package my_tank.com.qin.strategy;

import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.utils.Audio;

/**
 * Ĭ�ϵ�����
 * 
 * @author qinzhenwu
 *
 */
public class DefaultFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank tank) {
		int bX = tank.getX() + tank.WIDTH / 2 - Bullet.width / 2;
		int bY = tank.getY() + tank.WIDTH / 2 - Bullet.height / 2;// �����ӵ���λ��

		new Bullet(bX, bY, tank.getDir(), true, tank.tf, tank.getGroup());// ��frame�����е�list�з����ӵ�����Ȼ�󲻶ϴ�ӡ
		new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
	}

}
