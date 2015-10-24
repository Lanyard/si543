/* Name: Raynald Mirville
* Class name: SpaceShip
*/
package com.raynaldmirville.si543;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SpaceShip {
	private int xPos, yPos, xMax, yMax;
	private boolean shooting;
	private Color clr;
	private final int WIDTH = 50;
	private final int HEIGHT = 50;
	
	public SpaceShip() {
		this.setup();
		// Generate the random coordinates for the default ship
		this.xMax = 250;
		this.yMax = 250;
		Random pos = new Random();
		this.xPos = pos.nextInt(xMax + 1);
		this.yPos = pos.nextInt(yMax + 1);
	}

	public SpaceShip(int x, int y) {
		this.setup();
		this.xPos = x;
		this.yPos = y;
	}
	
	/*
	 * Does constructor setup common to all constructors
	 */
	public void setup() {
		this.clr = Color.red;
		this.shooting = false;
	}
	
	public void setShooting(boolean s) {
		this.shooting = s;
	}
	
	public void setColor(Color c) {
		this.clr = c;
	}
	
	public String toString() {
		String output = "xPos: " + this.xPos + "\n";
		output = output + "yPos: " + this.yPos + "\n";
		output = output + "Shooting?: " + this.shooting + "\n";
		return output;
	}
	
	public void draw(Graphics g, int w) {
		int xWin, yWin, xWin2, xWin3, hWin, wWin, xLaser, yLaser;
		Color cWin = new Color(50,50,127);
		
		// Draw the ship stand
		g.setColor(Color.white);
		g.drawLine(this.xPos, this.yPos, this.xPos + (this.WIDTH / 2), this.yPos + (this.HEIGHT / 2));
		g.drawLine(this.xPos, this.yPos + this.HEIGHT, this.xPos + (this.WIDTH / 2), this.yPos + (this.HEIGHT / 2));
		
		// Draw the hull of the ship
		g.setColor(this.clr);
		g.fillOval(this.xPos, this.yPos, this.WIDTH, this.HEIGHT);
		g.fillArc(this.xPos - (this.WIDTH / 2), this.yPos, 2 * this.WIDTH, this.HEIGHT, -90, 180);
		
		// Draw the windows
		wWin = this.WIDTH / 4;
		hWin = this.HEIGHT / 4;
		xWin = this.xPos + (this.WIDTH * 5 / 4) - (wWin / 2);
		yWin = this.yPos + (this.HEIGHT * 1 / 2) - (hWin / 2);
		xWin2 = xWin - (wWin * 3 / 2);
		xWin3 = xWin2 - (wWin * 3 / 2);
		g.setColor(cWin);
		g.fillOval(xWin, yWin, wWin, hWin);
		g.fillOval(xWin2, yWin, wWin, hWin);
		g.fillOval(xWin3, yWin, wWin, hWin);
		
		// Draw the laser if the ship is pew-pewing
		if (this.shooting) {
			g.setColor(Color.red);
			xLaser = this.xPos + (this.WIDTH * 3 / 2);
			yLaser = this.yPos + (this.HEIGHT / 2);
			g.drawLine(xLaser, yLaser, w, yLaser);
		}
		return;
	}
	
	public void move(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
}