package net.silentlycrashing.gestures;

import processing.core.*;

public class KeyPlotter extends GestureListener {
	public KeyPlotter(PApplet parent, GestureAnalyzer analyzer) {
		super(parent, analyzer);
	}
	
	/** 
	 * Holds actions to be performed when a new gesture is starting.
	 * 
	 * @param pt the first PointInTime of the gesture
	 */
	public void startListening(PointInTime pt) {
		p.beginShape();
		p.curveVertex(pt.x, pt.y);
	}
	
	/** 
	 * Holds actions to be performed when a new move occurs in the gesture.
	 * 
	 * @param pt the current PointInTime of the gesture
	 */
	public void keepListening(PointInTime pt) {
		p.curveVertex(pt.x, pt.y);
	}
	
	/** 
	 * Holds actions to be performed when the gesture is completed.
	 * 
	 * @param pt the last PointInTime of the gesture
	 */
	public void stopListening(PointInTime pt) {
		p.curveVertex(pt.x, pt.y);
		p.endShape();
		
		p.println(ga.getGesture());
	}
}
