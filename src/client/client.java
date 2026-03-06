package client;

import shared.board;

import javax.swing.*;
import java.awt.*;

import java.io.*;

class client extends JFrame {
	private int port = 5000;
	private String server_host = "localhost";

	public client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board_pannel board_pannel = new board_pannel();
		add(board_pannel, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	class board_pannel extends JPanel {
		private final int board_pannel_size_pixels = 600;
		private board b;

		public board_pannel() {
			b = new board();
			b.print_board();
		}

		public Dimension getPreferredSize() {
			return new Dimension(board_pannel_size_pixels, board_pannel_size_pixels);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			final int delta = 60;
			// center board
			Dimension d = this.getSize();
			int x = (d.width - (b.side_size * delta)) / 2;
			int y = (d.height - (b.side_size * delta)) / 2;

			for (int line = 0; line <= b.side_size; line++) {
				g.drawLine(x, y + line * delta, x + b.side_size * delta, y + line * delta);
				g.drawLine(x + line * delta, y, x + line * delta, y + b.side_size * delta);
			}
			final int circle_diameter = 2 * delta / 3;

			for (int y_index = 0; y_index < b.side_size; y_index++) {
				for (int x_index = 0; x_index < b.side_size; x_index++) {
					if (b.board[x_index + y_index * b.side_size] == b.BLACK_PAWN
							|| b.board[x_index] == b.BLACK_KING)
						g.setColor(Color.BLACK);
					else if (b.board[x_index + y_index * b.side_size] == b.RED_PAWN
							|| b.board[x_index] == b.RED_KING)
						g.setColor(Color.RED);
					else
						continue;
					g.fillOval((x - circle_diameter / 2 + delta / 2) + delta * x_index,
							(y - circle_diameter / 2 + delta / 2) + delta * y_index,
							circle_diameter,
							circle_diameter);
				}
			}
		}

		// g.setColor(Color.RED);

		// Draw Text
		// g.drawString("This is my custom Panel!", 10, 20);

	}

	public static void main(String[] args) {
		client c = new client();
		// b.
	}
}
