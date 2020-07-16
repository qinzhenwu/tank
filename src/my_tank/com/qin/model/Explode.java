package my_tank.com.qin.model;

import java.awt.Graphics;

import com.sun.deploy.uitoolkit.impl.fx.ui.resources.ResourceManager;

import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.manager.SourceManager;

/**
 * ��ըЧ��
 * 
 * @author qinzhenwu
 *
 */
public class Explode extends GameObject {
	 
	private int step = 0;// ��ǰЧ��ˢ�˶��ٴΣ�һ��16��ͼƬ������16������Ϊ0


	public static int WIDTH = SourceManager.explode[0].getWidth();
	public static int HEIGHT = SourceManager.explode[0].getHeight();

	private boolean isOver = true;// ��ը����

	public Explode(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.isOver = false;
	}

	public void paint(Graphics g) {
		if (step >= 0 && step < 16) {// ��ʼչʾ��ըЧ��
			g.drawImage(SourceManager.explode[step++], x, y, null);
		} else if (step == 16) {// ˢ��16�κ�ֹͣ��ը
			step = 0;
			isOver = true;
		}
		if (isOver) {
			GameModel.getInstance().remove(this);
		}

	}

}
