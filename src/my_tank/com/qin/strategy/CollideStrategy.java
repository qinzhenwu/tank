package my_tank.com.qin.strategy;

import my_tank.com.qin.model.GameObject;

/**
 * 碰撞接口
 * 
 * @author qinzhenwu
 *
 */
public interface CollideStrategy {

	/**
	 * 碰撞方法
	 * 
	 * @param g1
	 * @param g2
	 * @return
	 */
	boolean collide(GameObject g1, GameObject g2);
}
