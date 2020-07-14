package my_tank.com.qin.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.strategy.BulletTankCollide;
import my_tank.com.qin.strategy.CollideChain;
import my_tank.com.qin.strategy.CollideStrategy;
import my_tank.com.qin.strategy.TankTankCollide;

/**
 * 游戏模块，frame只需要调用他的paint方法即可。采用门面设计模式
 * 
 * @author qinzhenwu
 *
 */
public class GameModel {
	public Tank tank = new Tank(200, 200, Dir.RIGHT, this, TankGroup.RED);
	public List<GameObject> gameObjects = new ArrayList<>();
	private CollideStrategy collideStrategy = new BulletTankCollide();
	private CollideStrategy tankCollide = new TankTankCollide();
	private CollideChain collideChain = new CollideChain();

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
			for (int j = i + 1; j < this.gameObjects.size(); j++) {
				GameObject g1 = gameObjects.get(i);
				GameObject g2 = gameObjects.get(j);
//				collideStrategy.collide(g1, g2);//子弹tank间碰撞策略
//				tankCollide.collide(g1, g2);//tank间碰撞策略
				collideChain.collide(g1, g2);// 使用责任链模式替换

			}
		}
	}

}
