package my_tank.com.qin.strategy;

import java.io.Serializable;

import my_tank.com.qin.model.Tank;

/**
 * ·¢Éä²ßÂÔ
 * 
 * @author qinzhenwu
 *
 */
public interface FireStrategy extends Serializable{

	void fire(Tank tank );
}
