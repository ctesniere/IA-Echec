package fr.esgi.iuwindow;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelButtons extends JPanel {

    // static fields
    // --------------------------------------------------------------------------------------------

    public static final long serialVersionUID = 1L;

    // constructors
    // --------------------------------------------------------------------------------------------

    public PanelButtons() {
        JButton btnStart = new JButton("Coup suivant");
        btnStart.addActionListener(new ActionListener() {

            // implemented methods
            // (ActionListener)
            // ----------------------------------

            @Override
            public void actionPerformed(ActionEvent event) {
                // Retrieving the chessBoard, component #0 being the panel
                // containing the button
                ChessBoard generalBoard = (ChessBoard) getParent().getComponent(1);

                // Retrieving the URL based on the current chessboard state
                String url = generalBoard.reponse();

                try {
                    generalBoard.connexion(url);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        super.add(btnStart);
    }
}