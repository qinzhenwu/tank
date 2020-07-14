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
 * 游戏模块，frame只需要调用他的paint方法即可。采用门面设计模式
 * 
 * @author qinzhenwu
 *
 */
public class GameModel {
	public Tank tank = new Tank(200, 200, Dir.RIGHT, this, TankGroup.RED);
//	public List<Tank> enemyTanks = new ArrayList<>();// 敌机坦克
//	public List<Bullet> bullets = new ArrayList();// 子弹
//	public List<Explode> explodes = new ArrayList();// 爆炸效果
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
//		g.drawString("子弹的数量：" + bullets.size(), 10, 80);
//		g.drawString("敌人的数量：" + enemyTanks.size(), 10, 110);
//		g.drawString("爆炸的数量：" + explodes.size(), 10, 140);
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
