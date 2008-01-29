package net.silentlycrashing.gestures.preset;

import java.awt.*;

import net.silentlycrashing.gestures.GestureAnalyzer;
import net.silentlycrashing.gestures.PostGestureListener;
import processing.core.*;

/**
 * Listens for a counter-clockwise twirl after the movement is completed.
 */
/* $Id$ */
public class PostCCWTwirlListener extends PostGestureListener implements CCWTwirl {
	/**
	 * Builds a PostCCWTwirlListener covering the entire canvas.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 */
	public PostCCWTwirlListener(PApplet parent, GestureAnalyzer analyzer) {
		this(parent, analyzer, new Rectangle(0, 0, parent.width, parent.height));
	}
	
	/**
	 * Builds a bounded PostCCWTwirlListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param x the x-coordinate of the bounding Rectangle
	 * @param y the y-coordinate of the bounding Rectangle
	 * @param w the width of the bounding Rectangle
	 * @param h the height of the bounding Rectangle
	 */
	public PostCCWTwirlListener(PApplet parent, GestureAnalyzer analyzer, int x, int y, int w, int h) {
		this(parent, analyzer, new Rectangle(x, y, w, h));
	}
	
	/**
	 * Builds a bounded PostCCWTwirlListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param bounds the bounding Rectangle
	 */
	public PostCCWTwirlListener(PApplet parent, GestureAnalyzer analyzer, Rectangle bounds) {
		super(parent, analyzer, CCW_PATTERN, bounds);
	}
}
