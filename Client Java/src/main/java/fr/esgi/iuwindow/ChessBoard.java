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

		ImageIcon duke = new ImageIcon("C:\\Users\\EndL\\Desktop\\king.png"); // add an image here

		JLabel piece = new JLabel(duke);
		JPanel panel = (JPanel) chessBoard.getComponent(0);
		panel.add(piece);
		piece = new JLabel(duke);
		panel = (JPanel) chessBoard.getComponent(15);
		panel.add(piece);
	}

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
