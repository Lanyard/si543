/* Name: Raynald Mirville
* Class name: Asteroid
*/
package com.raynaldmirville.si543;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Asteroid {
	private int xPos, yPos, xMax, yMax, diameter;
	private final int DELTA_MAX = 15;
	private Color clr;
	
	public Asteroid() {
		// Generate the random coordinates for the default ship
		this.xMax = 250;
		this.yMax = 250;
		Random r = new Random();
		this.xPos = r.nextInt(this.xMax + 1);
		this.yPos = r.nextInt(this.yMax + 1);
		this.diameter = r.nextInt(15) + 10;
		this.clr = new Color(50,50,127);
	}
	
	public Asteroid(int h, int w) {
		Random pos = new Random();
		this.diameter = pos.nextInt(15) + 10;
		this.xMax = w - this.diameter;
		this.yMax = h - this.diameter;
		this.xPos = pos.nextInt(xMax + 1);
		this.yPos = pos.nextInt(yMax + 1);
		this.clr = new Color(50,50,127);
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public int getDiameter() {
		return diameter;
	}
	
	public void setBoundaries(int h, int w) {
		this.xMax = w - this.diameter;
		this.yMax = h - this.diameter;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.clr);
		g.fillOval(this.xPos, this.yPos, this.diameter, this.diameter);
	}
	
	public void move() {
		boolean move, up, down, left, right;
		int xDelta, yDelta;
		Random r = new Random();

		// TODO: Determine if it should move.
		
		up = down = left = right = true;
		move = false;
		
		// Determine if it's too close to any boundaries
		if (this.xPos < DELTA_MAX) {
			left = false;
		}
		if (this.xPos > this.xMax - this.diameter - DELTA_MAX) {
			right = false;
		}
		
		// Determine left v. right direction
		if (!left) {
			// can't go left or right
			if (!right) {
				xDelta = 0;
			}
			
			// can't go left, can go right
			else {
				xDelta = r.nextInt(DELTA_MAX) + 1;
			}
		}
		
		// can go left, can't go right
		else if (!right) {
			xDelta = -1 * (r.nextInt(DELTA_MAX) + 1);
		}
		
		// can go either left or right
		else {
			xDelta = r.nextInt(DELTA_MAX) + 1;
			if (r.nextInt(2) == 0) {
				xDelta *= -1;
			}
		}
		
		this.xPos += xDelta;
	}
	
	/*private int determineDirection(boolean axis) {
		return 0;
	}*/

}
