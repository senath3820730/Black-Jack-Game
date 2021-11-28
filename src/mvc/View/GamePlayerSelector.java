package mvc.View;


import java.util.Collection;


import javax.swing.JComboBox;


import model.interfaces.GameEngine;
import model.interfaces.Player;


public class GamePlayerSelector extends JComboBox {
	private GameEngine engine;

	public GamePlayerSelector(GameEngine engine) {
		this.engine = engine;
		this.addItem("House");

	}
	// this method removes all the items of the JComboBox and adds the players in the game engine collection 
	public void addItems() {
		this.removeAllItems();
		this.addItem("House");
		
		Collection<Player> players = engine.getAllPlayers();
		for (Player player : players) {
			this.addItem(player.getPlayerName());
		}
		
	}


}
