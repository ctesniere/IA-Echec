package fr.esgi.iuwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButtons extends JPanel{

public PanelButtons() {
		
		JButton btnStart = new JButton("Coup suivant");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChessBoard generalBoard = (ChessBoard) getParent().getComponent(1);
				String url = generalBoard.reponse();
				
			}
		});
		add(btnStart);

	}
}
