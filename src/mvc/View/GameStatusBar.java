package mvc.View;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class GameStatusBar extends JPanel {
	private JLabel gameStatusLabel = new JLabel("");
	
	public GameStatusBar() {
		setBorder(new TitledBorder("Status Bar"));
		setLayout(new BorderLayout());
		add(gameStatusLabel);
	}
	//this method is called to set the text of the JLabel in the status bar 
	public void setText(String gameStatus) {
		gameStatusLabel.setText(gameStatus);
		this.validate();
		this.repaint();
	}
}
