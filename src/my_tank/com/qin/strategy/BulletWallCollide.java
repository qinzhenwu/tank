package my_tank.com.qin.strategy;

import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Explode;
import my_tank.com.qin.model.GameModel;
import my_tank.com.qin.model.GameObject;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.model.Wall;

/**
 * 子弹tank碰撞策略
 * 
 * @author qinzhenwu
 *
 */
public class BulletWallCollide implements CollideStrategy {

	@Override
	public boolean collide(GameObject g1, GameObject g2) {
		if (g1 instanceof Bullet && g2 instanceof Wall) {
			Bullet b = (Bullet) g1;
			Wall w = (Wall) g2;
			crash(b, w);
		} else if (g1 instanceof Wall && g2 instanceof Bullet) {
			collide(g2, g1);
		}
		return true;
	}

	/**
	 * 碰撞方法
	 * 
	 * @param tank
	 */
	public boolean crash(Bullet bullet, Wall wall) {
		boolean isCrash = false;
		isCrash = bullet.rectangle.intersects(wall.getRectangle());
		if (isCrash) {// 如果矩形包含表示碰撞
			bullet.die();
		}
		return isCrash;
	}
}
