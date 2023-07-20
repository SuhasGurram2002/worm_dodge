package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entities.Node;

public class Worm {
	
	private Node head;
	private ArrayList<Node> nodes;
	private int length;
	
	private int tailX, tailY;
	
	public Worm(int xPos, int yPos, int length) {
		head = new Node(xPos, yPos);
		nodes = new ArrayList<Node>();
		this.length = length;

		for(int i = 0; i < length; i++) {
			Node temp = new Node(head.xPos, head.yPos+=15);
			nodes.add(temp);
		}
	}
	
	public void draw(int x, int y, Graphics2D g) {
		
		int oldHeadX = head.xPos, oldHeadY = head.yPos;

		head.updatePos(x, y);
		head.draw(g);
		
		for(int i = 0; i < length; i++) {
			
			int previousNodeXPosition = nodes.get(i).xPos;
			int previousNodeYPosition = nodes.get(i).yPos;
			
			nodes.get(i).xPos = oldHeadX;
			nodes.get(i).yPos = oldHeadY;
			
			if(i == length - 1) {
				tailX = nodes.get(i).xPos;
				tailY = nodes.get(i).yPos;
			}
			
			if(i < 20)
				g.setColor(Color.pink);
			else g.setColor(Color.blue);
			nodes.get(i).draw(g);

			oldHeadX = previousNodeXPosition;
			oldHeadY = previousNodeYPosition;
		}
	}
	
	public boolean intersects(Ball b) {
		if(b.contains(head.xPos, head.yPos)) return true;
		return false;
	}
	
	public void addLength() {
		
		for(int i = 0; i < 15; i++) {
			this.length++;
			Node temp = new Node(tailX, tailY);
			nodes.add(temp);
		}
	}
	
}
