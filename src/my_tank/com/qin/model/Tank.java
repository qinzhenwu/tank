package my_tank.com.qin.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.util.Random;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.manager.SourceManager;
import my_tank.com.qin.strategy.DefaultFireStrategy;
import my_tank.com.qin.strategy.FireStrategy;
import my_tank.com.qin.strategy.FourFireStrategy;
import my_tank.com.qin.utils.Audio;

/**
 * ̹�˶���
 * 
 * @author qinzhenwu
 *
 */
public class Tank extends GameObject {

	private Dir dir = Dir.DOWN;// ����

	private int speed = 5;

	private boolean isMove = false;// �趨�����Ƿ���ƶ�

	public int WIDTH = SourceManager.GoodTankUp.getWidth();

	public int HEIGHT = SourceManager.GoodTankUp.getHeight();

	private boolean isAlive = true;

	public GameModel gameModel;// ����frame����

	private TankGroup group = TankGroup.RED;// Ĭ�Ϻ��

	private Random random = new Random();

	private Rectangle rectangle = new Rectangle();// tank�γɵľ���

	private FireStrategy fireStrategy;// �������

	/**
	 * ���췽��
	 * 
	 * @param x
	 * @param y
	 * @param dir
	 */
	public Tank(int x, int y, Dir dir, GameModel gameModel, TankGroup group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.gameModel = gameModel;
		this.group = group;
		this.rectangle.x = this.x;
		this.rectangle.y = this.y;
		this.rectangle.width = WIDTH;
		this.rectangle.height = HEIGHT;
		// ����tankʱָ���������
		if (this.group == TankGroup.RED) {// ���Խ����Ե�ʵ���ࣨȫ·��������������ļ���ͨ��propertyMg��ȡ��·����ͨ������ķ�ʽ����ʵ��
			this.fireStrategy = new FourFireStrategy();
		} else {
			this.fireStrategy = new DefaultFireStrategy();
		}
	}

	public void fire() {
		fireStrategy.fire(this);
	}

	public void paint(Graphics g) {
		if (!isAlive) {
			gameModel.gameObjects.remove(this);
		}
		boundsCheck();
		rectangle.x = this.x;
		rectangle.y = this.y;// ���ö�Ӧ���εĴ�С
		if (!isMove) {
			drawStopTank(g);
			return;
		} else {
			move(g);
		}

	}

	/**
	 * �߽���
	 */
	private void boundsCheck() {
		if (this.x <= 2) {
			this.x = 2;
		}
		if (this.x >= (TankFrame.WIDTH - this.WIDTH - 2)) {
			this.x = (TankFrame.WIDTH - this.WIDTH - 2);
		}
		if (this.y <= 32) {// �������Ŀ��
			this.y = 32;
		}
		if (y >= (TankFrame.HEIGHT - this.HEIGHT - 2)) {
			y = (TankFrame.HEIGHT - this.HEIGHT - 2);
		}
	}

	/**
	 * �ƶ�
	 * 
	 * @param g
	 */
	private void move(Graphics g) {

		// �л�̹�˷������
		if (this.group == TankGroup.BLUE && random.nextInt(1000) > 965) {
			this.dir = Dir.values()[random.nextInt(4)];
			int rand = random.nextInt(1000);
			if (rand > 500) {//���fire�ķ����ƶ���move�����У�
				fire();
			}
		}

		switch (dir) {
		case DOWN:
			this.y += speed;
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankDn : SourceManager.BadTankDn, this.x,
					this.y, null);// this.group == TankGroup.RED ����tank�ķ��飬������ͬ��tank
			break;
		case UP:
			this.y -= speed;
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankUp : SourceManager.BadTankUp, this.x,
					this.y, null);
			break;
		case LEFT:
			this.x -= speed;
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankLf : SourceManager.BadTankLf, this.x,
					this.y, null);
			break;
		case RIGHT:
			this.x += speed;
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankRi : SourceManager.BadTankRi, this.x,
					this.y, null);
			break;
		default:
			break;
		}
	}

	/**
	 * ��ͣ��tank
	 * 
	 * @param g
	 */
	private void drawStopTank(Graphics g) {
		switch (dir) {
		case DOWN:
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankDn : SourceManager.BadTankDn, this.x,
					this.y, null);// this.group == TankGroup.RED ����tank�ķ��飬������ͬ��tank
			break;
		case UP:
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankUp : SourceManager.BadTankUp, this.x,
					this.y, null);
			break;
		case LEFT:
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankLf : SourceManager.BadTankLf, this.x,
					this.y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankRi : SourceManager.BadTankRi, this.x,
					this.y, null);
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
