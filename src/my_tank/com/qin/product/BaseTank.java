package my_tank.com.qin.product;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.manager.SourceManager;
import my_tank.com.qin.strategy.FireStrategy;

/**
 * �����tank���󣬾����tank����ʵ�֣������п��Զ���Щ����������
 * 
 * @author qinzhenwu
 *
 */
public abstract class BaseTank {
	public int x;// ����

	public int y;// ����

	public Dir dir = Dir.DOWN;// ����

	protected int speed = 5;

	protected boolean isMove = false;// �趨�����Ƿ���ƶ�

	public int WIDTH = SourceManager.GoodTankUp.getWidth();

	public int HEIGHT = SourceManager.GoodTankUp.getHeight();

	protected boolean isAlive = true;

	public TankFrame tf;// ����frame����

	public TankGroup group = TankGroup.RED;// Ĭ�Ϻ��

	protected Random random = new Random();

	public Rectangle rectangle = new Rectangle();// tank�γɵľ���

	protected FireStrategy fireStrategy;// �������

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public boolean isMove() {
		return isMove;
	}

	public void setMove(boolean isMove) {
		this.isMove = isMove;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public abstract void fire();

	public abstract void paint(Graphics g);

	public abstract void die();

}
