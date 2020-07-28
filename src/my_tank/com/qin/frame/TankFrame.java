package my_tank.com.qin.frame;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.swing.LayoutStyle;

import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Explode;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.utils.Audio;

public class TankFrame extends Frame {

	public static final TankFrame INSTANCE = new TankFrame();

	private Random r=new Random();
	
	public List<Tank> enemyTanks = new ArrayList<>();// 敌机坦克
	
	public Map<String, Tank> tanks=new HashMap<>();
	public List<Bullet> bullets = new ArrayList();// 子弹
	public List<Explode> explodes = new ArrayList();// 爆炸效果
	public List<Tank> allCrashTanks = new ArrayList<>();// 需要碰撞的tank
	public int WIDTH = 800, HEIGHT = 800;
	Tank tank = new Tank(r.nextInt(WIDTH), r.nextInt(HEIGHT), Dir.RIGHT, this, TankGroup.RED);

	private Random random = new Random();

	public TankFrame() {

		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setVisible(true);
		this.setLocation(600, 200);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

		this.addKeyListener(new MyKeyListener());
	}

	/**
	 * 双缓冲，解决闪屏
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
		gScreen.setColor(color);// 恢复原色
		paint(gScreen);
		g.drawImage(screenImage, 0, 0, null);
	}

	/**
	 * Graphics 画笔对象
	 */
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量：" + bullets.size(), 10, 80);
		g.drawString("敌人的数量：" + enemyTanks.size(), 10, 110);
		g.drawString("爆炸的数量：" + explodes.size(), 10, 140);
		g.setColor(c);
		tank.paint(g);

		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			b.paint(g);// 画出所有的子弹
			if (enemyTanks.size() > 0) {
				for (int k = 0; k < enemyTanks.size(); k++) {
					Tank enemyTank = enemyTanks.get(k);
					b.crash(enemyTank);
				}
				// boolean isKill = b.crash(tank);
//				if (isKill) {不会被打，无敌
//					explodes.add(new Explode(tank.getX(), tank.getY(), this));
//				}
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
	 * 画出敌机，并判断子弹是否命中
	 * 
	 * @param g
	 * @param b
	 */
	private void drawEnemy(Graphics g) {
		if (enemyTanks.size() > 0) {
			for (int i = 0; i < enemyTanks.size(); i++) {
				Tank tank = enemyTanks.get(i);
				tank.paint(g);
				int rand = random.nextInt(1000);
				if (rand > 950) {
					tank.fire();
				}
			}
		}

	}

	/**
	 * 获取到随机方向，不能与原来的方向一致
	 * 
	 * @param tank
	 * @return
	 */
	public Dir getRandomDir(Tank tank) {
		int di = random.nextInt(4);// 随机0-3
		Dir d = Dir.values()[di];
		Dir od = tank.getDir();
		if (od == d) {
			return getRandomDir(tank);
		}
		return d;
	}

	/**
	 * 键盘监听类
	 * 
	 * @author qinzhenwu
	 *
	 */
	class MyKeyListener extends KeyAdapter {

		/**
		 * 前进方向
		 */
		boolean is_u = false;
		boolean is_d = false;
		boolean is_l = false;
		boolean is_r = false;

		/**
		 * 键被按的时候调用
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
		 * 键弹起来的时候调用
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
			case KeyEvent.VK_CONTROL:// ctl键
				tank.fire();
				break;
			case KeyEvent.VK_SHIFT:// SHIFT键停止tank
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
			if (is_u || is_d || is_l || is_r) {// 按了方向键，进行移动
				tank.setMove(true);
			}

		}

	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public Tank findTankByUUID(UUID id) {
		 
		return tanks.get(id);
	}

	public void addTank(Tank t) {
		// TODO Auto-generated method stub
		
	}

 
	
}
