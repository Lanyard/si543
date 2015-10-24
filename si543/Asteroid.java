/* Name: Raynald Mirville
* Class name: Asteroid
*/
package com.raynaldmirville.si543;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Asteroid {
	private int diameter;
	private int[] pos, max;
	private final int DELTA_MAX = 15;
	private Color clr;
	
	public Asteroid() {
		// Generate the random coordinates for the default ship
		this.max = new int[2];
		this.max[0] = 250;
		this.max[1] = 250;
		Random r = new Random();
		this.pos = new int[2];
		this.pos[0] = r.nextInt(this.max[0] + 1);
		this.pos[1] = r.nextInt(this.max[1] + 1);
		this.diameter = r.nextInt(15) + 10;
		this.clr = new Color(50,50,127);
	}
	
	public Asteroid(int h, int w) {
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
	
	public int getX() {
		return pos[0];
	}
	
	public int getY() {
		return pos[1];
	}
	
	public int getDiameter() {
		return diameter;
	}
	
	public void setBoundaries(int h, int w) {
		this.max[0] = w - this.diameter;
		this.max[1] = h - this.diameter;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.clr);
		g.fillOval(this.pos[0], this.pos[1], this.diameter, this.diameter);
	}
	
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
