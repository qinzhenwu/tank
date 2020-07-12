package my_tank.com.qin.factory;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.product.AbstractBullet;
import my_tank.com.qin.product.AbstractTank;

/**
 * 抽象工厂，抽象出需要创建的产品对象，这里的产品是抽象的，具体的产品由具体的产品去实现<br>
 * 此工厂里定义多个抽象产品
 * 
 * @author qinzhenwu
 *
 */
public abstract class AbstractFactory {

	public abstract AbstractTank createTank(int x, int y, Dir dir, TankFrame tf);//创建一个tank，需要指定坐标等相关参数

	
	public abstract AbstractBullet createBullet(AbstractTank tank,Dir dir);
}
