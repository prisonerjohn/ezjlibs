package net.silentlycrashing.gestures;

import java.awt.*;
import processing.core.*;

public abstract class BoundedGestureListener extends GestureListener {
	protected PointInTime startPoint;
	protected Rectangle bounds;
	protected boolean inBounds;
	
	/**
	 * Builds a BoundedGestureListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param bounds the bounding Rectangle of the first mouse press
	 */
	public BoundedGestureListener(PApplet parent, GestureAnalyzer analyzer, Rectangle bounds) {
		super(parent, analyzer);
		
		this.bounds = bounds;
		inBounds = true;
	}

	/** 
	 * Checks that the gesture is starting in the correct bounds and sets the start point.
	 * 
	 * @param p the first PointInTime of the gesture
	 */
	public void startListening(PointInTime pt) {
		if (bounds.contains(pt)) {
			startPoint = pt;
			inBounds = true;
		} else {
			inBounds = false;
		}
	}
	
	public abstract void keepListening(PointInTime pt);
	
	public abstract void stopListening(PointInTime pt);
	
    public PointInTime getStartPoint() { return startPoint; }
    public void setBounds(Rectangle newBounds) { bounds = newBounds; }
}
