package net.silentlycrashing.gestures;

import java.awt.*;
import processing.core.*;

public class PostGestureListener extends BoundedGestureListener {
	protected String activePattern;
	
	/**
	 * Builds a PostGestureListener covering the entire canvas.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param pattern the move pattern to match for the Listener to be active
	 */
	public PostGestureListener(PApplet parent, GestureAnalyzer analyzer, String pattern) {
		this(parent, analyzer, pattern, new Rectangle(0, 0, parent.width, parent.height));
	}
	
	/**
	 * Builds a PostGestureListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param pattern the move pattern to match for the Listener to be active
	 * @param bounds the bounding Rectangle of the first mouse press
	 */
	public PostGestureListener(PApplet parent, GestureAnalyzer analyzer, String pattern, Rectangle bounds) {
		super(parent, analyzer, bounds);
		activePattern = pattern;
	}

	/** 
	 * Checks that the gesture is starting in the correct bounds and sets the start point.
	 * 
	 * @param p the first PointInTime of the gesture
	 */
	public void startListening(PointInTime pt) {
		reset();
		super.startListening(pt);
	}
	
	/** 
	 * Does nothing. This Listener waits until the gesture is complete to analyze it.
	 * 
	 * @param p the current PointInTime of the gesture (unused)
	 */
	public void keepListening(PointInTime pt) {}
	
	/** 
	 * Checks if the regex pattern is matched and sets the Listener as active or not.
	 * 
	 * @param p the last PointInTime of the gesture
	 */
	public void stopListening(PointInTime pt) {
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
	 * <P>The offAction should be calling this.</P>
	 */ 
	public void reset() {
		if (inBounds && active) {
			startPoint = null;
			deactivate();
		}
	}
}
