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
				// Retrieving the chessBoard, component #0 being the panel containing the button
				ChessBoard generalBoard = (ChessBoard) getParent().getComponent(1);
				// Retrieving the URL based on the current chessboard state
				String url = generalBoard.reponse();
				try {
					generalBoard.connexion(url);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(btnStart);

	}
}
