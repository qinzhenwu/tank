package my_tank.com.qin.strategy;

import my_tank.com.qin.frame.Dir;
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
		Dir[] dirs = Dir.values();
		for (Dir dir : dirs) {// �ĸ������
			tank.tf.factory.createBullet(tank, dir);// ��frame�����е�list�з����ӵ�����Ȼ�󲻶ϴ�ӡ
		}
		new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
	}

}
