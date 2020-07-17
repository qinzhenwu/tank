package my_tank.com.qin.test;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WTTest {

	public static void main(String [] args) {
		Frame frame=new Frame();
		frame.setSize(600, 600);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
	}
	
}
