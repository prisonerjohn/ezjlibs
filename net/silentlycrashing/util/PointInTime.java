package net.silentlycrashing.util;

import java.awt.*;

/**
 * A Point with a time stamp (in frames).
 */
/* $Id$ */
public class PointInTime extends Point {
	private int birthFrame;
	
	/**
	 * Builds a PointInTime.
	 * 
	 * @param x the x-coord
	 * @param y the y-coord
	 * @param b the birth frame
	 */
	public PointInTime(int x, int y, int b) {
		this(new Point(x, y), b);
	}
	
	/**
	 * Builds a PointInTime.
	 * 
	 * @param pt the Point
	 * @param b the birth frame
	 */
	public PointInTime(Point pt, int b) {
		super(pt);
		this.birthFrame = b;
	}
	
	public int birthFrame() { return birthFrame; }
}
