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

import my_tank.com.qin.model.Bullet;
import my_tank.com.qin.model.Explode;
import my_tank.com.qin.model.GameModel;
import my_tank.com.qin.model.Tank;

public class TankFrame extends Frame {

	
	public static int WIDTH = 800, HEIGHT = 800;



	GameModel gameModel=new GameModel();
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
		gameModel.paint(g);
	}

	
//
//	/**
//	 * 获取到随机方向，不能与原来的方向一致
//	 * 
//	 * @param tank
//	 * @return
//	 */
//	public Dir getRandomDir(Tank tank) {
//		int di = random.nextInt(4);// 随机0-3
//		Dir d = Dir.values()[di];
//		Dir od = tank.getDir();
//		if (od == d) {
//			return getRandomDir(tank);
//		}
//		return d;
//	}

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
				gameModel.tank.fire();
				break;
			case KeyEvent.VK_SHIFT:// SHIFT键停止tank
				gameModel.tank.setMove(false);
				break;
			default:
				break;
			}
			setDir();
		}

		private void setDir() {
			if (is_u) {
				gameModel.tank.setDir(Dir.UP);
			}
			if (is_d) {
				gameModel.tank.setDir(Dir.DOWN);
			}
			if (is_l) {
				gameModel.tank.setDir(Dir.LEFT);
			}
			if (is_r) {
				gameModel.tank.setDir(Dir.RIGHT);
			}
			if (is_u || is_d || is_l || is_r) {// 按了方向键，进行移动
				gameModel.tank.setMove(true);
			}

		}

	}

}
