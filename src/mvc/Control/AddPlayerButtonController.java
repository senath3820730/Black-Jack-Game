package mvc.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import mvc.View.GameFrame;
import mvc.View.GamePlayerSelector;

public class AddPlayerButtonController implements ActionListener {

	private GameEngine engine;
	private GameFrame parentFrame;
	private GamePlayerSelector selector;

	public AddPlayerButtonController(GameFrame frame, GameEngine engine, GamePlayerSelector selector) {
		this.engine = engine;
		this.parentFrame = frame;
		this.selector = selector;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField playerID = new JTextField();
		JTextField playerName = new JTextField();
		JTextField inPoints = new JTextField();
		Object[] message = { "Enter PlayerID      :", playerID, "Enter Player Name   :", playerName,
				"Enter Initial Points:", inPoints };
		
		int option = JOptionPane.showConfirmDialog(null, message, "Add Player", JOptionPane.OK_CANCEL_OPTION);
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(playerID.getText());
		boolean found = matcher.find(); // checks for any existing white spaces in the playerID field
		
		if (option == JOptionPane.OK_OPTION && (playerID.getText().equals("") || playerName.getText().equals("")
				|| inPoints.getText().equals("") || found ||  playerName.getText().trim().isEmpty())) {
			
			JOptionPane.showMessageDialog(null, "Please enter valid player information", "Invalid Player Info", JOptionPane.ERROR_MESSAGE);
			
		} else if (option == JOptionPane.OK_OPTION && (!playerID.getText().equals("")
				|| !playerName.getText().equals("") || !inPoints.getText().equals(""))) {
			
			try {
				engine.addPlayer(new SimplePlayer(playerID.getText(), playerName.getText(), Integer.parseInt(inPoints.getText())));
				playerID.setText(null);     // sets the fields to null, after a player is added
				playerName.setText(null);
				inPoints.setText(null);
				//calls the action performed method in the summary panel controller to update the summary panel
				new GameSummaryPanelController(parentFrame.getGameSummaryPanel()).actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
				selector.addItems();
				JOptionPane.showMessageDialog(null, "Player Added!");

			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(null, "Please enter valid player information", "Invalid Player Info", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
