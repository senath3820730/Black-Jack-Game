package mvc.View;

import java.util.Collection;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private GameFrame frame;

	public GameEngineCallbackGUI(GameFrame frame) {
		this.frame = frame;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getGameStatusBar().setText(String.format("Card dealt to %s .. %s, Score: %d",
						player.getPlayerName(), card.toString(), card.getScore()));
				//passes the current card and the player to create the JLabels
				new CardImageView(frame, card, player); 
				
			}
		});
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getGameStatusBar().setText(String.format("%s, final result=%d", player.getPlayerName(), result));
			}
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getGameStatusBar().setText(String.format("Card dealt to %s .. %s, Score: %d ... YOU BUSTED !",
						player.getPlayerName(), card.toString(), card.getScore()));
				new CardImageView(frame, card, player);
			}
		});
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getGameStatusBar().setText(String.format(
						String.format("Card dealt to House .. %s, Score: %d", card.toString(), card.getScore())));
				//player is set to null as house deals
				new CardImageView(frame, card, null);
			}
		});
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getGameStatusBar().setText(String.format("Card dealt to House .. %s, Score: %d ... YOU BUSTED !",
						card.toString(), card.getScore()));
				new CardImageView(frame, card, null);
			}
		});
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				String playerRes = "";
				Collection<Player> players = engine.getAllPlayers();
				String houseFinal = String.format("House, final result=%d", result);
				//passes the house result to the summary panel to determine win/loss/draw
				frame.getGameSummaryPanel().setHouseResult(result);
				//updates the summary panel
				frame.getGameSummaryPanel().addRows();
				for (Player player : players) {
					playerRes = playerRes + "\n" + player.toString();
				}
				String results = String.format("Final Player Results %s", playerRes);
				//concatenate the house and final result to show in the status bar
				frame.getGameStatusBar().setText(houseFinal + " " + results);
			}
		});

	}

}
