/* Name: Raynald Mirville
* Class name: Asteroid
*/
package com.raynaldmirville.si543;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * 
 * @author Raynald Mirville
 *
 */
public class Asteroid {
	private int diameter;
	private int[] pos, max;
	private final int DELTA_MAX = 15, DEFAULT_MAX = 250;
	private Color clr;
	
	public Asteroid() {
		// Generate the random coordinates for the default ship
		this.max = new int[2];
		this.max[0] = DEFAULT_MAX;
		this.max[1] = DEFAULT_MAX;
		Random r = new Random();
		this.pos = new int[2];
		this.pos[0] = r.nextInt(this.max[0] + 1);
		this.pos[1] = r.nextInt(this.max[1] + 1);
		this.diameter = r.nextInt(15) + 10;
		this.clr = new Color(50,50,127);
	}
	
	/**
	 * 
	 * @param h the height of the space the asteroid can move in.
	 * @param w the width of the space the asteroid can move in.
	 */
	public Asteroid(int h, int w) {
		// Check for minimum heights and widths to prevent boundary bugs.
		if (h < DELTA_MAX) {
			h = DELTA_MAX;
		}
		if (w < DELTA_MAX) {
			w = DELTA_MAX;
		}
		Random pos = new Random();
		this.diameter = pos.nextInt(15) + 10;
		this.max = new int[2];
		this.max[0] = w - this.diameter;
		this.max[1] = h - this.diameter;
		this.pos = new int[2];
		this.pos[0] = pos.nextInt(max[0] + 1);
		this.pos[1] = pos.nextInt(max[1] + 1);
		this.clr = new Color(50,50,127);
	}
	
	/**
	 * 
	 * @return the x position of the asteroid.
	 */
	public int getX() {
		return pos[0];
	}
	
	/**
	 * 
	 * @return the y position of the asteroid.
	 */
	public int getY() {
		return pos[1];
	}
	
	/**
	 * 
	 * @return the asteroid's diameter.
	 */
	public int getDiameter() {
		return diameter;
	}
	
	/**
	 * Sets the boundaries of the space the asteroid can move in.
	 * 
	 * @param height	the height of the space.
	 * @param width		the width of the space.
	 */
	public void setBoundaries(int height, int width) {
		Random r = new Random();
		// Check for minimum heights and widths to prevent boundary bugs
		if (height < DELTA_MAX) {
			height = DELTA_MAX*3;
		}
		if (width < DELTA_MAX) {
			width = DELTA_MAX*3;
		}

		// Reset the asteroid's boundaries
		this.max[0] = width - this.diameter;
		this.max[1] = height - this.diameter;
		
		// Reset the asteroid's position
		this.pos[0] = r.nextInt(this.max[0]);
		this.pos[1] = r.nextInt(this.max[1]);
	}
	
	/**
	 * Draws the asteroid.
	 * 
	 * @param g	The Graphics object the asteroid will be drawn in.
	 */
	public void draw(Graphics g) {
		g.setColor(this.clr);
		g.fillOval(this.pos[0], this.pos[1], this.diameter, this.diameter);
	}
	
	/**
	 * Determines the next position of the asteroid.
	 */
	public void move() {
		boolean move;
		Random r = new Random();
		
		// Determine if the asteroid should move
		move = (r.nextInt(3) == 0);
		if (move) {
			determineDirection(0);
			determineDirection(1);
		}
	}
	
	/**
	 * Determines the directions the asteroid can move in, given its boundaries.
	 * 
	 * @param axis	the dimension to determine possible directions for.
	 * 				0 determines the y position.
	 * 				1 determines the x position.
	 */
	private void determineDirection(int axis) {
		boolean negMov, posMov;
		int delta;
		Random r = new Random();
		
		negMov = posMov = true;
		
		// Determine if it's too close to any boundaries
		if (this.pos[axis] < DELTA_MAX) {
			negMov = false;
		}
		if (this.pos[axis] > this.max[axis] - this.diameter - DELTA_MAX) {
			posMov = false;
		}
		
		// Determine positive v. negative direction
		if (!negMov) {
			// can't go negative or positive
			if (!posMov) {
				delta = 0;
			}
			
			// can't go negative, can go positive
			else {
				delta = r.nextInt(DELTA_MAX) + 1;
			}
		}
		
		// can go negative, can't go positive
		else if (!posMov) {
			delta = -1 * (r.nextInt(DELTA_MAX) + 1);
		}
		
		// can go either negative or positive
		else {
			delta = r.nextInt(DELTA_MAX) + 1;
			if (r.nextInt(2) == 0) {
				delta *= -1;
			}
		}
		
		this.pos[axis] += delta;
	}

}
