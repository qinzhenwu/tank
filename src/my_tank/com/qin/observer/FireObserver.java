package my_tank.com.qin.observer;

import my_tank.com.qin.model.Tank;

/**
 * tank fire�۲���
 * @author qinzh
 *
 */
public class FireObserver implements Observer {

	@Override
	public void doAction(Event event) {
      Tank tank=(Tank) event.source;
      tank.fire();
	}

}
