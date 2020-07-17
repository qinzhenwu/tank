package my_tank.com.qin.manager;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.imageio.plugins.common.ImageUtil;

import my_tank.com.qin.utils.ImageUtils;

public class SourceManager {
	//保证其他类中无法创建，静态属性和静态代码块中的代码，在类加载完成后就会执行
	//只需要保证SourceManager为单例即可
	private SourceManager() {
		
	}

	public static BufferedImage GoodTankUp, GoodTankDn, GoodTankLf, GoodTankRi;// 不同方向的tank
	public static BufferedImage BadTankUp, BadTankDn, BadTankLf, BadTankRi;// 不同方向的tank
	public static BufferedImage bulletUp, bulletDn, bulletLf, bulletRi;// 不同方向的子弹

	public static BufferedImage[] explode = new BufferedImage[16];// 爆炸图片

	static {
		try {
			GoodTankUp = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			GoodTankDn = ImageUtils.rotateImage(GoodTankUp, 180);// 选装图片方向
			GoodTankLf = ImageUtils.rotateImage(GoodTankUp, -90);
			GoodTankRi = ImageUtils.rotateImage(GoodTankUp, 90);

			BadTankUp = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			BadTankDn = ImageUtils.rotateImage(BadTankUp, 180);
			BadTankLf = ImageUtils.rotateImage(BadTankUp, -90);
			BadTankRi = ImageUtils.rotateImage(BadTankUp, 90);

			bulletUp = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletDn = ImageUtils.rotateImage(bulletUp, 180);
			bulletLf = ImageUtils.rotateImage(bulletUp, -90);
			bulletRi = ImageUtils.rotateImage(bulletUp, 90);
			for (int i = 0; i < 16; i++) {
				explode[i] = ImageIO
						.read(SourceManager.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 将数据加载到内存
	}

}
