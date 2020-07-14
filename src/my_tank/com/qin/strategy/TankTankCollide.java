package my_tank.com.qin.strategy;

import my_tank.com.qin.model.GameObject;
import my_tank.com.qin.model.Tank;

/**
 * 坦克和坦克的碰撞策略
 * 
 * @author qinzhenwu
 *
 */
public class TankTankCollide implements CollideStrategy {

	@Override
	public boolean collide(GameObject g1, GameObject g2) {

		if (g1 instanceof Tank && g2 instanceof Tank) {
			Tank t1 = (Tank) g1;
			Tank t2 = (Tank) g2;
			return !crash(t1, t2);
		}
		return true;
	}

	private boolean crash(Tank t1, Tank t2) {
		if (t1.getRectangle().intersects(t2.getRectangle())) {
			getRandomDir(t1);
			getRandomDir(t2);
			return true;
		}
		return true;
	}

	private void getRandomDir(Tank tank) {
		tank.x = tank.oldX;
		tank.y = tank.oldY;
	}
}
