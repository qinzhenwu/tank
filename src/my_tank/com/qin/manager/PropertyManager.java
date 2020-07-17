package my_tank.com.qin.manager;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件管理类，单例实现
 * 
 * @author qinzhenwu
 *
 */
public class PropertyManager {

	private PropertyManager() {
		 
	}

	private static Properties properties = null;

	private static class NC {
		private static final Properties p = new Properties();

		static {
			try {
				System.out.println("222");
				p.load(PropertyManager.class.getClassLoader().getResourceAsStream("properties/properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static Object getV(String key) {
		properties = NC.p;

		return properties.get(key);
	}

	public static void main(String[] arg) {
		for (int i = 0; i < 3; i++) {
			new Thread(() -> {
				
				System.out.println(PropertyManager.getV("initTanksNum"));
			}).start();

		}

	}

}
