package mvc.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.View.GameFrame;
import mvc.View.GamePlayerSelector;
import mvc.ViewModel.GamePlayerState;

public class DealButtonController implements ActionListener{
	private GamePlayerSelector selector;
	private DisableButtonsController disableButtons;
	private GameEngine engine;
	
	GameFrame parentFrame;
	private GamePlayerState playerStatus;
	public DealButtonController(GameFrame parentFrame, GamePlayerSelector selector, DisableButtonsController disableButtons, GameEngine engine) {
		this.selector = selector;
		this.disableButtons = disableButtons;
		this.parentFrame = parentFrame;
		this.engine = engine;
		this.playerStatus = new GamePlayerState(parentFrame ,engine,disableButtons);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Collection<Player> players = engine.getAllPlayers();
		Player[] playersArr = players.toArray(new Player[players.size()]);
		/*first item in the JComboBox (selector) is house, therefore selector.getSelectedIndex() - 1 is used
		 * to get the player from the collection corresponding to the selected player in the JComboBox
		 */
		Player selectedPlayer= playersArr[selector.getSelectedIndex()-1];
		new Thread()
		{
		@Override
		public void run()
		{	
			//disables all necessary buttons and the JComboBox while player is being dealt 
			disableButtons.setEnabledBet(false);
			disableButtons.setEnabledResetBet(false);
			disableButtons.setEnabledDeal(false);
			disableButtons.setEnabledRemove(false);
			disableButtons.setEnabledAdd(false);
			selector.setEnabled(false);
			engine.dealPlayer(selectedPlayer, 100);
			//enables the necessary buttons and the JComboBox after deal  
			disableButtons.setEnabledAdd(true);
			selector.setEnabled(true);
			//calls the action performed method in the summary panel controller to update the summary panel
			new GameSummaryPanelController(parentFrame.getGameSummaryPanel()).actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
			if (playerStatus.hasDealtAllPlayers() == true) {
				int option = JOptionPane.showConfirmDialog(null, "House Ready, wish to continue?", "House ready", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					parentFrame.setDealtAllPlayers(true);
					selector.setSelectedIndex(0); //sets the JComboBox to the 'house' item in order to display the cards
					//disables all necessary buttons and the JComboBox while the house is being dealt 
					disableButtons.setEnabledAdd(false);
					selector.setEnabled(false);
					engine.dealHouse(100);
					//enables the necessary buttons and the JComboBox after deal  
					disableButtons.setEnabledAdd(true);
					selector.setEnabled(true);
					int option2 = JOptionPane.showConfirmDialog(null, "Wish to play another round?", "New Round", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (option2 == JOptionPane.YES_OPTION) {
						playerStatus.newRound(); //calls a method that commences a new round
					}else {
						disableButtons.setEnabledAdd(false); //as a new round is not played, more players will not be added
					}
				}
			}
		}
		}.start();
		
		
	}
	
}
