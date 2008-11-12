package net.silentlycrashing.gestures;

import processing.core.*;

public class ShakeListener extends TimedGestureListener {
	public static final int SHAKE_DURATION = 60;
	public static final int SHAKE_STROKES = 10;
	
	/**
	 * Builds a default ShakeListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 */
	public ShakeListener(PApplet parent, GestureAnalyzer analyzer) {
		this(parent, analyzer, 10, 30);
	}
	
	/**
	 * Builds a ShakeListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param n the minimum number of moves of the gesture (in the duration range)
	 * @param d the minimum duration of the gesture
	 */
	public ShakeListener(PApplet parent, GestureAnalyzer analyzer, int n, int d) {
		super(parent, analyzer, "(LR|RL)+", "L?(RL){9,}R?", n, d);
	}
}
