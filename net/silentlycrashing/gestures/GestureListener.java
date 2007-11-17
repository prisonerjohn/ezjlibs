package net.silentlycrashing.gestures;

import processing.core.*;

public abstract class GestureListener {
	protected PApplet p;
	protected GestureAnalyzer ga;
	
	public GestureListener(PApplet parent, GestureAnalyzer analyzer) {
		p = parent;
		ga = analyzer;
		ga.registerStartAction("startListening", this, new Class[] { PointInTime.class });
		ga.registerUpdateAction("keepListening", this, new Class[] { PointInTime.class });
		ga.registerStopAction("stopListening", this, new Class[] { PointInTime.class });	
	}
	
	/** 
	 * Holds actions to be performed when a new gesture is starting.
	 * 
	 * @param p the first PointInTime of the gesture
	 */
	public abstract void startListening(PointInTime pt);
	
	/** 
	 * Holds actions to be performed when a new move occurs in the gesture.
	 * 
	 * @param p the current PointInTime of the gesture
	 */
	public abstract void keepListening(PointInTime pt);
	
	/** 
	 * Holds actions to be performed when a gesture completes.
	 * 
	 * @param p the last PointInTime of the gesture
	 */
	public abstract void stopListening(PointInTime pt);
}
