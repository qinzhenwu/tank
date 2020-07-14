package my_tank.com.qin.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankGroup;

/**
 * ��Ϸģ�飬frameֻ��Ҫ��������paint�������ɡ������������ģʽ
 * 
 * @author qinzhenwu
 *
 */
public class GameModel {
	public Tank tank = new Tank(200, 200, Dir.RIGHT, this, TankGroup.RED);
//	public List<Tank> enemyTanks = new ArrayList<>();// �л�̹��
//	public List<Bullet> bullets = new ArrayList();// �ӵ�
//	public List<Explode> explodes = new ArrayList();// ��ըЧ��
	public List<GameObject> gameObjects = new ArrayList<>();
	private Random random = new Random();

	public GameModel() {
		addEnemy();
	}

	private void addEnemy() {
		for (int i = 0; i < 8; i++) {
			int x = 150 + (i * 80);
			int y = 100;
			Tank enTank = new Tank(x, y, Dir.DOWN, this, TankGroup.BLUE);
			enTank.setMove(true);
			this.gameObjects.add(enTank);
		}
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
//		g.drawString("�ӵ���������" + bullets.size(), 10, 80);
//		g.drawString("���˵�������" + enemyTanks.size(), 10, 110);
//		g.drawString("��ը��������" + explodes.size(), 10, 140);
		g.setColor(c);
		tank.paint(g);

		for (int i = 0; i < this.gameObjects.size(); i++) {
			gameObjects.get(i).paint(g);
			for (int j = 0; j < this.gameObjects.size(); j++) {

			}
		}
//		drawEnemy(g);
		// drawExplode(g);
	}
//	
//	private void drawExplode(Graphics g) {
//		if (explodes.size() > 0) {
//			for (int i = 0; i < explodes.size(); i++) {
//				Explode e = explodes.get(i);
//				e.paint(g);
//			}
//		}
//	}

}
