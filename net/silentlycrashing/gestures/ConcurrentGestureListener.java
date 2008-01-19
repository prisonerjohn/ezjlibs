package net.silentlycrashing.gestures;

import java.awt.*;

import net.silentlycrashing.util.PointInTime;
import processing.core.*;

/**
 * Listens for a matching gesture while the movement is being made.
 */
/* $Id$ */
public class ConcurrentGestureListener extends GestureListener {
	protected String activePattern;
	
	/**
	 * Builds a ConcurrentGestureListener covering the entire canvas.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param pattern the move pattern to match for the Listener to be active
	 */
	public ConcurrentGestureListener(PApplet parent, GestureAnalyzer analyzer, String pattern) {
		this(parent, analyzer, pattern, new Rectangle(0, 0, parent.width, parent.height));
	}
	
	/**
	 * Builds a bounded ConcurrentGestureListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param pattern the move pattern to match for the Listener to be active
	 * @param x the x-coordinate of the bounding Rectangle
	 * @param y the y-coordinate of the bounding Rectangle
	 * @param w the width of the bounding Rectangle
	 * @param h the height of the bounding Rectangle
	 */
	public ConcurrentGestureListener(PApplet parent, GestureAnalyzer analyzer, String pattern, int x, int y, int w, int h) {
		this(parent, analyzer, pattern, new Rectangle(x, y, w, h));
	}
	
	/**
	 * Builds a bounded ConcurrentGestureListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param pattern the move pattern to match for the Listener to be active
	 * @param bounds the bounding Rectangle
	 */
	public ConcurrentGestureListener(PApplet parent, GestureAnalyzer analyzer, String pattern, Rectangle bounds) {
		super(parent, analyzer, bounds);
		activePattern = pattern;
	}

	/** 
	 * Checks if the regex pattern is matched and sets the Listener as active or not.
	 * 
	 * @param pt the current PointInTime of the gesture
	 */
	public void keepListening(PointInTime pt) {
		if (inBounds) {
			if (ga.matches(activePattern)) {
				activate();
			} else {
				deactivate();
			}
		}
	}
	
	/** 
	 * Resets the Listener.
	 * 
	 * @param pt the last PointInTime of the gesture
	 */
	public void stopListening(PointInTime pt) {
		if (inBounds && active) {
			startPoint = null;
			deactivate();
		}
	}
}
