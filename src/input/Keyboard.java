package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	boolean keys[] = new boolean[120];
	public boolean up, down, left, right;

	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}

	public void keyPressed(KeyEvent e) {
		keys[KeyEvent.VK_UP] = false;
		keys[KeyEvent.VK_DOWN] = false;
		keys[KeyEvent.VK_RIGHT] = false;
		keys[KeyEvent.VK_LEFT] = false;
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
