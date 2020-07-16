package my_tank.com.qin.strategy;

import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Explode;
import my_tank.com.qin.model.GameModel;
import my_tank.com.qin.model.GameObject;
import my_tank.com.qin.model.Tank;

/**
 * 子弹tank碰撞策略
 * 
 * @author qinzhenwu
 *
 */
public class BulletTankCollide implements CollideStrategy {

	@Override
	public boolean collide(GameObject g1, GameObject g2) {
		if (g1 instanceof Bullet && g2 instanceof Tank) {
			Bullet b = (Bullet) g1;
			Tank t = (Tank) g2;
			return !crash(b, t);
		} else if (g1 instanceof Tank && g2 instanceof Bullet) {
			return collide(g2, g1);
		}
		return true;
	}

	/**
	 * 碰撞方法
	 * 
	 * @param tank
	 */
	public boolean crash(Bullet bullet, Tank tank) {
		boolean isCrash = false;
		if (bullet.group != tank.getGroup()) {// 同组的子弹不碰撞
			isCrash = bullet.rectangle.intersects(tank.getRectangle());
			if (isCrash) {// 如果矩形包含表示碰撞
				bullet.die();
				tank.die();
				int eX = tank.getX() + tank.WIDTH / 2 - Explode.WIDTH / 2;
				int eY = tank.getY() + tank.HEIGHT / 2 - Explode.HEIGHT / 2;
				GameModel.getInstance().add(new Explode(eX, eY));
			}
		}
		return isCrash;
	}
}
