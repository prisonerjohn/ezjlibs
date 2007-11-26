package net.silentlycrashing.gestures;

import java.awt.*;
import processing.core.*;

public class ConcurrentGestureListener extends BoundedGestureListener {
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
	 * Builds a ConcurrentGestureListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param pattern the move pattern to match for the Listener to be active
	 * @param bounds the bounding Rectangle of the first mouse press
	 */
	public ConcurrentGestureListener(PApplet parent, GestureAnalyzer analyzer, String pattern, Rectangle bounds) {
		super(parent, analyzer, bounds);
		activePattern = pattern;
	}

	/** 
	 * Checks if the regex pattern is matched and sets the Listener as active or not.
	 * 
	 * @param p the current PointInTime of the gesture
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
	 * @param p the last PointInTime of the gesture
	 */
	public void stopListening(PointInTime pt) {
		if (inBounds && active) {
			startPoint = null;
			deactivate();
		}
	}
}
