package mvc.View;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class GameSummaryPanel extends JPanel {
	private GameEngine engine;
	private DefaultTableModel model = new DefaultTableModel();
	private int houseResult;
	private JTable table = new JTable(model);

	public GameSummaryPanel(GameEngine ge) {
		this.engine = ge;
		houseResult = 0;
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Points");
		model.addColumn("Bet");
		model.addColumn("Result");
		model.addColumn("Win/Loss");
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Summary Panel"));
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);
		add(new JScrollPane(table));
		table.setFillsViewportHeight(true);
		setVisible(true);

	}

	public void addRows() {
		int indexA;
		Collection<Player> players = engine.getAllPlayers();
		Player[] playersArr = players.toArray(new Player[players.size()]);

		model.setRowCount(0);
		model.setRowCount(engine.getAllPlayers().size());

		for (indexA = 0; indexA < engine.getAllPlayers().size(); indexA++) {
			model.insertRow(indexA,
					new Object[] { playersArr[indexA].getPlayerId(), playersArr[indexA].getPlayerName(),
							playersArr[indexA].getPoints(), playersArr[indexA].getBet(), playersArr[indexA].getResult(),
							getWinLoss(playersArr[indexA]) });
		}

	}
	// determines win/loss/draw of the player 
	public String getWinLoss(Player player) {
		if (houseResult == 0) {
			return ("Not Determined");
		} else if (player.getResult() > houseResult) {
			return ("WON");
		} else if (player.getResult() < houseResult) {
			return ("LOST");
		} else if (player.getResult() == houseResult) {
			return ("DRAW");
		}
		return "";
	}
	//sets the result of the house after house is dealt
	public void setHouseResult(int result) {
		this.houseResult = result;
	}

}
