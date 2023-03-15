package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
	
	public final int radius = 7;
	public int x, y;
	public int dx, dy;
	private Color color;
	
	public Ball(int x, int y, int dx, int dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	public void draw(int x, int y, Graphics2D g) {
		g.setColor(color);
		g.fillOval(x, y, radius * 2, radius * 2);
		this.x = x;
		this.y = y;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public boolean contains(int x, int y) {
		
		if(x > this.x - 10 && x < this.x + radius * 2 && y > this.y - 10 && y < this.y + radius * 2) {
			return true;
		}
		return false;
	}
	
}
