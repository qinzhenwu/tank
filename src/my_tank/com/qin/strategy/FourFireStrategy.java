package my_tank.com.qin.strategy;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.utils.Audio;

/**
 * 四方发射策略
 * 
 * @author qinzhenwu
 *
 */
public class FourFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank tank) {
		int bX = tank.getX() + tank.WIDTH / 2 - Bullet.width / 2;
		int bY = tank.getY() + tank.WIDTH / 2 - Bullet.height / 2;// 计算子弹的位置
		Dir[] dirs = Dir.values();
		for (Dir dir : dirs) {// 四个方向的
			new Bullet(bX, bY, dir, true, tank.getTf(), tank.getGroup());// 向frame对象中的list中放入子弹对象，然后不断打印
		}
		new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();

	}

}
