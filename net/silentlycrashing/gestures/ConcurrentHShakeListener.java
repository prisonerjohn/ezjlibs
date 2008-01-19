package net.silentlycrashing.gestures;

import java.awt.*;

import net.silentlycrashing.gestures.preset.HShake;
import processing.core.*;

/**
 * Listens for a horizontal shake while the movement is being made.
 */
/* $Id$ */
public class ConcurrentHShakeListener extends ConcurrentGestureListener implements HShake {
	/**
	 * Builds a ConcurrentHShakeListener covering the entire canvas.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 */
	public ConcurrentHShakeListener(PApplet parent, GestureAnalyzer analyzer) {
		this(parent, analyzer, new Rectangle(0, 0, parent.width, parent.height));
	}
	
	/**
	 * Builds a bounded ConcurrentHShakeListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param x the x-coordinate of the bounding Rectangle
	 * @param y the y-coordinate of the bounding Rectangle
	 * @param w the width of the bounding Rectangle
	 * @param h the height of the bounding Rectangle
	 */
	public ConcurrentHShakeListener(PApplet parent, GestureAnalyzer analyzer, int x, int y, int w, int h) {
		this(parent, analyzer, new Rectangle(x, y, w, h));
	}
	
	/**
	 * Builds a bounded ConcurrentHShakeListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param bounds the bounding Rectangle
	 */
	public ConcurrentHShakeListener(PApplet parent, GestureAnalyzer analyzer, Rectangle bounds) {
		super(parent, analyzer, HS_PATTERN, bounds);
	}
}
