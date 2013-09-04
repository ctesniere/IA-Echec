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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import fr.esgi.export.Export;
import fr.esgi.export.PieceEx;
import fr.esgi.service.Connexion;

public class ChessBoard extends JPanel implements MouseListener,
		MouseMotionListener {
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	String movingPiece;

	public ChessBoard() {
		Dimension boardSize = new Dimension(400, 400);

		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		add(layeredPane);

		chessBoard = new JPanel();
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JPanel square = new JPanel(new BorderLayout());
				square.setBackground((i + j) % 2 == 0 ? Color.white
						: Color.gray);
				chessBoard.add(square);
			}
		}
		
		initPieces();
		
	}
	
	public void initPieces() {
		/*
		 * Placing black pieces on the chessboard
		 */
		ImageIcon tour = new ImageIcon(getClass().getResource("/black_tour.png"));
		JLabel piece = new JLabel(tour);
		JPanel panel = (JPanel) chessBoard.getComponent(0);
		chessBoard.getComponent(0).setName("black_tour");
		panel.add(piece);
		
		piece = new JLabel(tour);
		panel = (JPanel) chessBoard.getComponent(7);
		chessBoard.getComponent(7).setName("black_tour");
		panel.add(piece);
		
		ImageIcon reine = new ImageIcon(getClass().getResource("/black_reine.png"));
		piece = new JLabel(reine);
		panel = (JPanel) chessBoard.getComponent(4);
		chessBoard.getComponent(4).setName("black_reine");
		panel.add(piece);
		
		ImageIcon roi = new ImageIcon(getClass().getResource("/black_roi.png"));
		piece = new JLabel(roi);
		panel = (JPanel) chessBoard.getComponent(3);
		chessBoard.getComponent(3).setName("black_roi");
		panel.add(piece);
		
		ImageIcon cavalier = new ImageIcon(getClass().getResource("/black_cavalier.png"));
		piece = new JLabel(cavalier);
		panel = (JPanel) chessBoard.getComponent(1);
		chessBoard.getComponent(1).setName("black_cavalier");
		panel.add(piece);
		
		piece = new JLabel(cavalier);
		panel = (JPanel) chessBoard.getComponent(6);
		chessBoard.getComponent(6).setName("black_cavalier");
		panel.add(piece);
		
		ImageIcon fou = new ImageIcon(getClass().getResource("/black_fou.png"));
		piece = new JLabel(fou);
		panel = (JPanel) chessBoard.getComponent(2);
		chessBoard.getComponent(2).setName("black_fou");
		panel.add(piece);
		
		piece = new JLabel(fou);
		panel = (JPanel) chessBoard.getComponent(5);
		chessBoard.getComponent(5).setName("black_fou");
		panel.add(piece);
		
		ImageIcon pion = new ImageIcon(getClass().getResource("/black_pion.png"));
		for(int i=8; i< 16; i++)
		{
			piece = new JLabel(pion);
			panel = (JPanel) chessBoard.getComponent(i);
			chessBoard.getComponent(i).setName("black_pion");
			panel.add(piece);
		}
		
		/*
		 * Placing black pieces on the chessboard
		 */
		tour = new ImageIcon(getClass().getResource("/white_tour.png"));
		piece = new JLabel(tour);
		panel = (JPanel) chessBoard.getComponent(56);
		chessBoard.getComponent(56).setName("white_tour");
		panel.add(piece);
		
		piece = new JLabel(tour);
		panel = (JPanel) chessBoard.getComponent(63);
		chessBoard.getComponent(63).setName("white_tour");
		panel.add(piece);
		
		reine = new ImageIcon(getClass().getResource("/white_reine.png"));
		piece = new JLabel(reine);
		panel = (JPanel) chessBoard.getComponent(60);
		chessBoard.getComponent(60).setName("white_reine");
		panel.add(piece);
		
		roi = new ImageIcon(getClass().getResource("/white_roi.png"));
		piece = new JLabel(roi);
		panel = (JPanel) chessBoard.getComponent(59);
		chessBoard.getComponent(59).setName("black_roi");
		panel.add(piece);
		
		cavalier = new ImageIcon(getClass().getResource("/white_cavalier.png"));
		piece = new JLabel(cavalier);
		panel = (JPanel) chessBoard.getComponent(57);
		chessBoard.getComponent(57).setName("white_cavalier");
		panel.add(piece);
		
		piece = new JLabel(cavalier);
		panel = (JPanel) chessBoard.getComponent(62);
		chessBoard.getComponent(62).setName("white_cavalier");
		panel.add(piece);
		
		fou = new ImageIcon(getClass().getResource("/white_fou.png"));
		piece = new JLabel(fou);
		panel = (JPanel) chessBoard.getComponent(58);
		chessBoard.getComponent(58).setName("white_fou");
		panel.add(piece);
		
		piece = new JLabel(fou);
		panel = (JPanel) chessBoard.getComponent(61);
		chessBoard.getComponent(61).setName("white_fou");
		panel.add(piece);
		
		pion = new ImageIcon(getClass().getResource("/white_pion.png"));
		for(int i=48; i< 56; i++)
		{
			piece = new JLabel(pion);
			panel = (JPanel) chessBoard.getComponent(i);
			chessBoard.getComponent(i).setName("white_pion");
			panel.add(piece);
		}
	
		// debug purpose only
		for(int i=0;i<64; i++)
			System.out.println(chessBoard.getComponent(i).getName());
	}

		
	public void connexion(String _url) throws JsonParseException, JsonMappingException, IOException {
		Connexion c = new Connexion();
		String s = c.Connexion(_url);
		ObjectMapper mapper = new ObjectMapper();
//		Export export = mapper.readValue("ia.json"), Export.class);
		Export export = mapper.readValue(s, Export.class);
		
		List<PieceEx> piece = export.getListPiece();
		
		for(PieceEx res : piece) {
			
			String pos = res.getLocation();
			int location = 0;
			if ((pos.charAt(0) == 'a')) location = 0;
			if ((pos.charAt(0) == 'b')) location = 8;
			if ((pos.charAt(0) == 'c')) location = 16;
			if ((pos.charAt(0) == 'd')) location = 24;
			if ((pos.charAt(0) == 'e')) location = 32;
			if ((pos.charAt(0) == 'f')) location = 40;
			if ((pos.charAt(0) == 'g')) location = 48;
			if ((pos.charAt(0) == 'h')) location = 56;
		
			if ((pos.charAt(1) == '1')) location += 0;
			if ((pos.charAt(1) == '2')) location += 1;
			if ((pos.charAt(1) == '3')) location += 2;
			if ((pos.charAt(1) == '4')) location += 3;
			if ((pos.charAt(1) == '5')) location += 4;
			if ((pos.charAt(1) == '6')) location += 5;
			if ((pos.charAt(1) == '7')) location += 6;
			if ((pos.charAt(1) == '8')) location += 7;
			
			ImageIcon somePiece = new ImageIcon(getClass().getResource("/"+res.getColor()+"_"+res.getName()+".png"));
			JLabel myPiece = new JLabel(somePiece);
			JPanel panel = (JPanel) chessBoard.getComponent(location); // à modifier selon le format renvoyé par getLocation()
			panel.add(myPiece);
        }
	}
	
	/**
	 * Permet de récupèrer la liste des pièces et de le renvoyer a l'ia
	 */
	public String reponse() {
		List<PieceEx> listPiece = new ArrayList<PieceEx>();
		String pieceName;
		for(int i=0; i<64; i++) {
			pieceName = chessBoard.getComponent(i).getName();
			
			if(null != pieceName) {
				int columnPosition = i%8;
				PieceEx piece = new PieceEx();
				
				StringBuilder location = new StringBuilder();
				if(i>=0 && i<=7) location.append("a");
				if(i>=8 && i<=15) location.append("b");
				if(i>=16 && i<=23) location.append("c");
				if(i>=24 && i<=31) location.append("d");
				if(i>=32 && i<=39) location.append("e");
				if(i>=40 && i<=47) location.append("f");
				if(i>=48 && i<=55) location.append("g");
				if(i>=56 && i<=63) location.append("h");				
							
				location.append(columnPosition);
				

				// Determines the color of the piece
				String color = chessBoard.getComponent(i).getName().startsWith("black_") ? "black" : "white";				
				
				piece.setColor(color);
				piece.setLocation(location.toString());
				piece.setName(chessBoard.getComponent(i).getName().substring(6));
				
				listPiece.add(piece);
				
			}
		}
		
		String url = "192.168.X.X/iaechec/black/bKing/bQueen/bCrazy/bKnight/bTower/bPawn/white/wKing/wQueen/wCrazy/wKnight/wTower/wPawn";
		String tab[] = url.split("/");
		for (PieceEx maPiece : listPiece) 
		{
			if(maPiece.getColor() == "black")
			{
				if (maPiece.getName().equals("roi")) url = url.replace("bKing", maPiece.getLocation());
				if (maPiece.getName().equals("reine")) url = url.replace("bQueen", maPiece.getLocation());
				if (maPiece.getName().equals("fou")) {					
					if(tab[5] == "bCrazy")
					{
						tab[5] = maPiece.getLocation();
					} else {
						tab[5] += ":" + maPiece.getLocation();
					}
				}
				if (maPiece.getName().equals("bKnight")){
					if(tab[6] == "bKnight")
					{
						tab[6] = maPiece.getLocation();
					} else {
						tab[6] += ":" + maPiece.getLocation();
					}
				}
				if (maPiece.getName().equals("tour")) {
					if(tab[7] == "bTower")
					{
						tab[7] = maPiece.getLocation();
					} else {
						tab[7] += ":" + maPiece.getLocation();
					}
				}
				if (maPiece.getName().equals("pion")) {
					if(tab[8] == "bPawn")
					{
						tab[8] = maPiece.getLocation();
					} else {
						tab[8] += ":" + maPiece.getLocation();
					}
				}
			}
			else if(maPiece.getColor() == "white")
			{
				if (maPiece.getName().equals("roi")) url = url.replace("wKing", maPiece.getLocation());
				if (maPiece.getName().equals("reine")) url = url.replace("wQueen", maPiece.getLocation());
				
				if (maPiece.getName().equals("fou")) {				
					if(tab[12] == "wCrazy")
					{
						tab[12] = maPiece.getLocation();
					} else {
						tab[12] += ":" + maPiece.getLocation();
					}
				}
				if (maPiece.getName().equals("cavalier")){
					if(tab[13] == "wKnight")
					{
						tab[13] = maPiece.getLocation();
					} else {
						tab[13] += ":" + maPiece.getLocation();
					}
				}
				if (maPiece.getName().equals("tour")) {
					if(tab[14] == "wTower")
					{
						tab[14] = maPiece.getLocation();
					} else {
						tab[14] += ":" + maPiece.getLocation();
					}
				}
				if (maPiece.getName().equals("pion")) {
					if(tab[15] == "wPawn")
					{
						tab[15] = maPiece.getLocation();
					} else {
						tab[15] += ":" + maPiece.getLocation();
					}
				}
			}
			
			url.replace("bCrazy", tab[5]);
			url.replace("bKnight", tab[6]);
			url.replace("bTower", tab[7]);
			url.replace("bPawn", tab[8]);
			url.replace("wCrazy", tab[12]);
			url.replace("wKnight", tab[13]);
			url.replace("wTower", tab[14]);
			url.replace("wPawn", tab[15]);
		}
		
		System.out.println("l url est: "+url);
		return url;
	}

	/*
	 * * Add the selected chess piece to the dragging layer so it can be moved
	 */
	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		movingPiece = c.getParent().getName();
		c.getParent().setName(null);
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
			parent.setName(movingPiece);
			parent.validate();			
		} else {
			Container parent = (Container) c;
			parent.add(chessPiece);
			parent.setName(movingPiece);
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
