package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
	
	public final int radius = 7;
	public int x, y;
	public int dx, dy;
	private Color color;
	private boolean isGreen = false;
	
	public Ball(int x, int y, int dx, int dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	public void toggleColor() {
        isGreen = !isGreen;
    }
	
	public void draw(int x, int y, Graphics2D g) {
		if (isGreen) {
            color = Color.green;
        } else {
            color = Color.red;
        }
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
