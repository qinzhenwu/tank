package my_tank.com.qin.strategy;

import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Explode;
import my_tank.com.qin.model.GameModel;
import my_tank.com.qin.model.GameObject;
import my_tank.com.qin.model.Tank;

/**
 * �ӵ�tank��ײ����
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
	 * ��ײ����
	 * 
	 * @param tank
	 */
	public boolean crash(Bullet bullet, Tank tank) {
		boolean isCrash = false;
		if (bullet.group != tank.getGroup()) {// ͬ����ӵ�����ײ
			isCrash = bullet.rectangle.intersects(tank.getRectangle());
			if (isCrash) {// ������ΰ�����ʾ��ײ
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
