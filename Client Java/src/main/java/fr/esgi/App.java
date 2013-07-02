package fr.esgi;

import javax.swing.JFrame;

import fr.esgi.iuwindow.MainGraphique;

/**
 * App
 * 
 * @author Cédric TESNIERE
 * @since 5 janv. 2013
 */
public class App extends JFrame
{
    public static void main( String[] args )
    {
    	JFrame frame = new MainGraphique();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
    }
}
