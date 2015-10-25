/* Name: Raynald Mirville
* Class name: Screen
*
* Notes for instructor (if any):
*
* Received assistance from: No one
* Expected score: 90
*/
package com.raynaldmirville.si543;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.*;

public class Screen extends JPanel implements ActionListener, MouseListener, MouseMotionListener, ComponentListener {
	private SpaceShip ship;
	private Star[] stars;
	private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	private final int NUM_STARS = 100, NUM_ASTEROIDS = 15, WIDTH = 720, HEIGHT = 480, DELAY = 30;
	private Timer timer;
	
	public Screen() {
		int[] clr = new int[3];
		this.stars = new Star[NUM_STARS];
		
		// initialize timer
		this.timer = new Timer(this.DELAY, this);
		
		// initialize screen
		setPreferredSize (new Dimension(WIDTH, HEIGHT));
	    setBackground (Color.black);
		
		// initialize stars
		for(int i = 0; i < stars.length; i++) {
			stars[i] = new Star(HEIGHT, WIDTH);
		}
		
		// initialize asteroids
		for(int i = 0; i < NUM_ASTEROIDS; i++) {
			this.asteroids.add(new Asteroid(HEIGHT, WIDTH));
		}
		// initialize spaceship
		ship = new SpaceShip();
		clr = generateColor();
		ship.setColor(new Color(clr[0],clr[1],clr[2]));
		
		// start listeners
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addComponentListener(this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the stars
		for(Star st: this.stars) {
			st.drawStar(g);
		}
		
		for(Asteroid astr: this.asteroids) {
			astr.draw(g);
		}
		
		this.ship.draw(g, this.getWidth());
	}
	
	private int[] generateColor() {
		Random r = new Random();
		int[] clr = new int[3];
		// Setting hueRange to generate random light hues that are visible against black background.
		int hueRange = 127;
		for (int i = 0; i < 3; i++) {
			clr[i] = hueRange + r.nextInt(hueRange + 2);
		}
		return clr;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// Animate the asteroids' movement.
		for (Asteroid a: asteroids){
			a.move();
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Dragged.");
		this.ship.move(e.getX(), e.getY());
		repaint();
	}

	// Move the ship along with the mouse cursor
	@Override
	public void mouseMoved(MouseEvent e) {
		this.ship.move(e.getX(), e.getY());
		this.ship.setShooting(false);
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	// Fire the photon torpedoes while the mouse is pressed.
	@Override
	public void mousePressed(MouseEvent e) {
		this.ship.setShooting(true);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.ship.setShooting(false);
		repaint();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// Adapt asteroids' boundaries and reposition them.
	    for (Iterator<Asteroid> asteroidIter = asteroids.iterator(); asteroidIter.hasNext();){
	      asteroidIter.next().setBoundaries(this.getHeight(), this.getWidth());
	    }
		
		// Adapt stars' boundaries and reposition them.
	    for (int i = 0; i < this.stars.length; i++) {
	    	stars[i].setDimensions(this.getHeight(), this.getWidth());
	    }
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		
	}

	public static void main(String[] args) {
		JFrame window = new JFrame("Spaceships in space, because space.");
		window.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		Screen screen = new Screen();
		
		window.getContentPane().add(screen);
		window.pack();
		window.setVisible(true);
	}
}