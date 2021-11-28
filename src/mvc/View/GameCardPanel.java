package mvc.View;

import java.awt.Color;

import java.awt.GridLayout;
import java.util.HashMap;

import java.util.Map.Entry;

import javax.swing.BorderFactory;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class GameCardPanel extends JPanel {
	private int count = 0;
	private HashMap<String, JLabel> playerCardsMap;

	public GameCardPanel() {
		super(new GridLayout(1, 6));
		setBorder(new TitledBorder("CardPanel"));
		playerCardsMap = new HashMap<String, JLabel>();

	}

	public void addCardImage(JLabel card, Player player) {
		card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(card);
		count++;
		/*puts the playerID and a blank space to identify the position at which the playerID ends
		  a unique integer is also concatenated in order to make each key unique*/
		playerCardsMap.put(player.getPlayerId() + " " + Integer.toString(count), card);

	}

	public void repaintPanel() {
		removeAll();
		validate();
		repaint();
	}

	public void addHouseCards(JLabel card) {
		card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(card);
		count++;
		playerCardsMap.put("house " + Integer.toString(count), card);
	}
	// method to show already dealt house cards 
	public void showDealtHouseCards() {
		removeAll();
		validate();
		repaint();
		for (Entry<String, JLabel> set : playerCardsMap.entrySet()) {
			if (set.getKey().length() > 5) { 
				if (set.getKey().substring(0, 5).equals("house")) {
					add(set.getValue());
				}
			}
		}
	}
	// method to show already dealt player cards 
	public void showDealtCards(Player player) {
		removeAll();
		validate();
		repaint();
		int endIndex = 0;
		for (Entry<String, JLabel> set : playerCardsMap.entrySet()) {
			endIndex = set.getKey().indexOf(" ");
			//extracts the playerID and compares it to the current player to display cards
			if (set.getKey().substring(0, endIndex).equals(player.getPlayerId())) {
				add(set.getValue());
			}
		}
	}

	public void emptyPlayerCardsMap() {
		this.playerCardsMap.clear();
		;
	}

}
