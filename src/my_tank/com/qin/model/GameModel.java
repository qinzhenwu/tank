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

	private static final GameModel INSTANCE = new GameModel();// 饿汉式单例
	private List<GameObject> gameObjects = new ArrayList<>();

	private GameModel() {

	}

	public static GameModel getInstance() {
		return INSTANCE;
	}

	private CollideChain collideChain;

	public Tank tank;

	public void init() {
		collideChain = new CollideChain();
		tank = new Tank(200, 200, Dir.RIGHT, TankGroup.RED);
		addEnemy();
		addWall();
		//gameObjects.add(tank);
	}

	public void add(GameObject gameObject) {
		gameObjects.add(gameObject);
	}

	public void remove(GameObject gameObject) {
		gameObjects.remove(gameObject);
	}

//	public Tank tank = new Tank(200, 200, Dir.RIGHT, this, TankGroup.RED);

//	private CollideStrategy collideStrategy = new BulletTankCollide();
//	private CollideStrategy tankCollide = new TankTankCollide();

//	public GameModel() {
//		addEnemy();
//	}

	private void addEnemy() {
		for (int i = 0; i < 10; i++) {
			int x = 150 + (i * 80);
			int y = 100;
			Tank enTank = new Tank(x, y, Dir.DOWN, TankGroup.BLUE);
			enTank.setMove(true);
			add(enTank);
		}
	}

	private void addWall() {
		new Wall(50, 60, 50, 220);
		new Wall(400, 500, 200, 50);
		new Wall(50, 400, 200, 50);

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
