package fr.esgi.iuwindow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * MainGraphique
 * 
 * @author Cédric TESNIERE
 * @since 5 janv. 2013
 */
public class MainGraphique extends JFrame {

	// fields
	// --------------------------------------------------------------------------------------------

	private JPanel contentPane;

	// static fields
	// --------------------------------------------------------------------------------------------

	public static final long serialVersionUID = 1L;

	// constructors
	// --------------------------------------------------------------------------------------------

	public MainGraphique() {
		super("IAEchec");
		super.setBounds(100, 100, 600, 500);

		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));

		this.contentPane.add(new PanelButtons(), BorderLayout.NORTH);
		this.contentPane.add(new ChessBoard(), BorderLayout.CENTER);
		this.contentPane.add(new JScrollPane(new JTextArea()), BorderLayout.EAST);
		
		super.setContentPane(this.contentPane);
		
		Dimension indexSize = new Dimension(20, 10);
		JPanel panel = new JPanel();
		panel.setPreferredSize(indexSize);
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(8, 1));
		for(int i = 0; i < 8; i++) {
			JPanel indexSquare = new JPanel();
			JLabel indexLabel = new JLabel(String.valueOf(i+1));
			indexSquare.add(indexLabel);
			panel.add(indexSquare);
		}
		
		indexSize.setSize(10, 20);
		String[] columns = {"a", "b", "c", "d", "e", "f", "g", "g"};
		panel = new JPanel();
		panel.setPreferredSize(indexSize);
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 8));
		for(int i = 0; i < 8; i++) {
			JPanel indexSquare = new JPanel();
			JLabel indexLabel = new JLabel(columns[i]);
			indexSquare.add(indexLabel);
			panel.add(indexSquare);
		}
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(true);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}
}