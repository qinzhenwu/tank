package my_tank.com.qin.model;

import java.awt.Color;
import java.awt.Graphics;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.manager.SourceManager;
import my_tank.com.qin.product.BaseBullet;
import my_tank.com.qin.product.BaseTank;

/**
 * 方形的子弹
 * 
 * @author qinzhenwu
 *
 */
public class RectBullet extends BaseBullet {

	public RectBullet(int bX, int bY, Dir dir, boolean b, TankFrame tf, TankGroup group) {
		this.x = bX;
		this.dir = dir;
		this.y = bY;
		this.group = group;
		this.isAlive = b;
		this.tankFrame = tf;
		this.tankFrame.bullets.add(this);
	}

	@Override
	public void paint(Graphics g) {
		// 采用不同的策略
		if (!this.isAlive) {
			tankFrame.bullets.remove(this);// 此处list的remove方法，如果frame采用iterator迭代器的方式遍历的话会报错，经典面试题。
		}
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		g.setColor(c);
		
		switch (dir) {
		case DOWN:
			this.y += speed;
			break;
		case UP:
			this.y -= speed;
			break;
		case LEFT:
			this.x -= speed;
			break;
		case RIGHT:
			this.x += speed;
			break;
		default:
			break;
		}
		if (x < 0 || y < 0 || y > tankFrame.WIDTH || x > tankFrame.HEIGHT) {// 子弹失效的原因，越界
			this.isAlive=false;
		}
	}

	@Override
	public void crash(BaseTank enemyTank) {
		boolean isCrash = false;
		if (this.group != enemyTank.group) {// 同组的子弹不碰撞
			rectangle.setBounds(this.x, this.y, width, height);
			isCrash = rectangle.intersects(enemyTank.rectangle);
			if (isCrash) {// 如果矩形包含表示碰撞
				die();
				enemyTank.die();
				int eX = enemyTank.x + enemyTank.WIDTH / 2 - Explode.WIDTH / 2;
				int eY = enemyTank.y + enemyTank.HEIGHT / 2 - Explode.HEIGHT / 2;
				this.tankFrame.explodes.add(new Explode(eX, eY, this.tankFrame));
			}
		}
	}

}
