package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.*;

public class Ball extends JFrame {

	private static final int RADIUS = 20;
	int x = 0;
	int y = 0;
	int xspeed = 1;
	int yspeed = 1;
	private GameBoard game;
	boolean hitwall = false;
	Color color;

	public Ball(GameBoard game, Color color) {
		this.game = game;
		this.color = color;
	}

	void move() {
		if (y + yspeed < 0)
			yspeed = 1;
		if (y + yspeed > game.getHeight() - 2 * RADIUS)
			yspeed = -1;
		if (collision()) {
			xspeed = -xspeed;
			//System.out.println("HITRacquet");
		} else if ((x + xspeed < 0 || x + xspeed > game.getWidth() - 2 * RADIUS) && game.getWidth() > 0) {
			//System.out.println(("HITWall"));
			hitwall = true;
			game.setBackground(Color.pink);
			game.deleteballs();
		} else {
			//System.out.println("Moving");
		}
		x = x + xspeed;
		y = y + yspeed;
	}

	private boolean collision() {
		return game.racquet1.getBounds().intersects(getBounds())
				|| game.racquet2.getBounds().intersects(getBounds());
	}


	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 2 * RADIUS, 2 * RADIUS);
	}
}