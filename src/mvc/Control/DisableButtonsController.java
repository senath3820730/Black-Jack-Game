package mvc.Control;

import javax.swing.AbstractButton;

import model.interfaces.GameEngine;
/* this is a helper class, where all the JButtons of the tool bar is passed into the constructor
   and the buttons can be enabled/disabled by calling methods from this class*/
public class DisableButtonsController {
	private AbstractButton addPlayer, removePlayer, bet, resetBet, deal;
	private GameEngine engine;

	public DisableButtonsController(AbstractButton addPlayer, AbstractButton removePlayer, AbstractButton bet,
			AbstractButton resetBet, AbstractButton deal, GameEngine engine) {
		this.addPlayer = addPlayer;
		this.removePlayer = removePlayer;
		this.bet = bet;
		this.resetBet = resetBet;
		this.deal = deal;
		this.engine = engine;
	}

	public void removePlayerButton() {
		if (engine.getAllPlayers().isEmpty()) { //if the collection of players is empty, only the house is left
			removePlayer.setEnabled(false);
		} else {
			removePlayer.setEnabled(true);
		}
	}

	public void setEnabledAdd(boolean result) {
		addPlayer.setEnabled(result);
	}

	public void setEnabledRemove(boolean result) {
		removePlayer.setEnabled(result);
	}

	public void setEnabledBet(boolean result) {
		bet.setEnabled(result);
	}

	public void setEnabledResetBet(boolean result) {
		resetBet.setEnabled(result);
	}

	public void setEnabledDeal(boolean result) {
		deal.setEnabled(result);
	}
}
