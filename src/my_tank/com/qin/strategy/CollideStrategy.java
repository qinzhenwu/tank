package my_tank.com.qin.strategy;

import my_tank.com.qin.model.GameObject;

/**
 * ��ײ�ӿ�
 * 
 * @author qinzhenwu
 *
 */
public interface CollideStrategy {

	/**
	 * ��ײ����
	 * 
	 * @param g1
	 * @param g2
	 * @return
	 */
	boolean collide(GameObject g1, GameObject g2);
}
