package mvc.app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import mvc.View.GameEngineCallbackGUI;
import mvc.View.GameFrame;
import view.GameEngineCallbackImpl;

public class MVCTest {
	public static void main(String[] args) {
		final GameEngine gameEngine = new GameEngineImpl();
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
		} catch (Exception e) {
		}
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				GameFrame frame = new GameFrame(gameEngine);
				gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(frame));
				gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
			}
		});
	}
}
