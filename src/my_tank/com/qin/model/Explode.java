package my_tank.com.qin.model;

import java.awt.Graphics;

import com.sun.deploy.uitoolkit.impl.fx.ui.resources.ResourceManager;

import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.manager.SourceManager;
import my_tank.com.qin.product.BaseExplode;

/**
 * ��ըЧ��
 * 
 * @author qinzhenwu
 *
 */
public class Explode extends BaseExplode {
	private int x;// ����

	private int y;// ����

	private int step = 0;// ��ǰЧ��ˢ�˶��ٴΣ�һ��16��ͼƬ������16������Ϊ0

	private TankFrame tf;// ����frame����

	public static int WIDTH = SourceManager.explode[0].getWidth();
	public static int HEIGHT = SourceManager.explode[0].getHeight();

	private boolean isOver = true;// ��ը����

	public Explode(int x, int y, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
		this.isOver = false;
	}

	@Override
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
