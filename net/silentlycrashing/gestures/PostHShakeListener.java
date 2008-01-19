package net.silentlycrashing.gestures;

import java.awt.*;
import processing.core.*;

/**
 * Listens for a horizontal shake after the movement is completed.
 */
/* $Id$ */
public class PostHShakeListener extends PostGestureListener implements HShake {
	/**
	 * Builds a PostHShakeListener covering the entire canvas.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 */
	public PostHShakeListener(PApplet parent, GestureAnalyzer analyzer) {
		this(parent, analyzer, new Rectangle(0, 0, parent.width, parent.height));
	}
	
	/**
	 * Builds a bounded PostHShakeListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param x the x-coordinate of the bounding Rectangle
	 * @param y the y-coordinate of the bounding Rectangle
	 * @param w the width of the bounding Rectangle
	 * @param h the height of the bounding Rectangle
	 */
	public PostHShakeListener(PApplet parent, GestureAnalyzer analyzer, int x, int y, int w, int h) {
		this(parent, analyzer, new Rectangle(x, y, w, h));
	}
	
	/**
	 * Builds a bounded PostHShakeListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param bounds the bounding Rectangle
	 */
	public PostHShakeListener(PApplet parent, GestureAnalyzer analyzer, Rectangle bounds) {
		super(parent, analyzer, HS_PATTERN, bounds);
	}
}
