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
import my_tank.com.qin.product.AbstractBullet;
import my_tank.com.qin.product.AbstractTank;
import my_tank.com.qin.utils.Audio;
import sun.audio.AudioData;

/**
 * �ӵ���
 * 
 * @author qinzhenwu
 *
 */
public class Bullet extends AbstractBullet{

	 

	public void die() {
		this.isAlive = false;
		new Thread(() -> new Audio("audio/explode.wav").play()).start();// �½������̴߳��������������߳��л����𿨶�
	}

	/**
	 * ��ײ����
	 * 
	 * @param enemyTank
	 */
	public boolean crash(AbstractTank enemyTank) {
		boolean isCrash = false;
		if (this.group != enemyTank.group) {// ͬ����ӵ�����ײ
			rectangle.setBounds(this.x, this.y, width, height);
			isCrash = rectangle.intersects(enemyTank.rectangle);
			if (isCrash) {// ������ΰ�����ʾ��ײ
				this.die();
				enemyTank.die();
				int eX = enemyTank.x + enemyTank.WIDTH / 2 - Explode.WIDTH / 2;
				int eY = enemyTank.y + enemyTank.HEIGHT / 2 - Explode.HEIGHT / 2;
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
		this.tankFrame.bullets.add(this);
	}

	public void paint(Graphics g) {
		// ���ò�ͬ�Ĳ���
		if (!this.isAlive) {
			tankFrame.bullets.remove(this);// �˴�list��remove���������frame����iterator�������ķ�ʽ�����Ļ��ᱨ�����������⡣
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
		if (x < 0 || y < 0 || y > tankFrame.WIDTH || x > tankFrame.HEIGHT) {// �ӵ�ʧЧ��ԭ��Խ��
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
