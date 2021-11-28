package mvc.View;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;




import model.interfaces.Player;
import model.interfaces.PlayingCard;

import mvc.View.Factory.CardImageFactory;


public class CardImageView {
	private CardImageFactory factory;
	public CardImageView(GameFrame frame, PlayingCard card, Player player) {
		factory = new CardImageFactory();
		ImageIcon cardIcon = factory.getCardImage(card);
		// creates a scaled instance of the card images to fit the card panel
		Image cardImage = cardIcon.getImage().getScaledInstance(cardIcon.getIconWidth()/4,cardIcon.getIconHeight()/4,Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(cardImage); 
		JLabel cardLabel = new JLabel();
		cardLabel.setHorizontalAlignment(JLabel.CENTER);
		cardLabel.setVerticalAlignment(JLabel.CENTER);
		cardLabel.setIcon(icon);
		if (player != null) { //if no player (null) was passed into this constructor, this means cards are being dealt to the house
		frame.getGameCardPanel().addCardImage(cardLabel,player);
		}else {
			frame.getGameCardPanel().addHouseCards(cardLabel);
		}
	}

}
