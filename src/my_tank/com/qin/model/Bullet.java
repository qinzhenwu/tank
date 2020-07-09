package my_tank.com.qin.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.sound.sampled.AudioInputStream;

import javafx.scene.media.AudioClip;
import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.manager.SourceManager;
import my_tank.com.qin.utils.Audio;
import sun.audio.AudioData;

/**
 * 子弹类
 * 
 * @author qinzhenwu
 *
 */
public class Bullet {

	// 速度，坐标，方向，大小（宽度、高度）

	// 定义成圆的，红色，画子弹
	private int speed = 10;

	private int x;

	private int y;

	public static int width = SourceManager.bulletDn.getWidth();

	public static int height = SourceManager.bulletDn.getHeight();

	private Dir dir = Dir.DOWN;

	private TankFrame tankFrame;

	private Rectangle rectangle = new Rectangle();// 子弹形成的矩形

	private boolean isAlive = false;// 默认子弹是失效的

	private TankGroup group = TankGroup.RED;// 默认红队

	public void die() {
		this.isAlive = false;
		new Thread(()->new Audio("audio/explode.wav").play()).start();//新建个子线程处理声音，在主线程中会引起卡顿
	}

	/**
	 * 碰撞方法
	 * 
	 * @param tank
	 */
	public boolean crash(Tank tank) {
		boolean isCrash = false;
		if (this.group != tank.getGroup()) {// 同组的子弹不碰撞
			rectangle.setBounds(this.x, this.y, width, height);
			isCrash = rectangle.intersects(tank.getRectangle());
			if (isCrash) {// 如果矩形包含表示碰撞
				this.die();
				tank.die();
				int eX = tank.getX() + tank.WIDTH / 2 - Explode.WIDTH / 2;
				int eY = tank.getY() + tank.HEIGHT / 2 - Explode.HEIGHT / 2;
				this.tankFrame.explodes.add(new Explode(eX, eY, this.tankFrame));
			}
		}
		return isCrash;
	}

	public Bullet(int x, int y, Dir dir, boolean isAlive, TankFrame tankFrame, TankGroup group) {
		super();
		this.x = x;
		this.dir = dir;
		this.y = y;
		this.group = group;
		this.isAlive = isAlive;
		this.tankFrame = tankFrame;
	}

	public void paint(Graphics g) {

		if (!this.isAlive) {
			tankFrame.bullets.remove(this);// 此处list的remove方法，如果frame采用iterator迭代器的方式遍历的话会报错，经典面试题。
		}
		switch (dir) {
		case DOWN:
			this.y += speed;
			g.drawImage(SourceManager.bulletDn, x, y, null);
			break;
		case UP:
			this.y -= speed;
			g.drawImage(SourceManager.bulletUp, x, y, null);
			break;
		case LEFT:
			this.x -= speed;
			g.drawImage(SourceManager.bulletLf, x, y, null);
			break;
		case RIGHT:
			this.x += speed;
			g.drawImage(SourceManager.bulletRi, x, y, null);
			break;
		default:
			break;
		}
		if (x < 0 || y < 0 || y > tankFrame.WIDTH || x > tankFrame.HEIGHT) {// 子弹失效的原因，越界
			this.setAlive(false);
		}
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public TankFrame getTankFrame() {
		return tankFrame;
	}

	public void setTankFrame(TankFrame tankFrame) {
		this.tankFrame = tankFrame;
	}

}
