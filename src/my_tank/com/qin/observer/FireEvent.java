package my_tank.com.qin.observer;

import my_tank.com.qin.model.GameObject;

/**
 * fireÊÂ¼ş
 * 
 * @author qinzh
 *
 */
public class FireEvent extends Event {

	public FireEvent(GameObject g) {
		this.source=g;
	}
}
