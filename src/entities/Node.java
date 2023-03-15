package entities;

import java.awt.Graphics2D;

public class Node {

	private final int radius = 15;
	
	public int xPos, yPos;
	
	public Node(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void updatePos(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public void draw(Graphics2D graphics) {
		graphics.fillOval(xPos, yPos, radius, radius);
	}
	
}
