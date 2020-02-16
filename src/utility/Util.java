package utility;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Util {

	public static JPanel wrap(JComponent... components) {
		JPanel panel = new JPanel();
		for (JComponent jComponent : components) {
			panel.add(jComponent);
		}
		return panel;
	}

	public static void sleep(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
