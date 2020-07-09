package my_tank.com.qin.model;

import java.awt.Graphics;

import com.sun.deploy.uitoolkit.impl.fx.ui.resources.ResourceManager;

import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.manager.SourceManager;

/**
 * ��ըЧ��
 * 
 * @author qinzhenwu
 *
 */
public class Explode {
	private int x;// ����

	private int y;// ����

	private int step = 0;// ��ǰЧ��ˢ�˶��ٴΣ�һ��16��ͼƬ������16������Ϊ0

	private TankFrame tf;// ����frame����

	private boolean isOver = true;// ��ը����

	public Explode(int x, int y, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
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
			tf.explodes.remove(this);
		}

	}

}
