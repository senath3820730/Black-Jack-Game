package mvc.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import mvc.View.GameFrame;
import mvc.View.GamePlayerSelector;
import mvc.ViewModel.GamePlayerState;

public class GamePlayerSelectorController implements ActionListener {
	private GamePlayerSelector selector;
	private GameEngine engine;
	private GameFrame frame;
	private DisableButtonsController disableButtons;
	private GamePlayerState playerStatus;

	public GamePlayerSelectorController(GameFrame frame, GamePlayerSelector selector, GameEngine engine,
			DisableButtonsController disableButtons) {
		this.selector = selector;
		this.frame = frame;
		this.engine = engine;
		this.disableButtons = disableButtons;
		playerStatus = new GamePlayerState(frame, engine, disableButtons);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Collection<Player> players = engine.getAllPlayers();
		Player[] playersArr = players.toArray(new Player[players.size()]);
		if (selector.getItemCount() != 0) {       //if the JComboBox hasn't been populated yet
		if (selector.getSelectedIndex() == 0) { //if house is selected (house is the first item to select in the JComboBox)
			disableButtons.setEnabledBet(false);
			disableButtons.setEnabledDeal(false);
			disableButtons.setEnabledResetBet(false);
			disableButtons.setEnabledRemove(false);
			frame.getGameStatusBar().setText("House Selected"); //status bar displays the currently selected
		}
		if (selector.getSelectedIndex() != 0) { //if a player has been selected 
			/*first item in the JComboBox (selector) is house, therefore selector.getSelectedIndex() - 1 is used
			  to get the player from the collection corresponding to the selected player in the JComboBox*/
			Player selectedPlayer = playersArr[selector.getSelectedIndex() - 1];
			frame.getGameStatusBar().setText(selectedPlayer.getPlayerName() + " selected");
			playerStatus.hasBet(selectedPlayer); //disables/enables JButtons depending on if the player has bet
			playerStatus.hasDealt(selectedPlayer);//disables/enables JButtons depending on if the player has dealt
			if (selectedPlayer.getResult() == 0) { 
				frame.getGameCardPanel().repaintPanel();
			} else {
				//if the player has dealt, the cards of the player are shown
				frame.getGameCardPanel().showDealtCards(selectedPlayer);
			}
		} else if (frame.getDealtAllPlayers() == true) {
			//if the house has dealt, the cards of the house are shown
			frame.getGameCardPanel().showDealtHouseCards();
		} else {
			frame.getGameCardPanel().repaintPanel();
		}
		}
	}

}
