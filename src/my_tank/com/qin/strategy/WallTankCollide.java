package my_tank.com.qin.strategy;

import my_tank.com.qin.model.GameObject;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.model.Wall;

/**
 * 墙坦克和坦克的碰撞策略
 * 
 * @author qinzhenwu
 *
 */
public class WallTankCollide implements CollideStrategy {

	@Override
	public boolean collide(GameObject g1, GameObject g2) {

		if (g1 instanceof Wall && g2 instanceof Tank) {
			Wall w = (Wall) g1;
			Tank t2 = (Tank) g2;
			crash(w, t2);
		} else if (g1 instanceof Tank && g2 instanceof Wall) {
			collide(g2, g1);
		}
		return true;
	}

	private void crash(Wall w, Tank t2) {
		if (w.getRectangle().intersects(t2.getRectangle())) {
			getRandomDir(t2);
		}
	}

	private void getRandomDir(Tank tank) {
		tank.setX(tank.oldX);
		tank.setY(tank.oldY);
	}
}
