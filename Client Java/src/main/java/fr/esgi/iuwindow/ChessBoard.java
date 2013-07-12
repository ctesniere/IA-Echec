package fr.esgi.iuwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.esgi.service.Connexion;

public class ChessBoard extends JPanel implements MouseListener,
		MouseMotionListener {
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;

	public ChessBoard() {
		Dimension boardSize = new Dimension(400, 400);

		// Use a Layered Pane for this this application

		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		add(layeredPane);

		// Add a chess board to the Layered Pane

		chessBoard = new JPanel();
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);

		// Build the Chess Board squares

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JPanel square = new JPanel(new BorderLayout());
				square.setBackground((i + j) % 2 == 0 ? Color.white
						: Color.black);
				chessBoard.add(square);
			}
		}
		
		// Add a few pieces to the board

		ImageIcon tour = new ImageIcon(getClass().getResource("/tour.png")); // add an image here
		JLabel piece = new JLabel(tour);
		JPanel panel = (JPanel) chessBoard.getComponent(0);
		panel.add(piece);
		
		piece = new JLabel(tour);
		panel = (JPanel) chessBoard.getComponent(7);
		panel.add(piece);
		
		ImageIcon reine = new ImageIcon(getClass().getResource("/reine.png"));
		piece = new JLabel(reine);
		panel = (JPanel) chessBoard.getComponent(4);
		panel.add(piece);
		
		ImageIcon roi = new ImageIcon(getClass().getResource("/roi.png"));
		piece = new JLabel(roi);
		panel = (JPanel) chessBoard.getComponent(3);
		panel.add(piece);
		
		ImageIcon cavalier = new ImageIcon(getClass().getResource("/cavalier.png"));
		piece = new JLabel(cavalier);
		panel = (JPanel) chessBoard.getComponent(1);
		panel.add(piece);
		
		piece = new JLabel(cavalier);
		panel = (JPanel) chessBoard.getComponent(6);
		panel.add(piece);
		
		ImageIcon fou = new ImageIcon(getClass().getResource("/fou.png"));
		piece = new JLabel(fou);
		panel = (JPanel) chessBoard.getComponent(2);
		panel.add(piece);
		
		piece = new JLabel(fou);
		panel = (JPanel) chessBoard.getComponent(5);
		panel.add(piece);
		
		ImageIcon pion = new ImageIcon(getClass().getResource("/pion.png"));
		for(int i=8; i< 16; i++)
		{
			piece = new JLabel(pion);
			panel = (JPanel) chessBoard.getComponent(i);
			panel.add(piece);
		}
		
		//this.connexionWS();
	}
	
	/**
	 * Permet d'appeler le WebService et de placer les pions
	 * 
	 * @author Valérie MALET
	 */
//	public void connexionWS()
//	{
//		
//		int i = chessBoard.getComponentCount();
//		for (int j = 0; j < i; j++) {
//		
//			
//		}
//
//		//Component[] c = chessBoard.getComponents();
//		//chessBoard.get
//		
//		
//		//connexion c = new connexion();
//		//String s = c.Connexion();
//		//System.out.println(s);
//		Connexion c = new Connexion();
//		String s = c.Connexion();
//		System.out.println(s);
//		
//		String jsonString = "{ [Black,roi;e1,reine;f4,pion;f5], [White,roi;e1,reine;f4,pion;f5]}";
//		if(s != null)
//		{
//			
//			String[] splitBlackWhite = jsonString.split("],");
//			String blackPieces = splitBlackWhite[0].replace('{', ' ').replace('[', ' ').trim();
//			String whitePieces = splitBlackWhite[1].replace('}', ' ').replace(']', ' ').trim();
//			
//						
//			String[] blackPositions = blackPieces.split(",");
//			
//			// tu as toutes les positions des pièces dans les tableaux blackPositions et whitePositions au format
//			// roi;e1
//			// reine;f4
//			// pion;f5
//				
//			
//			/** Traitement pièces Noires **/
//			//String ChaineBlack = stri.substring(bl, wh);
//			//ChaineBlack = ChaineBlack.replace("Black", "");
//			
//	        String[] result = ChaineBlack.split(",");
//
//	        for(String res : result){
//	            System.out.println(res);
//	            
//	            //String piece = res.substring(0, res.lastIndexOf(";"));
//	            //String extension = res.substring(res.indexOf(";"));
//	            //String extension = res.substring(res.lastIndexOf(";"));
//	            //System.out.println(res.indexOf("roi"));
//	            //System.out.println(extension);
//	        }
//			
//			
//			int roi = ChaineBlack.indexOf("Roi");
//			int tour = ChaineBlack.indexOf("Tour");
//			int fou = ChaineBlack.indexOf("Fou");
//			int pion = ChaineBlack.indexOf("Pion");			
//			
//			/** Traitement pièces Blanches **/ 
//			String ChaineWhite = stri.substring(wh);
//			
//			
//			//System.out.println(ChaineBlack);
//			//System.out.println(ChaineWhite);
//			
//			
//			
//			
//			/*if ((_location.charAt(0) == 'a')) return 0;
//			if ((_location.charAt(0) == 'b')) return 1;
//			if ((_location.charAt(0) == 'c')) return 2;
//			if ((_location.charAt(0) == 'd')) return 3;
//			if ((_location.charAt(0) == 'e')) return 4;
//			if ((_location.charAt(0) == 'f')) return 5;
//			if ((_location.charAt(0) == 'g')) return 6;
//			if ((_location.charAt(0) == 'h')) return 7;
//
//		
//			if ((_location.charAt(1) == '1')) return 0;
//			if ((_location.charAt(1) == '2')) return 1;
//			if ((_location.charAt(1) == '3')) return 2;
//			if ((_location.charAt(1) == '4')) return 3;
//			if ((_location.charAt(1) == '5')) return 4;
//			if ((_location.charAt(1) == '6')) return 5;
//			if ((_location.charAt(1) == '7')) return 6;
//			if ((_location.charAt(1) == '8')) return 7;*/
//			
//		}
//		else 
//		{
//			
//		}
//	}
	
	
	/*
	 * * Add the selected chess piece to the dragging layer so it can be moved
	 */
	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel) c;
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);

		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
		layeredPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	}

	/*
	 * * Move the chess piece around
	 */
	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null)
			return;

		// The drag location should be within the bounds of the chess board

		int x = me.getX() + xAdjustment;
		int xMax = layeredPane.getWidth() - chessPiece.getWidth();
		x = Math.min(x, xMax);
		x = Math.max(x, 0);

		int y = me.getY() + yAdjustment;
		int yMax = layeredPane.getHeight() - chessPiece.getHeight();
		y = Math.min(y, yMax);
		y = Math.max(y, 0);

		chessPiece.setLocation(x, y);
	}

	/*
	 * * Drop the chess piece back onto the chess board
	 */
	public void mouseReleased(MouseEvent e) {
		layeredPane.setCursor(null);

		if (chessPiece == null)
			return;

		// Make sure the chess piece is no longer painted on the layered pane

		chessPiece.setVisible(false);
		layeredPane.remove(chessPiece);
		chessPiece.setVisible(true);

		// The drop location should be within the bounds of the chess board

		int xMax = layeredPane.getWidth() - chessPiece.getWidth();
		int x = Math.min(e.getX(), xMax);
		x = Math.max(x, 0);

		int yMax = layeredPane.getHeight() - chessPiece.getHeight();
		int y = Math.min(e.getY(), yMax);
		y = Math.max(y, 0);

		Component c = chessBoard.findComponentAt(x, y);

		if (c instanceof JLabel) {
			Container parent = c.getParent();
			parent.remove(0);
			parent.add(chessPiece);
			parent.validate();
		} else {
			Container parent = (Container) c;
			parent.add(chessPiece);
			parent.validate();
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
