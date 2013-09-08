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
					
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(true);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}
}