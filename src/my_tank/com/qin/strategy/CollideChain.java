package my_tank.com.qin.strategy;

import java.util.LinkedList;
import java.util.List;

import my_tank.com.qin.model.GameObject;

/**
 * ������ģʽ <br>
 * ��ײ��������,ÿ����ײ�ж϶��൱����ײ�����һ�����ڣ�������������ӵ�����tank��ʧ������û��Ҫ���к������ж���<br>
 * ����Ҳ�̳���ײ���Խӿڣ����Է�����chainƴ��
 * 
 * @author qinzhenwu
 *
 */
public class CollideChain implements CollideStrategy {

	private List<CollideStrategy> collideStrategys = new LinkedList<>();// ���е���ײ����

	public CollideChain() {
		add(new BulletTankCollide()).add(new TankTankCollide());
	}

	/**
	 * �����ײ����
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
			if (!c.collide(g1, g2)) {// �����һ�����Է���false����ʾ���ٽ����±ߵĲ���
				return false;
			}
		}
		return true;
	}

}
