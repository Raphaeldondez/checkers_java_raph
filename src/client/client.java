package client;

import java.awt.*;
import javax.swing.*;
import shared.board;

public class client extends JFrame {

	public clientNetwork clientNetwork;

	public client() {
		// setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board_pannel board_pannel = new board_pannel();
		add(board_pannel, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	class board_pannel extends JPanel {
		public  final int board_pannel_size_pixels = 600;

		public static void boardPannel() {
			board b = new board();
			b.print_board();
		}

		public Dimension getPreferredSize() {
			return new Dimension(board_pannel_size_pixels, board_pannel_size_pixels);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			int delta = 60;
			Dimension d = this.getSize();
			System.out.println("y size : " + d.height + " x size : " + d.width);
			int x = (d.width - (10 * delta)) / 2;
			int y = (d.height - (10 * delta)) / 2;

			for (int line = 0; line <= 10; line++) {
				System.out.println("x: " + Integer.toString(x) + " y: "
						+ Integer.toString(y + line * delta));
				g.drawLine(x, y + line * delta, x + 10 * delta, y + line * delta);
				g.drawLine(x + line * delta, y, x + line * delta, y + 10 * delta);
			}
			// g.setColor(Color.BLACK);

			// g.setColor(Color.RED);

			// Draw Text
			// g.drawString("This is my custom Panel!", 10, 20);
		}
	}

	public static void main(String[] args) {

	}

}
