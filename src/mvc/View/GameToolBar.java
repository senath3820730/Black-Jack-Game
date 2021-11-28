package mvc.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import model.interfaces.GameEngine;
import mvc.Control.AddPlayerButtonController;
import mvc.Control.BetButtonController;
import mvc.Control.DealButtonController;
import mvc.Control.DisableButtonsController;
import mvc.Control.GamePlayerSelectorController;
import mvc.Control.RemovePlayerButtonController;
import mvc.Control.ResetBetButtonController;

@SuppressWarnings("serial")
public class GameToolBar extends JToolBar {
	private GamePlayerSelector selector;

	public GameToolBar(GameFrame frame, GameEngine engine) {
		setBorder(new TitledBorder("Tool Bar"));
		setLayout(new BorderLayout());

		AbstractButton addPlayerButton = new JButton("Add Player");
		AbstractButton removePlayerButton = new JButton("Remove Player");
		AbstractButton betButton = new JButton("Bet");
		AbstractButton resetBetButton = new JButton("Reset Bet");
		AbstractButton dealButton = new JButton("Deal");
		selector = new GamePlayerSelector(engine);
		DisableButtonsController disableButtons = new DisableButtonsController(addPlayerButton, removePlayerButton,
				betButton, resetBetButton, dealButton, engine);
		JPanel panel = new JPanel(new GridLayout(1, 6));
		removePlayerButton.addActionListener(new RemovePlayerButtonController(frame, engine, selector, disableButtons));
		addPlayerButton.addActionListener(new AddPlayerButtonController(frame, engine, selector));
		betButton.addActionListener(new BetButtonController(frame, selector, disableButtons, engine));
		dealButton.addActionListener(new DealButtonController(frame, selector, disableButtons, engine));
		resetBetButton.addActionListener(new ResetBetButtonController(frame, selector, engine, disableButtons));
		selector.addActionListener(new GamePlayerSelectorController(frame, selector, engine, disableButtons));
		panel.add(addPlayerButton);
		panel.add(removePlayerButton);
		panel.add(betButton);
		panel.add(resetBetButton);
		panel.add(dealButton);
		panel.add(selector);
		//initially disables all buttons except for add player, as house is the only item currently 
		disableButtons.setEnabledRemove(false);
		disableButtons.setEnabledBet(false);
		disableButtons.setEnabledDeal(false);
		disableButtons.setEnabledResetBet(false);
		disableButtons.setEnabledRemove(false);
		add(panel);
		panel.setVisible(true);

	}

	public GamePlayerSelector getPlayerSelector() {
		return selector;
	}

	
}
