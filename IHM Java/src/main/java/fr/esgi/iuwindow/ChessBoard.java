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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import fr.esgi.importJson.Import;
import fr.esgi.importJson.MoveEx;
import fr.esgi.importJson.PieceEx;
import fr.esgi.service.Connexion;
import fr.esgi.service.Helper;

public class ChessBoard extends JPanel implements MouseListener, MouseMotionListener {

	// fields
	// --------------------------------------------------------------------------------------------

	private JLayeredPane layeredPane;
	private JPanel chessBoard;
	private JLabel chessPiece;
	private int xAdjustment;
	private int yAdjustment;
	private String movingPiece;
	private Boolean isWhiteTurn;

	// static fields
	// --------------------------------------------------------------------------------------------

	public static final long serialVersionUID = 1L;

	// constructors
	// --------------------------------------------------------------------------------------------

	public ChessBoard() {
		Dimension boardSize = new Dimension(400, 400);

		this.chessBoard = new JPanel();
		this.chessBoard.setLayout(new GridLayout(8, 8));
		this.chessBoard.setPreferredSize(boardSize);
		this.chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				JPanel square = new JPanel(new BorderLayout());
				square.setBackground(((i + j) % 2 == 0) ? Color.white : Color.gray);

				this.chessBoard.add(square);
			}

		this.layeredPane = new JLayeredPane();
		this.layeredPane.setPreferredSize(boardSize);

		this.layeredPane.addMouseListener(this);
		this.layeredPane.addMouseMotionListener(this);

		this.layeredPane.add(this.chessBoard, JLayeredPane.DEFAULT_LAYER);

		super.add(this.layeredPane);

		this.initPieces();
	}

	// implemented methods
	// (MouseMotionListener)
	// --------------------------------------------------------------------------------------------

	/*
	 * * Move the chess piece around
	 */
	@Override
	public void mouseDragged(MouseEvent me) {
		if (this.chessPiece == null)
			return;

		// The drag location should be within the bounds of the chess board

		int x = me.getX() + this.xAdjustment;
		int xMax = this.layeredPane.getWidth() - this.chessPiece.getWidth();
		x = Math.min(x, xMax);
		x = Math.max(x, 0);

		int y = me.getY() + this.yAdjustment;
		int yMax = this.layeredPane.getHeight() - this.chessPiece.getHeight();
		y = Math.min(y, yMax);
		y = Math.max(y, 0);

		this.chessPiece.setLocation(x, y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	// (MouseListener)
	// --------------------------------------------------------------------------------------------

	/*
	 * * Add the selected chess piece to the dragging layer so it can be moved
	 */
	@Override
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
	 * * Drop the chess piece back onto the chess board
	 */
	@Override
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

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	// instance methods
	// --------------------------------------------------------------------------------------------

	public void initPieces() {
		/*
		 * ***** PLACEMENTS PIECES BLANCHES **************
		 */

		// placement des tours
		this.placerPiece("white_tour", 0, 7);

		// placement de la reine
		this.placerPiece("white_reine", 4);

		// placement du roi
		this.placerPiece("white_roi", 3);

		// placement des cavaliers
		this.placerPiece("white_cavalier", 1, 6);

		// placement des fous
		this.placerPiece("white_fou", 2, 5);

		// placement des pions
		this.placerPiece("white_pion", 8, 9, 10, 11, 12, 13, 14, 15);

		/*
		 * ************ PLACEMENT PIECES NOIRES ***************
		 */
		// placement des tours
		this.placerPiece("black_tour", 56, 63);

		// placement de la reine
		this.placerPiece("black_reine", 60);

		// placement du roi
		this.placerPiece("black_roi", 59);

		// placmeent des cavaliers
		this.placerPiece("black_cavalier", 57, 62);

		// placement des fous
		this.placerPiece("black_fou", 58, 61);

		// placements des pions
		this.placerPiece("black_pion", 48, 49, 50, 51, 52, 53, 54, 55);
	}

	/**
	 * Place une pièce sur l'échiquier.
	 * 
	 * @param index L'index de l'échiquier où placer la pièce
	 * @param name Le nom de la pièce
	 */
	private void placerPiece(String name, int... indexArray) {
		if (indexArray == null)
			return;

		for (int index : indexArray) {
			ImageIcon image = new ImageIcon(this.getClass().getResource("/" + name + ".png"));

			JLabel piece = new JLabel(image);

			JPanel panel = (JPanel) this.chessBoard.getComponent(index);
			panel.setName(name);
			panel.add(piece);
		}
	}

	public void connexion(String _url) throws JsonParseException, JsonMappingException, IOException {
		Connexion c = new Connexion();
		String s = c.connexion(_url);

		if (!s.equals("ERROR: Pas de mouvement possible.")) {
			ObjectMapper mapper = new ObjectMapper();
			Import export = mapper.readValue(s, Import.class);

			MoveEx bestMove = export.getBestMovePiece();

			int startX = Helper.getXfromString(bestMove.getStart());
			int startY = Helper.getYfromString(bestMove.getStart());

			int endX = Helper.getXfromString(bestMove.getEnd());
			int endY = Helper.getYfromString(bestMove.getEnd());

			int initialPosition = ((startY) * 8) + (startX);
			int destination = ((endY) * 8) + (endX);

			JTextArea histo = (JTextArea) getParent().getComponent(2);
			histo.append(bestMove.getColor() + " " + bestMove.getPieceName() 
					+ " : " + bestMove.getStart() + " -> " + bestMove.getEnd());
			
			System.out.println(initialPosition + " -> " + destination);

			JPanel square = (JPanel) this.chessBoard.getComponent(destination);
			if (null != square.getName())
				square.removeAll();

			square = (JPanel) this.chessBoard.getComponent(initialPosition);
			this.movingPiece = square.getName();
			square.setName(null);
			this.chessPiece = (JLabel) square.getComponent(0);
			square.removeAll();
			square = (JPanel) this.chessBoard.getComponent(destination);
			square.setName(this.movingPiece);
			square.add(this.chessPiece);
			this.chessBoard.revalidate();
			this.chessBoard.repaint();

		} else
			JOptionPane.showMessageDialog(null, "Jeu terminé ! Il n'y a plus de coup possible.");
	}

	/**
	 * Permet de récupèrer la liste des pièces et de le renvoyer a l'ia
	 */
	public String reponse() {
		List<PieceEx> listPiece = new ArrayList<PieceEx>();
		StringBuilder location = new StringBuilder();
		String pieceName;
		for (int i = 0; i < 64; i++) {
			pieceName = this.chessBoard.getComponent(i).getName();

			if (null != pieceName) {
				int columnPosition = (i % 8);
				PieceEx piece = new PieceEx();

				location.setLength(0);

				switch (columnPosition) {
					case 0:
						location.append("a");
						break;
					case 1:
						location.append("b");
						break;
					case 2:
						location.append("c");
						break;
					case 3:
						location.append("d");
						break;
					case 4:
						location.append("e");
						break;
					case 5:
						location.append("f");
						break;
					case 6:
						location.append("g");
						break;
					case 7:
						location.append("h");
						break;
					default:
						break;
				}

				location.append((i / 8) + 1);

				// Determines the color of the piece
				String color = chessBoard.getComponent(i).getName().startsWith("black_") ? "black"
						: "white";

				piece.setColor(color);
				piece.setLocation(location.toString());
				piece.setName(chessBoard.getComponent(i).getName().substring(6));

				listPiece.add(piece);

			}
		}

		// Change player's turn
		if (null != isWhiteTurn)
			isWhiteTurn = !isWhiteTurn;

		String url = "http://127.0.0.1:8080/IA_Echec/alphabeta/isWhiteTurn/black/bKing/bQueen/bCrazy/bKnight/bTower/bPawn/white/wKing/wQueen/wCrazy/wKnight/wTower/wPawn";

		StringBuilder str = new StringBuilder();

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
			str.setLength(0);
			for (PieceEx fou : listBlackFou)
				str.append(fou.getLocation()).append(":");
			str.deleteCharAt(str.length() - 1);
		} else
			str.append("null");

		url = url.replace("bCrazy", str.toString());

		if (!listBlackPion.isEmpty()) {
			str.setLength(0);
			for (PieceEx pion : listBlackPion)
				str.append(pion.getLocation()).append(":");
			str.deleteCharAt(str.length() - 1);
		} else
			str.append("null");

		url = url.replace("bPawn", str.toString());

		if (!listBlackCavalier.isEmpty()) {
			str.setLength(0);
			for (PieceEx cavalier : listBlackCavalier)
				str.append(cavalier.getLocation()).append(":");
			str.deleteCharAt(str.length() - 1);
		} else
			str.append("null");

		url = url.replace("bKnight", str.toString());

		if (!listBlackTour.isEmpty()) {
			str.setLength(0);
			for (PieceEx tour : listBlackTour)
				str.append(tour.getLocation()).append(":");
			str.deleteCharAt(str.length() - 1);
		} else
			str.append("null");

		url = url.replace("bTower", str.toString());

		if (!listWhiteFou.isEmpty()) {
			str.setLength(0);
			for (PieceEx fou : listWhiteFou)
				str.append(fou.getLocation()).append(":");
			str.deleteCharAt(str.length() - 1);
		} else
			str.append("null");

		url = url.replace("wCrazy", str.toString());

		if (!listWhitePion.isEmpty()) {
			str.setLength(0);
			for (PieceEx pion : listWhitePion)
				str.append(pion.getLocation()).append(":");
			str.deleteCharAt(str.length() - 1);
		} else
			str.append("null");

		url = url.replace("wPawn", str.toString());

		if (!listWhiteCavalier.isEmpty()) {
			str.setLength(0);
			for (PieceEx cavalier : listWhiteCavalier)
				str.append(cavalier.getLocation()).append(":");
			str.deleteCharAt(str.length() - 1);
		} else
			str.append("null");

		url = url.replace("wKnight", str.toString());

		if (!listWhiteTour.isEmpty()) {
			str.setLength(0);
			for (PieceEx tour : listWhiteTour)
				str.append(tour.getLocation()).append(":");
			str.deleteCharAt(str.length() - 1);
		} else
			str.append("null");

		url = url.replace("wTower", str.toString());

		if (!foundBlackKing) {
			JOptionPane.showMessageDialog(null, "Jeu terminé ! Il n'y a plus de coup possible.");
			url = url.replace("bKing", "null");
		}
		if (!foundWhiteKing) {
			JOptionPane.showMessageDialog(null, "Jeu terminé ! Il n'y a plus de coup possible.");
			url = url.replace("wKing", "null");
		}
		if (!foundBlackQueen)
			url = url.replace("bQueen", "null");
		if (!foundWhiteQueen)
			url = url.replace("wQueen", "null");

		// Force the user to select an option
		while (null == isWhiteTurn) {
			JPanel askWhosTurn = new JPanel();
			JRadioButton blackRadio = new JRadioButton("Black");
			JRadioButton whiteRadio = new JRadioButton("White");
			askWhosTurn.add(blackRadio);
			askWhosTurn.add(whiteRadio);
			JOptionPane.showOptionDialog(null, askWhosTurn, "Who's turn ?", JOptionPane.OK_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);

			// Both test are here to make sure one option is selected
			// otherwise, isWhiteTurn must remain null
			if (whiteRadio.isSelected())
				this.isWhiteTurn = true;
			if (blackRadio.isSelected())
				this.isWhiteTurn = false;
		}

		url = url.replace("isWhiteTurn", String.valueOf(this.isWhiteTurn));

		System.out.println(url);
		return url;
	}

}
