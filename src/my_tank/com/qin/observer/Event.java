package my_tank.com.qin.observer;

import my_tank.com.qin.model.GameObject;

/**
 * 事件对象
 * 
 * @author qinzh
 *
 */
public abstract class Event {

	/**
	 * 持有的被观察的事件对象
	 */
	protected GameObject source;
}
