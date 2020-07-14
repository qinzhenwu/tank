package my_tank.com.qin.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.util.Random;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.manager.SourceManager;
import my_tank.com.qin.strategy.DefaultFireStrategy;
import my_tank.com.qin.strategy.FireStrategy;
import my_tank.com.qin.strategy.FourFireStrategy;
import my_tank.com.qin.utils.Audio;

/**
 * 坦克对象
 * 
 * @author qinzhenwu
 *
 */
public class Tank extends GameObject {

	private Dir dir = Dir.DOWN;// 方向

	private int speed = 5;

	private boolean isMove = false;// 设定弹框是否可移动

	public int WIDTH = SourceManager.GoodTankUp.getWidth();

	public int HEIGHT = SourceManager.GoodTankUp.getHeight();

	private boolean isAlive = true;

	public GameModel gameModel;// 持有frame对象

	private TankGroup group = TankGroup.RED;// 默认红队

	private Random random = new Random();

	private Rectangle rectangle = new Rectangle();// tank形成的矩形

	private FireStrategy fireStrategy;// 发射策略

	/**
	 * 构造方法
	 * 
	 * @param x
	 * @param y
	 * @param dir
	 */
	public Tank(int x, int y, Dir dir, GameModel gameModel, TankGroup group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.gameModel = gameModel;
		this.group = group;
		this.rectangle.x = this.x;
		this.rectangle.y = this.y;
		this.rectangle.width = WIDTH;
		this.rectangle.height = HEIGHT;
		// 创建tank时指定发射策略
		if (this.group == TankGroup.RED) {// 可以将策略的实现类（全路径）放入的配置文件，通过propertyMg获取到路径，通过反射的方式创建实例
			this.fireStrategy = new FourFireStrategy();
		} else {
			this.fireStrategy = new DefaultFireStrategy();
		}
	}

	public void fire() {
		fireStrategy.fire(this);
	}

	public void paint(Graphics g) {
		if (!isAlive) {
			gameModel.gameObjects.remove(this);
		}
		boundsCheck();
		rectangle.x = this.x;
		rectangle.y = this.y;// 设置对应矩形的大小
		if (!isMove) {
			drawStopTank(g);
			return;
		} else {
			move(g);
		}

	}

	/**
	 * 边界检测
	 */
	private void boundsCheck() {
		if (this.x <= 2) {
			this.x = 2;
		}
		if (this.x >= (TankFrame.WIDTH - this.WIDTH - 2)) {
			this.x = (TankFrame.WIDTH - this.WIDTH - 2);
		}
		if (this.y <= 32) {// 标题栏的宽度
			this.y = 32;
		}
		if (y >= (TankFrame.HEIGHT - this.HEIGHT - 2)) {
			y = (TankFrame.HEIGHT - this.HEIGHT - 2);
		}
	}

	/**
	 * 移动
	 * 
	 * @param g
	 */
	private void move(Graphics g) {

		// 敌机坦克方向随机
		if (this.group == TankGroup.BLUE && random.nextInt(1000) > 965) {
			this.dir = Dir.values()[random.nextInt(4)];
			int rand = random.nextInt(1000);
			if (rand > 500) {//随机fire的方法移动到move方法中，
				fire();
			}
		}

		switch (dir) {
		case DOWN:
			this.y += speed;
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankDn : SourceManager.BadTankDn, this.x,
					this.y, null);// this.group == TankGroup.RED 根据tank的分组，来画不同的tank
			break;
		case UP:
			this.y -= speed;
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankUp : SourceManager.BadTankUp, this.x,
					this.y, null);
			break;
		case LEFT:
			this.x -= speed;
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankLf : SourceManager.BadTankLf, this.x,
					this.y, null);
			break;
		case RIGHT:
			this.x += speed;
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankRi : SourceManager.BadTankRi, this.x,
					this.y, null);
			break;
		default:
			break;
		}
	}

	/**
	 * 画停的tank
	 * 
	 * @param g
	 */
	private void drawStopTank(Graphics g) {
		switch (dir) {
		case DOWN:
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankDn : SourceManager.BadTankDn, this.x,
					this.y, null);// this.group == TankGroup.RED 根据tank的分组，来画不同的tank
			break;
		case UP:
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankUp : SourceManager.BadTankUp, this.x,
					this.y, null);
			break;
		case LEFT:
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankLf : SourceManager.BadTankLf, this.x,
					this.y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == TankGroup.RED ? SourceManager.GoodTankRi : SourceManager.BadTankRi, this.x,
					this.y, null);
			break;
		default:
			break;
		}
	}

	public void die() {
		this.isAlive = false;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public TankGroup getGroup() {
		return group;
	}

	public void setGroup(TankGroup group) {
		this.group = group;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isMove() {
		return isMove;
	}

	public void setMove(boolean isMove) {
		this.isMove = isMove;
	}

 

}
