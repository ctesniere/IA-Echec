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
import fr.esgi.export.MoveEx;
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
		 * Placing white pieces on the chessboard
		 */
		ImageIcon tour = new ImageIcon(getClass()
				.getResource("/white_tour.png"));
		JLabel piece = new JLabel(tour);
		JPanel panel = (JPanel) chessBoard.getComponent(0);
		chessBoard.getComponent(0).setName("white_tour");
		panel.add(piece);

		piece = new JLabel(tour);
		panel = (JPanel) chessBoard.getComponent(7);
		chessBoard.getComponent(7).setName("white_tour");
		panel.add(piece);

		ImageIcon reine = new ImageIcon(getClass().getResource(
				"/white_reine.png"));
		piece = new JLabel(reine);
		panel = (JPanel) chessBoard.getComponent(4);
		chessBoard.getComponent(4).setName("white_reine");
		panel.add(piece);

		ImageIcon roi = new ImageIcon(getClass().getResource("/white_roi.png"));
		piece = new JLabel(roi);
		panel = (JPanel) chessBoard.getComponent(3);
		chessBoard.getComponent(3).setName("white_roi");
		panel.add(piece);

		ImageIcon cavalier = new ImageIcon(getClass().getResource(
				"/white_cavalier.png"));
		piece = new JLabel(cavalier);
		panel = (JPanel) chessBoard.getComponent(1);
		chessBoard.getComponent(1).setName("white_cavalier");
		panel.add(piece);

		piece = new JLabel(cavalier);
		panel = (JPanel) chessBoard.getComponent(6);
		chessBoard.getComponent(6).setName("white_cavalier");
		panel.add(piece);

		ImageIcon fou = new ImageIcon(getClass().getResource("/white_fou.png"));
		piece = new JLabel(fou);
		panel = (JPanel) chessBoard.getComponent(2);
		chessBoard.getComponent(2).setName("white_fou");
		panel.add(piece);

		piece = new JLabel(fou);
		panel = (JPanel) chessBoard.getComponent(5);
		chessBoard.getComponent(5).setName("white_fou");
		panel.add(piece);

		ImageIcon pion = new ImageIcon(getClass()
				.getResource("/white_pion.png"));
		for (int i = 8; i < 16; i++) {
			piece = new JLabel(pion);
			panel = (JPanel) chessBoard.getComponent(i);
			chessBoard.getComponent(i).setName("white_pion");
			panel.add(piece);
		}

		/*
		 * Placing black pieces on the chessboard
		 */
		tour = new ImageIcon(getClass().getResource("/black_tour.png"));
		piece = new JLabel(tour);
		panel = (JPanel) chessBoard.getComponent(56);
		chessBoard.getComponent(56).setName("black_tour");
		panel.add(piece);

		piece = new JLabel(tour);
		panel = (JPanel) chessBoard.getComponent(63);
		chessBoard.getComponent(63).setName("black_tour");
		panel.add(piece);

		reine = new ImageIcon(getClass().getResource("/black_reine.png"));
		piece = new JLabel(reine);
		panel = (JPanel) chessBoard.getComponent(60);
		chessBoard.getComponent(60).setName("black_reine");
		panel.add(piece);

		roi = new ImageIcon(getClass().getResource("/black_roi.png"));
		piece = new JLabel(roi);
		panel = (JPanel) chessBoard.getComponent(59);
		chessBoard.getComponent(59).setName("black_roi");
		panel.add(piece);

		cavalier = new ImageIcon(getClass().getResource("/black_cavalier.png"));
		piece = new JLabel(cavalier);
		panel = (JPanel) chessBoard.getComponent(57);
		chessBoard.getComponent(57).setName("black_cavalier");
		panel.add(piece);

		piece = new JLabel(cavalier);
		panel = (JPanel) chessBoard.getComponent(62);
		chessBoard.getComponent(62).setName("black_cavalier");
		panel.add(piece);

		fou = new ImageIcon(getClass().getResource("/black_fou.png"));
		piece = new JLabel(fou);
		panel = (JPanel) chessBoard.getComponent(58);
		chessBoard.getComponent(58).setName("black_fou");
		panel.add(piece);

		piece = new JLabel(fou);
		panel = (JPanel) chessBoard.getComponent(61);
		chessBoard.getComponent(61).setName("black_fou");
		panel.add(piece);

		pion = new ImageIcon(getClass().getResource("/black_pion.png"));
		for (int i = 48; i < 56; i++) {
			piece = new JLabel(pion);
			panel = (JPanel) chessBoard.getComponent(i);
			chessBoard.getComponent(i).setName("black_pion");
			panel.add(piece);
		}
	}

	public void connexion(String _url) throws JsonParseException,
			JsonMappingException, IOException {
		Connexion c = new Connexion();
		String s = c.Connexion(_url);
		ObjectMapper mapper = new ObjectMapper();
		Export export = mapper.readValue(s, Export.class);
		
		MoveEx bestMove = export.getBestMovePiece();
		
		int initialPosition = ((bestMove.getStartY()-1)*8)+bestMove.getStartX();
		int destination = ((bestMove.getEndY()-1)*8)+bestMove.getEndX();
			
		JPanel component = (JPanel) chessBoard.getComponent(initialPosition);
		movingPiece = component.getName();
		chessPiece = (JLabel) component.getComponent(0);
		chessPiece.setVisible(false);
		chessBoard.getParent().remove(chessPiece);
		chessPiece.setVisible(true);
		chessBoard.getComponent(destination).setName(movingPiece);
//		chessBoard.add(chessPiece);
		chessBoard.validate();
	}

	/**
	 * Permet de récupèrer la liste des pièces et de le renvoyer a l'ia
	 */
	public String reponse() {
		List<PieceEx> listPiece = new ArrayList<PieceEx>();
		String pieceName;
		for (int i = 0; i < 64; i++) {
			pieceName = chessBoard.getComponent(i).getName();

			if (null != pieceName) {
				int columnPosition = i % 8;
				PieceEx piece = new PieceEx();

				StringBuilder location = new StringBuilder();
				if (i >= 0 && i <= 7)
					location.append("a");
				if (i >= 8 && i <= 15)
					location.append("b");
				if (i >= 16 && i <= 23)
					location.append("c");
				if (i >= 24 && i <= 31)
					location.append("d");
				if (i >= 32 && i <= 39)
					location.append("e");
				if (i >= 40 && i <= 47)
					location.append("f");
				if (i >= 48 && i <= 55)
					location.append("g");
				if (i >= 56 && i <= 63)
					location.append("h");

				location.append(columnPosition + 1);

				// Determines the color of the piece
				String color = chessBoard.getComponent(i).getName()
						.startsWith("black_") ? "black" : "white";

				piece.setColor(color);
				piece.setLocation(location.toString());
				piece.setName(chessBoard.getComponent(i).getName().substring(6));

				listPiece.add(piece);

			}
		}

		String url = "http://127.0.0.1:8080/IA_Echec/alphabeta/black/bKing/bQueen/bCrazy/bKnight/bTower/bPawn/white/wKing/wQueen/wCrazy/wKnight/wTower/wPawn";
		
		StringBuilder strBlackPion = new StringBuilder();
		StringBuilder strWhitePion = new StringBuilder();
		StringBuilder strBlackTour = new StringBuilder();
		StringBuilder strWhiteTour = new StringBuilder();
		StringBuilder strBlackCavalier = new StringBuilder();
		StringBuilder strWhiteCavalier = new StringBuilder();
		StringBuilder strBlackFou = new StringBuilder();
		StringBuilder strWhiteFou = new StringBuilder();

		Boolean foundBlackKing = false;
		Boolean foundWhiteKing = false;
		Boolean foundBlackQueen = false;
		Boolean foundWhiteQueen = false;
		
		List<PieceEx> listBlackFou = new ArrayList<PieceEx>();
		List<PieceEx> listBlackPion = new ArrayList<PieceEx>();
		List<PieceEx> listBlackCavalier = new ArrayList<PieceEx>();
		List<PieceEx> listBlackTour = new ArrayList<PieceEx>();
		List<PieceEx> listWhiteFou = new ArrayList<PieceEx>();
		List<PieceEx> listWhitePion = new ArrayList<PieceEx>();
		List<PieceEx> listWhiteCavalier = new ArrayList<PieceEx>();
		List<PieceEx> listWhiteTour = new ArrayList<PieceEx>();

		for (PieceEx maPiece : listPiece) {
			if (maPiece.getColor().equals("black")) {
				// King + Queen
				if (maPiece.getName().equals("roi")) {
					foundBlackKing = true;
					url = url.replace("bKing", maPiece.getLocation());
				}
				if (maPiece.getName().equals("reine")) {
					foundBlackQueen = true;
					url = url.replace("bQueen", maPiece.getLocation());
				}

				// Counting pieces for "Fou"
				if (maPiece.getName().equals("fou"))
					listBlackFou.add(maPiece);

				// Counting pieces for "Pion"
				if (maPiece.getName().equals("pion"))
					listBlackPion.add(maPiece);

				// Counting pieces for "Cavalier"
				if (maPiece.getName().equals("cavalier"))
					listBlackCavalier.add(maPiece);

				// Counting pieces for "Tour"
				if (maPiece.getName().equals("tour"))
					listBlackTour.add(maPiece);				
			} else if (maPiece.getColor().equals("white")) {
				if (maPiece.getName().equals("roi")) {
					foundWhiteKing = true;
					url = url.replace("wKing", maPiece.getLocation());
				}
				if (maPiece.getName().equals("reine")) {
					foundWhiteQueen = true;
					url = url.replace("wQueen", maPiece.getLocation());
				}

				// Counting pieces for "Fou"
				if (maPiece.getName().equals("fou"))
					listWhiteFou.add(maPiece);

				// Counting pieces for "Pion"
				if (maPiece.getName().equals("pion"))
					listWhitePion.add(maPiece);

				// Counting pieces for "Cavalier"
				if (maPiece.getName().equals("cavalier"))
					listWhiteCavalier.add(maPiece);

				// Counting pieces for "Tour"
				if (maPiece.getName().equals("tour"))
					listWhiteTour.add(maPiece);
			}
		}
		
		if (!listBlackFou.isEmpty()) {
			for (PieceEx fou : listBlackFou)
				strBlackFou.append(fou.getLocation()).append(":");
			strBlackFou.deleteCharAt(strBlackFou.length()-1);
		} else
			strBlackFou.append("null");

		if (!listBlackPion.isEmpty()) {
			for (PieceEx pion : listBlackPion)
				strBlackPion.append(pion.getLocation()).append(":");
			strBlackPion.deleteCharAt(strBlackPion.length()-1);
		} else
			strBlackPion.append("null");

		if (!listBlackCavalier.isEmpty()) {
			for (PieceEx cavalier : listBlackCavalier)
				strBlackCavalier.append(cavalier.getLocation()).append(":");
			strBlackCavalier.deleteCharAt(strBlackCavalier.length()-1);
		} else
			strBlackCavalier.append("null");

		if (!listBlackTour.isEmpty()) {
			for (PieceEx tour : listBlackTour)
				strBlackTour.append(tour.getLocation()).append(":");
			strBlackTour.deleteCharAt(strBlackTour.length()-1);
		} else
			strBlackTour.append("null");
		
		if (!listWhiteFou.isEmpty()) {
			for (PieceEx fou : listWhiteFou)
				strWhiteFou.append(fou.getLocation()).append(":");
			strWhiteFou.deleteCharAt(strWhiteFou.length()-1);
		} else
			strWhiteFou.append("null");

		if (!listWhitePion.isEmpty()) {
			for (PieceEx pion : listWhitePion)
				strWhitePion.append(pion.getLocation()).append(":");
			strWhitePion.deleteCharAt(strWhitePion.length()-1);
		} else
			strWhitePion.append("null");

		if (!listWhiteCavalier.isEmpty()) {
			for (PieceEx cavalier : listWhiteCavalier)
				strWhiteCavalier.append(cavalier.getLocation()).append(":");
			strWhiteCavalier.deleteCharAt(strWhiteCavalier.length()-1);
		} else
			strWhiteCavalier.append("null");

		if (!listWhiteTour.isEmpty()) {
			for (PieceEx tour : listWhiteTour)
				strWhiteTour.append(tour.getLocation()).append(":");
			strWhiteTour.deleteCharAt(strWhiteTour.length()-1);
		} else
			strWhiteTour.append("null");
		
		if (!foundBlackKing)			
			url = url.replace("bKing", "null");
		if (!foundBlackQueen)
			url = url.replace("bQueen", "null");
		if (!foundWhiteKing)
			url = url.replace("wKing", "null");
		if (!foundWhiteQueen)
			url = url.replace("wQueen", "null");
		
		url = url.replace("bKnight", strBlackCavalier.toString());
		url = url.replace("bCrazy", strBlackFou.toString());
		url = url.replace("bPawn", strBlackPion.toString());
		url = url.replace("bTower", strBlackTour.toString());
		
		url = url.replace("wKnight", strWhiteCavalier.toString());
		url = url.replace("wCrazy", strWhiteFou.toString());
		url = url.replace("wPawn", strWhitePion.toString());
		url = url.replace("wTower", strWhiteTour.toString());		
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