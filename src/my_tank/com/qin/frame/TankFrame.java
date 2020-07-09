package my_tank.com.qin.frame;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;

import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Explode;
import my_tank.com.qin.model.Tank;

public class TankFrame extends Frame {

	Tank tank = new Tank(200, 200, Dir.RIGHT, this, TankGroup.RED);
	public List<Tank> enemyTanks = new ArrayList<>();// �л�̹��
	public List<Bullet> bullets = new ArrayList();// �ӵ�
	public List<Explode> explodes = new ArrayList();// ��ըЧ��
	public int WIDTH = 600, HEIGHT = 600;

	public TankFrame() {

		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setVisible(true);
		this.setTitle("tank");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

		this.addKeyListener(new MyKeyListener());
	}

	/**
	 * ˫���壬�������
	 */
	Image screenImage = null;

	@Override
	public void update(Graphics g) {
		if (screenImage == null) {
			screenImage = this.createImage(WIDTH, HEIGHT);
		}
		Graphics gScreen = screenImage.getGraphics();
		Color color = gScreen.getColor();
		gScreen.setColor(Color.black);
		gScreen.fillRect(0, 0, WIDTH, HEIGHT);
		gScreen.setColor(color);// �ָ�ԭɫ
		paint(gScreen);
		g.drawImage(screenImage, 0, 0, null);
	}

	/**
	 * Graphics ���ʶ���
	 */
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("�ӵ���������" + bullets.size(), 10, 80);
		g.drawString("���˵�������" + enemyTanks.size(), 10, 110);
		g.drawString("��ը��������" + explodes.size(), 10, 150);
		g.setColor(c);
		tank.paint(g);

		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			b.paint(g);// �������е��ӵ�
			drawEnemy(g);
			if (enemyTanks.size() > 0) {
				for (int k = 0; k < enemyTanks.size(); k++) {
					Tank tank = enemyTanks.get(k);
					boolean isExplode = b.crash(tank);
					if (isExplode) {
						explodes.add(new Explode(tank.getX(), tank.getY(), this));
					}

				}

			}
		}
		drawEnemy(g);
		drawExplode(g);
	}

	private void drawExplode(Graphics g) {
		if (explodes.size() > 0) {
			for (int i = 0; i < explodes.size(); i++) {
				Explode e = explodes.get(i);
				e.paint(g);
			}
		}
	}

	/**
	 * �����л������ж��ӵ��Ƿ�����
	 * 
	 * @param g
	 * @param b
	 */
	private void drawEnemy(Graphics g) {
		if (enemyTanks.size() > 0) {
			for (int i = 0; i < enemyTanks.size(); i++) {
				Tank tank = enemyTanks.get(i);
				tank.paint(g);
			}
		}
	}

	/**
	 * ���̼�����
	 * 
	 * @author qinzhenwu
	 *
	 */
	class MyKeyListener extends KeyAdapter {

		/**
		 * ǰ������
		 */
		boolean is_u = false;
		boolean is_d = false;
		boolean is_l = false;
		boolean is_r = false;

		/**
		 * ��������ʱ�����
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_LEFT:
				is_l = true;
				break;
			case KeyEvent.VK_UP:
				is_u = true;
				break;
			case KeyEvent.VK_RIGHT:
				is_r = true;
				break;
			case KeyEvent.VK_DOWN:
				is_d = true;
				break;

			default:
				break;
			}
			setDir();
		}

		/**
		 * ����������ʱ�����
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_LEFT:
				is_l = false;
				break;
			case KeyEvent.VK_UP:
				is_u = false;
				break;
			case KeyEvent.VK_RIGHT:
				is_r = false;
				break;
			case KeyEvent.VK_DOWN:
				is_d = false;
				break;
			case KeyEvent.VK_CONTROL:// ctl��
				tank.fire();
				break;
			case KeyEvent.VK_SHIFT:// SHIFT��ֹͣtank
				tank.setMove(false);
				break;
			default:
				break;
			}
			setDir();
		}

		private void setDir() {
			if (is_u) {
				tank.setDir(Dir.UP);
			}
			if (is_d) {
				tank.setDir(Dir.DOWN);
			}
			if (is_l) {
				tank.setDir(Dir.LEFT);
			}
			if (is_r) {
				tank.setDir(Dir.RIGHT);
			}
			if (is_u || is_d || is_l || is_r) {// ���˷�����������ƶ�
				tank.setMove(true);
			}

		}

	}

}
