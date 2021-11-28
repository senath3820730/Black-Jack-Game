package mvc.ViewModel;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.Control.DisableButtonsController;
import mvc.View.GameFrame;

public class GamePlayerState {
	private DisableButtonsController disableButtons;
	private GameEngine engine;
	private GameFrame frame;

	public GamePlayerState(GameFrame frame, GameEngine engine, DisableButtonsController disableButtons) {
		this.disableButtons = disableButtons;
		this.engine = engine;
		this.frame = frame;
	}
	
	public void hasBet(Player player) {
		// if the player placed a bet, the relevant buttons are enabled/disabled
		if (player.getBet() > 0) {
			disableButtons.setEnabledDeal(true);
			disableButtons.setEnabledResetBet(true);
		} else {
			disableButtons.setEnabledDeal(false);
			disableButtons.setEnabledResetBet(false);
		}
	}

	public void hasDealt(Player player) {
		if (player.getResult() > 0) {
			// if the player has dealt, the relevant buttons are enabled/disabled
			disableButtons.setEnabledBet(false);
			disableButtons.setEnabledResetBet(false);
			disableButtons.setEnabledRemove(false);
			disableButtons.setEnabledDeal(false);
		} else {
			disableButtons.setEnabledRemove(true);
			disableButtons.setEnabledBet(true);
		}

	}

	public boolean hasDealtAllPlayers() {
		for (Player player : engine.getAllPlayers()) {
			if (player.getResult() == 0) {
				return false;
			}
		}
		return true;
	}
	// resets the bets and results of all the players 
	public void newRound() {
		for (Player player : engine.getAllPlayers()) {
			player.resetBet();
			player.setResult(0);
			if (player.getPoints() == 0) {
				engine.removePlayer(player);
				JOptionPane.showMessageDialog(null, "The player, "+ player.getPlayerName() +" was disqualified due to insufficient points.", "Player Disqualified",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		frame.setDealtAllPlayers(false);
		frame.getGameCardPanel().emptyPlayerCardsMap();
		frame.getGameSummaryPanel().setHouseResult(0);
		//selects the house for the start of the new round 
		frame.getGameToolBar().getPlayerSelector().setSelectedIndex(0);
		//updates the summary panel
		frame.getGameSummaryPanel().addRows();
		//updates the JComboBox
		frame.getGameToolBar().getPlayerSelector().addItems();
	}

}
