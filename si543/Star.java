package com.raynaldmirville.si543;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;


public class Star {
	private int xPos;
	private int yPos;
	private int[] dim;
	Color starColor;
	static final int DEFAULT_DIM = 600;
	
	public Star(){
		Random r = new Random();
		this.dim = new int[2];
		this.dim[0] = DEFAULT_DIM;
		this.dim[1] = DEFAULT_DIM;
		xPos = r.nextInt(dim[0]);
		yPos = r.nextInt(dim[1]);
		starColor = Color.yellow;
	}
	
	/**
	 * Generate a Star with a specified height and width for
	 * the space it can appear in.
	 * @param height	the height of the boundary
	 * @param width		the width of the boundary
	 */
	public Star(int height, int width) {
		Random r = new Random();
		this.dim = new int[2];
		this.dim[0] = width;
		this.dim[1] = height;
		xPos = r.nextInt(dim[0]);
		yPos = r.nextInt(dim[1]);
		starColor = Color.yellow;
	}
	
	/**
	 * Modify the boundaries the Star can be drawn in,
	 * and reset the Star's position.
	 * @param height	the height of the boundary
	 * @param width		the width of the boundary
	 */
	public void setDimensions(int height, int width) {
		Random r = new Random();
		this.dim[0] = width;
		this.dim[1] = height;
		xPos = r.nextInt(dim[0]);
		yPos = r.nextInt(dim[1]);
	}
	
	public void drawStar(Graphics g){
		int[] xArr1 = {xPos, xPos+5, xPos+10};
		int[] yArr1 = {yPos + 10, yPos, yPos+10};
		int[] xArr2 = {xPos, xPos+10, xPos+5};
		int[] yArr2 = {yPos +5, yPos+5, yPos+10};
		
		g.setColor(starColor);
		g.fillPolygon(xArr1, yArr1, 3);
		g.fillPolygon(xArr2, yArr2, 3);	
	}
}