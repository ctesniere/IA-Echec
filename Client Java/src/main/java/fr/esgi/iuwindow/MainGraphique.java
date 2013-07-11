package fr.esgi.iuwindow;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * MainGraphique
 * 
 * @author Cédric TESNIERE
 * @since 5 janv. 2013
 */
public class MainGraphique extends JFrame {

	private JPanel contentPane;
	
	public MainGraphique() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		PanelButtons panelButtons = new PanelButtons();
		contentPane.add(panelButtons, BorderLayout.NORTH);
		
		ChessBoard chessBoard = new ChessBoard();
		contentPane.add(chessBoard, BorderLayout.CENTER);
	}
}
