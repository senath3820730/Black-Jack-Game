package mvc.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;


import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.View.GameFrame;
import mvc.View.GamePlayerSelector;

public class RemovePlayerButtonController implements ActionListener {
	private GameEngine engine;
	private GameFrame parentFrame;
	private GamePlayerSelector selector;
	private DisableButtonsController disableButtons;

	public RemovePlayerButtonController(GameFrame frame, GameEngine engine, GamePlayerSelector selector, DisableButtonsController disableButtons) {
		this.engine = engine;
		this.parentFrame = frame;
		this.selector = selector;
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
		Object[] message = { " Remove " + selectedPlayer.getPlayerName() + " from game?", };
		int option = JOptionPane.showConfirmDialog(null, message, "Remove Player", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			engine.removePlayer(engine.getPlayer(selectedPlayer.getPlayerId()));
			selector.addItems();//updates the JComboBox
			//calls the action performed method in the summary panel controller to update the summary panel
			new GameSummaryPanelController(parentFrame.getGameSummaryPanel()).actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
			disableButtons.removePlayerButton();
			JOptionPane.showMessageDialog(null, "Player Removed!");
		}
	}
}
