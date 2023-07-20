package main;

import javax.swing.JPanel;

import input.Keyboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

public class Game extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	private int wormX = 100, wormY = 100;
	
	private int score = 0;
	
	private Random random;

	private Keyboard key;
	
	public Thread thread;
	private boolean running = false;
	
	//private Color colors[] = {Color.red, Color.white, Color.magenta, Color.orange, Color.cyan, Color.blue, Color.lightGray, Color.pink, Color.yellow};
	
	private Worm worm;
	private int wormSpeed = 2;
	
	private Ball balls[];
	private int numberOfBalls = 15;

	private long start = 0;
	private boolean firstTime = true;

	public Game() {
		initGui();
		worm = new Worm(wormX, wormY, 75);
		balls = new Ball[numberOfBalls];
		key = new Keyboard();
		addKeyListener(key);
		
		random = new Random();

		//int colorIndex = 0;
		
		for(int i = 0; i < balls.length; i++) {
			int ballx = random.nextInt(WIDTH - 15 * 2);
			int bally = random.nextInt(HEIGHT - 15 * 2);

			/*if(colorIndex == 9)
				colorIndex = 0;*/
			
			int balldx = random.nextInt(4) + 1;
			int balldy = random.nextInt(4) + 1;
			balls[i] = new Ball(ballx, bally, balldx, balldy);
			//balls[i].setColor(colors[colorIndex]);
			balls[i].setColor(Color.red);
			//colorIndex++;
		}
		
		setFocusable(true);
		requestFocus();
	}
	
	private void initGui() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.black);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		
		graphics.setColor(Color.orange);
		graphics.setFont(new Font("Consolas", Font.BOLD, 26));
		if(!firstTime)
			graphics.drawString("SCORE: " + (System.currentTimeMillis() - start), 10, 30);
		else
			graphics.drawString("SCORE: 0", 10, 30);

		graphics.setColor(Color.blue);
		worm.draw(wormX, wormY, graphics);
		for(int i = 0; i < balls.length; i++) {
			balls[i].draw(balls[i].x, balls[i].y, graphics);
		}
		
		graphics.dispose();
	}
	
	public synchronized void start() {
		if(thread != null) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		if(thread == null) return;

		running = false;
		try {
			thread.join();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
			}
			repaint();
		}
	}
	
	public void update() {
		if(key.down) {
			if(firstTime) {
				start = System.currentTimeMillis();
				firstTime = false;
			}
			wormY += wormSpeed;
		}
		if(key.up) {
			if(firstTime) {
				start = System.currentTimeMillis();
				firstTime = false;
			}
			wormY -= wormSpeed;
		}
		if(key.right) {
			if(firstTime) {
				start = System.currentTimeMillis();
				firstTime = false;
			}
			wormX += wormSpeed;
		}
		if(key.left) {
			if(firstTime) {
				start = System.currentTimeMillis();
				firstTime = false;
			}
			wormX -= wormSpeed;
		}
		key.update();
		
		checkForCollisions();
		
		moveBall();
	}
	
	private void moveBall() {
		for(int i = 0; i < balls.length; i++) {
			balls[i].x += balls[i].dx;
			balls[i].y += balls[i].dy;
		}
	}
	
	
	private void checkForCollisions() {
		
		//WORM WITH BOUNDARY
		if(wormX > WIDTH - 10 || wormY > HEIGHT - 10 || wormX < -10 || wormY < -10) {
			running = false;
		}
		
		//WORM WITH BALL
		for(int i = 0; i < balls.length; i++) {
			if(worm.intersects(balls[i])) {
				wormSpeed++;
				score++;
				balls[i].x = random.nextInt(WIDTH - balls[i].radius * 2);
				balls[i].y = random.nextInt(HEIGHT - balls[i].radius * 2);

				System.out.println("Collided with ball " + score);
				balls[i].toggleColor();
			}
			
		}
		
		//BALL WITH BOUNDARY
		for(int i = 0; i < balls.length; i++) {
			if(balls[i].x > WIDTH - 10 || balls[i].x < -10) {
				balls[i].dx = -balls[i].dx;
			}
			if(balls[i].y > HEIGHT - 10 || balls[i].y < -10) {
				balls[i].dy = -balls[i].dy;
			}
		}
	}
	
}
