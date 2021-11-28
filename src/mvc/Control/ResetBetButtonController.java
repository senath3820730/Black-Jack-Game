package mvc.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.View.GameFrame;
import mvc.View.GamePlayerSelector;

public class ResetBetButtonController implements ActionListener {
	private GamePlayerSelector selector;
	private GameEngine engine;
	private GameFrame parentFrame;
	private DisableButtonsController disableButtons;

	public ResetBetButtonController(GameFrame parentFrame, GamePlayerSelector selector, GameEngine engine,
			DisableButtonsController disableButtons) {
		this.parentFrame = parentFrame;
		this.selector = selector;
		this.engine = engine;
		this.disableButtons = disableButtons;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Collection<Player> players = engine.getAllPlayers();
		Player[] playersArr = players.toArray(new Player[players.size()]);
		/*first item in the JComboBox (selector) is house, therefore selector.getSelectedIndex() - 1 is used
		  to get the player from the collection corresponding to the selected player in the JComboBox
		 */
		Player selectedPlayer = playersArr[selector.getSelectedIndex() - 1];
		Object[] message = { String.format("Reset the bet of %s to 0?", selectedPlayer.getPlayerName()) };
		int option = JOptionPane.showConfirmDialog(null, message, "Bet For Player", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			selectedPlayer.resetBet();
			disableButtons.setEnabledResetBet(false);
			disableButtons.setEnabledDeal(false);
			//calls the action performed method in the summary panel controller to update the summary panel
			new GameSummaryPanelController(parentFrame.getGameSummaryPanel()).actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
			JOptionPane.showMessageDialog(null, "Bet reset to 0!");
		}

	}

}
