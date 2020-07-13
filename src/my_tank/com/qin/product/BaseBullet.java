package my_tank.com.qin.product;

import java.awt.Graphics;
import java.awt.Rectangle;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.manager.SourceManager;
import my_tank.com.qin.utils.Audio;

/**
 * �����bullet���󣬾����bullet����ʵ�֣������п��Զ���Щ����������
 * 
 * @author qinzhenwu
 *
 */
public abstract class BaseBullet {

	protected int speed = 10;// �ٶ�

	protected int x;// ����

	protected int y;// ����

	public static int width = SourceManager.bulletDn.getWidth();

	public static int height = SourceManager.bulletDn.getHeight();

	protected Dir dir = Dir.DOWN;// ����

	protected TankFrame tankFrame;// ���е����

	protected Rectangle rectangle = new Rectangle();// �ӵ��γɵľ���

	protected boolean isAlive = false;// Ĭ���ӵ���ʧЧ��

	protected TankGroup group = TankGroup.RED;// Ĭ�Ϻ��

	public abstract void paint(Graphics g);

	public abstract void crash(BaseTank enemyTank);
	
	protected void die() {
		this.isAlive = false;
		new Thread(() -> new Audio("audio/explode.wav").play()).start();// �½������̴߳��������������߳��л����𿨶�
	}
}
