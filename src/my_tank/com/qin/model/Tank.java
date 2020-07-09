package my_tank.com.qin.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.math.BigDecimal;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.frame.manager.SourceManager;

/**
 * ̹�˶���
 * 
 * @author qinzhenwu
 *
 */
public class Tank {

	private int x;// ����

	private int y;// ����

	private Dir dir = Dir.DOWN;// ����

	private int speed = 5;

	private boolean isMove = false;// �趨�����Ƿ���ƶ�

	private int WIDTH = SourceManager.tankDn.getWidth();

	private int HEIGHT = SourceManager.tankDn.getHeight();

	private boolean isAlive = true;

	private TankFrame tf;// ����frame����

	private TankGroup group = TankGroup.RED;// Ĭ�Ϻ��

	private Rectangle rectangle = new Rectangle();// tank�γɵľ���

	/**
	 * ���췽��
	 * 
	 * @param x
	 * @param y
	 * @param dir
	 */
	public Tank(int x, int y, Dir dir, TankFrame tf, TankGroup group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
	}

	public void fire() {
		int d = +(BigDecimal.valueOf(this.WIDTH).divide(new BigDecimal(2)).intValue()
				- (BigDecimal.valueOf(Bullet.width).divide(new BigDecimal(2)).intValue()));// �����ӵ���λ��
		tf.bullets.add(new Bullet(this.x + d, this.y + d, this.dir, true, this.tf, this.group));// ��frame�����е�list�з����ӵ�����Ȼ�󲻶ϴ�ӡ
	}

	public void paint(Graphics g) {

		if (!isAlive) {
			tf.enemyTanks.remove(this);
		}
		rectangle.setBounds(x, y, WIDTH, HEIGHT);// ���ö�Ӧ���εĴ�С
		if (!isMove) {
			switch (dir) {
			case DOWN:
				g.drawImage(SourceManager.tankDn, this.x, this.y, null);
				break;
			case UP:
				g.drawImage(SourceManager.tankUp, this.x, this.y, null);
				break;
			case LEFT:
				g.drawImage(SourceManager.tankLf, this.x, this.y, null);
				break;
			case RIGHT:
				g.drawImage(SourceManager.tankRi, this.x, this.y, null);
				break;
			default:
				break;
			}
			return;
		}

		switch (dir) {
		case DOWN:
			this.y += speed;
			g.drawImage(SourceManager.tankDn, this.x, this.y, null);
			break;
		case UP:
			this.y -= speed;
			g.drawImage(SourceManager.tankUp, this.x, this.y, null);
			break;
		case LEFT:
			this.x -= speed;
			g.drawImage(SourceManager.tankLf, this.x, this.y, null);
			break;
		case RIGHT:
			this.x += speed;
			g.drawImage(SourceManager.tankRi, this.x, this.y, null);
			break;
		default:
			break;
		}

	}

	public void die() {
		this.isAlive = false;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public TankGroup getGroup() {
		return group;
	}

	public void setGroup(TankGroup group) {
		this.group = group;
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

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isMove() {
		return isMove;
	}

	public void setMove(boolean isMove) {
		this.isMove = isMove;
	}

}
