package fr.esgi.iuwindow;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButtons extends JPanel {

	public PanelButtons() {
		JButton btnStart = new JButton("Start");
		add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		add(btnStop);
	}
}
