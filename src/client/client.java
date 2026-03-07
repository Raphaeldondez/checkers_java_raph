package client;

import shared.board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

class client extends JFrame {
	private int port = 5000;
	private String server_host = "localhost";
	private int square_clicked = -1;
	private final int delta = 60;
	int origin_x;
	int origin_y;

	public client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board_pannel board_pannel = new board_pannel();
		add(board_pannel, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	class board_pannel extends JPanel implements MouseListener {
		private final int board_pannel_size_pixels = 600;
		private board b;

		public board_pannel() {
			addMouseListener(this);
			b = new board();
			// b.print_board();
		}

		public Dimension getPreferredSize() {
			return new Dimension(board_pannel_size_pixels, board_pannel_size_pixels);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			// center board
			Dimension d = this.getSize();
			origin_x = (d.width - (b.side_size * delta)) / 2;
			origin_y = (d.height - (b.side_size * delta)) / 2;

			System.out.printf("origin x: %d\norigin y: %d\n", origin_x, origin_y);

			// draw grid
			for (int line = 0; line <= b.side_size; line++) {
				g.drawLine(origin_x, origin_y + line * delta, origin_x + b.side_size * delta,
						origin_y + line * delta);
				g.drawLine(origin_x + line * delta, origin_y, origin_x + line * delta,
						origin_y + b.side_size * delta);
			}
			final int circle_diameter = 2 * delta / 3;

			// draw pieces
			for (int y_index = 0; y_index < b.side_size; y_index++) {
				for (int x_index = 0; x_index < b.side_size; x_index++) {
					int board_array_idx = x_index + y_index * b.side_size;
					if (board_array_idx == square_clicked) {
						g.setColor(Color.green);
						g.fillRect(origin_x + 1 + delta * x_index,
								origin_y + 1 + delta * y_index,
								delta - 1, delta - 1);
					}
					if (b.board[board_array_idx] == b.BLACK_PAWN
							|| b.board[x_index] == b.BLACK_KING)
						g.setColor(Color.BLACK);
					else if (b.board[board_array_idx] == b.RED_PAWN
							|| b.board[x_index] == b.RED_KING)
						g.setColor(Color.RED);
					else
						continue;
					g.fillOval((origin_x - circle_diameter / 2 + delta / 2) + delta * x_index,
							(origin_y - circle_diameter / 2 + delta / 2) + delta * y_index,
							circle_diameter,
							circle_diameter);
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			Point p = e.getPoint();
			System.out.printf("x: %d\ny: %d\n", p.x, p.y);
			if (p.y < b.side_size * delta + origin_y && p.y > origin_y
					&& p.x < b.side_size * delta + origin_x
					&& p.x > origin_x) { // make sure click is within bounds
				square_clicked = ((p.y - origin_y) / delta) * b.side_size + (p.x - origin_x) / delta;
				System.out.printf("clicked on %d\n", square_clicked);
				repaint();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	public static void main(String[] args) {
		client c = new client();
		// b.
	}
}
