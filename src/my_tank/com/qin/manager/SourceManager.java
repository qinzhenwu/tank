package my_tank.com.qin.manager;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.imageio.plugins.common.ImageUtil;

import my_tank.com.qin.utils.ImageUtils;

public class SourceManager {
	//��֤���������޷���������̬���Ժ;�̬������еĴ��룬���������ɺ�ͻ�ִ��
	//ֻ��Ҫ��֤SourceManagerΪ��������
	private SourceManager() {
		
	}

	public static BufferedImage GoodTankUp, GoodTankDn, GoodTankLf, GoodTankRi;// ��ͬ�����tank
	public static BufferedImage BadTankUp, BadTankDn, BadTankLf, BadTankRi;// ��ͬ�����tank
	public static BufferedImage bulletUp, bulletDn, bulletLf, bulletRi;// ��ͬ������ӵ�

	public static BufferedImage[] explode = new BufferedImage[16];// ��ըͼƬ

	static {
		try {
			GoodTankUp = ImageIO.read(SourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			GoodTankDn = ImageUtils.rotateImage(GoodTankUp, 180);// ѡװͼƬ����
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
		} // �����ݼ��ص��ڴ�
	}

}
