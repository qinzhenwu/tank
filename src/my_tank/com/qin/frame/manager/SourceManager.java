package my_tank.com.qin.frame.manager;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SourceManager {

	public static BufferedImage tankUp, tankDn, tankLf, tankRi;// 不同方向的tank
	public static BufferedImage bulletUp, bulletDn, bulletLf, bulletRi;// 不同方向的子弹

	public static BufferedImage[] explode = new BufferedImage[16];// 爆炸图片

	static {
		try {
			tankUp = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
			tankDn = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
			tankLf = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
			tankRi = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/tankR.gif"));

			bulletUp = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
			bulletDn = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			bulletLf = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
			bulletRi = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
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
