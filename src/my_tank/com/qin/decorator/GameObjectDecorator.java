package my_tank.com.qin.decorator;

import my_tank.com.qin.model.GameObject;

/**
 * ³éÏóµÄÐÞÊÎÆ÷
 * 
 * @author qinzhenwu
 *
 */
public abstract class GameObjectDecorator extends GameObject {

	protected GameObject gameObject;

	public GameObjectDecorator(GameObject g) {
		this.gameObject = g;
	}
}
