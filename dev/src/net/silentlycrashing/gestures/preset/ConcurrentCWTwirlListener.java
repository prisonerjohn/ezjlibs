package net.silentlycrashing.gestures.preset;

import java.awt.*;

import net.silentlycrashing.gestures.ConcurrentGestureListener;
import net.silentlycrashing.gestures.GestureAnalyzer;
import processing.core.*;

/**
 * Listens for a clockwise twirl while the movement is being made.
 */
/* $Id$ */
public class ConcurrentCWTwirlListener extends ConcurrentGestureListener implements CWTwirl {
	/**
	 * Builds a ConcurrentCWTwirlListener covering the entire canvas.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 */
	public ConcurrentCWTwirlListener(PApplet parent, GestureAnalyzer analyzer) {
		this(parent, analyzer, new Rectangle(0, 0, parent.width, parent.height));
	}
	
	/**
	 * Builds a bounded ConcurrentCWTwirlListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param x the x-coordinate of the bounding Rectangle
	 * @param y the y-coordinate of the bounding Rectangle
	 * @param w the width of the bounding Rectangle
	 * @param h the height of the bounding Rectangle
	 */
	public ConcurrentCWTwirlListener(PApplet parent, GestureAnalyzer analyzer, int x, int y, int w, int h) {
		this(parent, analyzer, new Rectangle(x, y, w, h));
	}
	
	/**
	 * Builds a bounded ConcurrentCWTwirlListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param bounds the bounding Rectangle
	 */
	public ConcurrentCWTwirlListener(PApplet parent, GestureAnalyzer analyzer, Rectangle bounds) {
		super(parent, analyzer, CW_PATTERN, bounds);
	}
}
