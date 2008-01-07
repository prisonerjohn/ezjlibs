package net.silentlycrashing.gestures;

import processing.core.*;

/**
 * Draws the path of the matching movement.
 */
/* $Id$ */
public class KeyPlotter extends GestureListener {
	/**
	 * Builds a KeyPlotter.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 */
	public KeyPlotter(PApplet parent, GestureAnalyzer analyzer) {
		super(parent, analyzer);
	}
	
	/** 
	 * Begins the shape and adds the first PointInTime.
	 * 
	 * @param pt the first PointInTime of the gesture
	 */
	public void startListening(PointInTime pt) {
		p.beginShape();
		p.curveVertex(pt.x, pt.y);
	}
	
	/** 
	 * Adds the current PointInTime to the shape.
	 * 
	 * @param pt the current PointInTime of the gesture
	 */
	public void keepListening(PointInTime pt) {
		p.curveVertex(pt.x, pt.y);
	}
	
	/** 
	 * Adds the last PointInTime and closes the shape.
	 * 
	 * @param pt the last PointInTime of the gesture
	 */
	public void stopListening(PointInTime pt) {
		p.curveVertex(pt.x, pt.y);
		p.endShape();
		
		PApplet.println(ga.getGesture());
	}
}
