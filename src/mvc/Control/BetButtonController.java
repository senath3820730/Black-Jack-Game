package mvc.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.View.GameFrame;
import mvc.View.GamePlayerSelector;

public class BetButtonController implements ActionListener {
	private GamePlayerSelector selector;
	private DisableButtonsController disableButtons;
	private GameEngine engine;
	private GameFrame parentFrame;

	public BetButtonController(GameFrame parentFrame, GamePlayerSelector selector, DisableButtonsController disableButtons, GameEngine engine) {
		this.parentFrame = parentFrame;
		this.selector = selector;
		this.disableButtons = disableButtons;
		this.engine = engine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Collection<Player> players = engine.getAllPlayers();
		Player[] playersArr = players.toArray(new Player[players.size()]);
		/*first item in the JComboBox (selector) is house, therefore selector.getSelectedIndex() - 1 is used
		  to get the player from the collection corresponding to the selected player in the JComboBox
		 */
		Player selectedPlayer = playersArr[selector.getSelectedIndex() - 1];
		JTextField bet = new JTextField();
		Object[] message = { "Enter Amount to Bet:", bet };
		int option = JOptionPane.showConfirmDialog(null, message, "Bet For Player", JOptionPane.OK_CANCEL_OPTION);
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(bet.getText());
		boolean found = matcher.find(); // checks for any existing white spaces 
		try {
			if (option == JOptionPane.OK_OPTION && (bet.getText().equals("") || found == true
					|| selectedPlayer.getPoints() < Integer.parseInt(bet.getText())
					|| Integer.parseInt(bet.getText()) <= 0)) {
				
				JOptionPane.showMessageDialog(null, "Please enter a valid bet", "Invalid Bet", JOptionPane.ERROR_MESSAGE);
			} else if (option == JOptionPane.OK_OPTION && !bet.getText().equals("")) {
				engine.placeBet(selectedPlayer, Integer.parseInt(bet.getText()));
				disableButtons.setEnabledDeal(true); //enables the deal button 
				disableButtons.setEnabledResetBet(true); //enables the resetBetbutton
				bet.setText(null); //the bet text field is set to null after bet is placed
				//calls the action performed method in the summary panel controller to update the summary panel
				new GameSummaryPanelController(parentFrame.getGameSummaryPanel()).actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
				JOptionPane.showMessageDialog(null, "Bet Placed!");
			}
		} catch (NumberFormatException exception) {
			JOptionPane.showMessageDialog(null, "Please enter a valid bet", "Invalid Bet", JOptionPane.ERROR_MESSAGE);
		}

	}
}
