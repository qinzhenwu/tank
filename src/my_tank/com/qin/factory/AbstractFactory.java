package my_tank.com.qin.factory;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.product.AbstractBullet;
import my_tank.com.qin.product.AbstractTank;

/**
 * ���󹤳����������Ҫ�����Ĳ�Ʒ��������Ĳ�Ʒ�ǳ���ģ�����Ĳ�Ʒ�ɾ���Ĳ�Ʒȥʵ��<br>
 * �˹����ﶨ���������Ʒ
 * 
 * @author qinzhenwu
 *
 */
public abstract class AbstractFactory {

	public abstract AbstractTank createTank(int x, int y, Dir dir, TankFrame tf);//����һ��tank����Ҫָ���������ز���

	
	public abstract AbstractBullet createBullet(AbstractTank tank,Dir dir);
}
