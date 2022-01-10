package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GameBoard extends JPanel implements KeyListener {
	Ball ball1;
	Ball ball2;
	Ball ball3;
	Racquet racquet1;
	Racquet racquet2;

	boolean gamefail = false;

	public GameBoard() {
		ball1 = new Ball(this, Color.red);
		ball1.x = 200; ball1.y = 300;
		ball2 = new Ball(this, Color.blue);
		ball2.x = 250; ball2.y = 50;
		ball3 = new Ball(this, Color.yellow);
		ball3.x = 100; ball3.y = 100;
		this.setBackground(Color.green);
		racquet1 = new Racquet(this, 10, 150, Color.blue);
		racquet2 = new Racquet(this, 560, 150, Color.yellow);
		setFocusable(true);
		addKeyListener(this);
	}
	public void deleteballs() {
		ball1.color = Color.pink;
		ball2.color = Color.pink;
		ball3.color = Color.pink;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		racquet1.keyReleased(e);
		racquet2.keyReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		racquet1.keyPressed(e);
		racquet2.keyPressed(e);
	}

	private void move() {
		ball1.move();
		ball2.move();
		ball3.move();
		racquet1.move();
		racquet2.move();
		if (ball1.hitwall || ball2.hitwall || ball3.hitwall) {
			gamefail = true;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ball1.draw(g2d);
		ball2.draw(g2d);
		ball3.draw(g2d);
		racquet1.draw(g2d);
		racquet2.draw(g2d);
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Pong 게임");
		JLabel labels;

		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		labels = new JLabel("Fail");
		labels.setForeground(Color.black);
		labels.setFont(new Font("Serif", Font.BOLD, 50));
		labels.setLocation(250,70);
		labels.setSize(200,200);


		GameBoard game = new GameBoard();
		frame.add(game);


		while (true) {
			game.move();
			game.repaint();
			if (game.gamefail) {
				game.add(labels);
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}