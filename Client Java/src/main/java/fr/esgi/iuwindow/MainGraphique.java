package fr.esgi.iuwindow;

import javax.swing.JFrame;

/**
 * MainGraphique
 * 
 * @author Cédric TESNIERE
 * @since 5 janv. 2013
 */
public class MainGraphique {

	public static void main(String[] args) {
		try {
			JFrame fenetre = new JFrame("ChessBoard");
			fenetre.setSize(400, 400);
			fenetre.setVisible(true);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
