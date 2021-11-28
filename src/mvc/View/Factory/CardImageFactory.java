package mvc.View.Factory;

import java.io.File;

import javax.swing.ImageIcon;

import model.interfaces.PlayingCard;


public class CardImageFactory {
	private final String FILE_PATH = String.format("cards%s", File.separator);
	
	public CardImageFactory() {
	}


	public ImageIcon getCardImage(PlayingCard card) {
		return new ImageIcon(
				String.format("%s%s_%s.png", FILE_PATH, card.getSuit().toString(), card.getValue().toString()));
	}
}
