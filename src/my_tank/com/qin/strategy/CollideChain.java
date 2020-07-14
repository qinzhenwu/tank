package my_tank.com.qin.strategy;

import java.util.LinkedList;
import java.util.List;

import my_tank.com.qin.model.GameObject;

/**
 * 责任链模式 <br>
 * 碰撞，责任链,每个碰撞判断都相当于碰撞链里的一个环节，如果摸个环节子弹或者tank消失掉，就没必要进行后续的判断了<br>
 * 此类也继承碰撞策略接口，可以方便多个chain拼接
 * 
 * @author qinzhenwu
 *
 */
public class CollideChain implements CollideStrategy {

	private List<CollideStrategy> collideStrategys = new LinkedList<>();// 所有的碰撞策略

	public CollideChain() {
		add(new BulletTankCollide()).add(new TankTankCollide());
	}

	/**
	 * 添加碰撞策略
	 * 
	 * @param collideStrategy
	 * @return
	 */
	public CollideChain add(CollideStrategy collideStrategy) {
		collideStrategys.add(collideStrategy);
		return this;
	}

	@Override
	public boolean collide(GameObject g1, GameObject g2) {

		for (int i = 0; i < collideStrategys.size(); i++) {
			CollideStrategy c = collideStrategys.get(i);
			if (!c.collide(g1, g2)) {// 如果有一个策略返回false，表示不再进行下边的策略
				return false;
			}
		}
		return true;
	}

}
