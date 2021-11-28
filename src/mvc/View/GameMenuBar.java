package mvc.View;

import java.awt.event.KeyEvent;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import mvc.Control.ExitMenuItemController;

@SuppressWarnings("serial")
public class GameMenuBar extends JMenuBar {
	private JMenu menu;

	public GameMenuBar() {
		menu = new JMenu("GameMenu");
		menu.setMnemonic(KeyEvent.VK_F);
		add(menu);
		JMenuItem exitItem = new JMenuItem("Exit Game", KeyEvent.VK_E);
		exitItem.addActionListener(new ExitMenuItemController());
		menu.addSeparator();
		menu.add(exitItem);

	}

}
