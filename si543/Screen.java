/* Name: Raynald Mirville
* Class name: Screen
*
* Notes for instructor (if any):
*
* Received assistance from: No one
* Expected score: 100 -- Created method to generate
* random light colors for the ships that are always
* clearly visible against the black background.
*/
package com.raynaldmirville.si543;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JPanel {
	private SpaceShip ship1, ship2, ship3, ship4;
	private Star st1, st2, st3;
	
	public Screen() {
		int[] clr = new int[3];
		
		// create four Spaceships, two with default constructor, two with second constructor
		ship1 = new SpaceShip();
		ship2 = new SpaceShip();
		ship3 = new SpaceShip(50, 50);
		ship4 = new SpaceShip(150, 150);
		
		// create three stars
		st1 = new Star();
		st2 = new Star();
		st3 = new Star();
		
		// change the color of the two default ships to random colors
		clr = generateColor();
		ship1.setColor(new Color(clr[0],clr[1],clr[2]));
		clr = generateColor();
		ship2.setColor(new Color(clr[0],clr[1],clr[2]));
		
		// change the status of two ships to start shooting
		ship2.setShooting(true);
		ship4.setShooting(true);
		
		// setup the initial dimensions of the screen
		setPreferredSize (new Dimension(600, 600));
	    setBackground (Color.black);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw a star
		st1.drawStar(g);
		st2.drawStar(g);
		st3.drawStar(g);
		
		ship1.draw(g, this.getWidth());
		ship2.draw(g, this.getWidth());
		ship3.draw(g, this.getWidth());
		ship4.draw(g, this.getWidth());
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

	public static void main(String[] args) {
		JFrame window = new JFrame("Spaceships in space, because space.");
		window.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		Screen screen = new Screen();
		
		window.getContentPane().add(screen);
		window.pack();
		window.setVisible(true);
	}
}
