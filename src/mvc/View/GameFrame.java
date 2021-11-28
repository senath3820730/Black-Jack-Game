package mvc.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Toolkit;


import javax.swing.JFrame;
import javax.swing.JPanel;

import model.interfaces.GameEngine;



@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private GameStatusBar statusBar;
	private GameSummaryPanel summaryPanel;
	private GameCardPanel cardPanel;
	private GameToolBar toolBar;
	private boolean result;
	
	public GameFrame(GameEngine engine) {
		super("CardGameGUI");
		result = false; //sets the result of dealt all players to false initially
		statusBar = new GameStatusBar();
		summaryPanel = new GameSummaryPanel(engine);
		toolBar = new GameToolBar(this, engine);
		cardPanel = new GameCardPanel();
		GameMenuBar menuBar = new GameMenuBar();
		// sets the minimum size of the frame
		Dimension minimumSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int width = (int) minimumSize.getWidth();
	    int height =  (int) minimumSize.getHeight();
	    setMinimumSize(new Dimension(width/2,height/2));
		setBounds(100, 100, 800, 600);
		
		summaryPanel.setPreferredSize(new Dimension(this.getWidth(), 120));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel innerPanel = new JPanel(new BorderLayout());
		JPanel firstPanel = new JPanel(new BorderLayout());

		innerPanel.add(cardPanel, BorderLayout.CENTER);
		innerPanel.add(summaryPanel, BorderLayout.NORTH);
		innerPanel.add(statusBar, BorderLayout.SOUTH);

		outerPanel.add(toolBar, BorderLayout.NORTH);
		//ads the inner panel to the outer panel
		outerPanel.add(innerPanel, BorderLayout.CENTER);
		firstPanel.add(menuBar, BorderLayout.NORTH);
		//adds the outer panel to the first panel
		firstPanel.add(outerPanel, BorderLayout.CENTER);
		add(firstPanel);

		setVisible(true);
	}

	public GameStatusBar getGameStatusBar() {
		return this.statusBar;
	}

	public GameSummaryPanel getGameSummaryPanel() {
		return this.summaryPanel;
	}

	public GameCardPanel getGameCardPanel() {
		return this.cardPanel;
	}

	public GameToolBar getGameToolBar() {
		return this.toolBar;
	}

	public void setDealtAllPlayers(boolean result) {
		this.result = result;
	}

	public boolean getDealtAllPlayers() {
		return result;
	}

	

}
