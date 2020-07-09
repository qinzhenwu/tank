package my_tank.com.qin.main;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.model.Tank;

public class MyTankMain {

	public static void main(String[] args) {
		TankFrame tankFrame = new TankFrame();
		for (int i = 0; i < 5; i++) {
			int x = 150 + (i * 80);
			int y = 100;
			Tank enTank = new Tank(x, y, Dir.DOWN, tankFrame, TankGroup.BLUE);
			tankFrame.enemyTanks.add(enTank);
		}

		while (true) {
			try {
				Thread.sleep(50);// 每隔50毫秒属性窗口，调用repaint方法，会自动调用paint方法

				tankFrame.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
