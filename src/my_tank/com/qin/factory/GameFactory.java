package my_tank.com.qin.factory;

import my_tank.com.qin.frame.Dir;
import my_tank.com.qin.frame.TankFrame;
import my_tank.com.qin.frame.TankGroup;
import my_tank.com.qin.product.BaseBullet;
import my_tank.com.qin.product.BaseExplode;
import my_tank.com.qin.product.BaseTank;

/**
 * 抽象工厂，抽象出需要创建的产品对象，这里的产品是抽象的，具体的产品由具体的产品去实现<br>
 * 此工厂里定义多个抽象产品
 * 
 * @author qinzhenwu
 *
 */
public abstract class GameFactory {

	public abstract BaseTank createTank(int x, int y, Dir dir,TankGroup group, TankFrame tf);//创建一个tank，需要指定坐标等相关参数

	
	public abstract BaseBullet createBullet(BaseTank tank,Dir dir);

	
	public abstract BaseExplode createExplode(BaseTank tank);
}
