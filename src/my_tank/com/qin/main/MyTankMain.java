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
				Thread.sleep(50);// ÿ��50�������Դ��ڣ�����repaint���������Զ�����paint����

				tankFrame.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
