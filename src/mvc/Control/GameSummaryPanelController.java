package mvc.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mvc.View.GameSummaryPanel;

public class GameSummaryPanelController implements ActionListener{
	private GameSummaryPanel summaryPanel;
	public GameSummaryPanelController(GameSummaryPanel summaryPanel) {
		this.summaryPanel = summaryPanel;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		summaryPanel.addRows(); //a call to update the summary panel
		
	}

}
