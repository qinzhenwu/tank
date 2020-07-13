package my_tank.com.qin.factory;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.product.BaseBullet;
import my_tank.com.qin.product.BaseExplode;
import my_tank.com.qin.product.BaseTank;

/**
 * ���󹤳����������Ҫ�����Ĳ�Ʒ��������Ĳ�Ʒ�ǳ���ģ�����Ĳ�Ʒ�ɾ���Ĳ�Ʒȥʵ��<br>
 * �˹����ﶨ���������Ʒ
 * 
 * @author qinzhenwu
 *
 */
public abstract class GameFactory {

	public abstract BaseTank createTank(int x, int y, Dir dir,TankGroup group, TankFrame tf);//����һ��tank����Ҫָ���������ز���

	
	public abstract BaseBullet createBullet(BaseTank tank,Dir dir);

	
	public abstract BaseExplode createExplode(BaseTank tank);
}
