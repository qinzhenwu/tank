package my_tank.com.qin.main;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.manager.PropertyManager;
import my_tank.com.qin.model.Tank;
import my_tank.com.qin.utils.Audio;

public class MyTankMain {

	public static void main(String[] args) {
		TankFrame tankFrame = new TankFrame();
	
		//new Thread(()->new Audio("audio/war1.wav").loop()).start();
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
