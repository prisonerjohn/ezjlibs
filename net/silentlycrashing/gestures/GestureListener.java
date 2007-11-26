package net.silentlycrashing.gestures;

import net.silentlycrashing.util.RegisteredAction;
import processing.core.*;

public abstract class GestureListener {
	protected PApplet p;
	protected GestureAnalyzer ga;
	
	protected boolean active;
	
	protected RegisteredAction onAction;
	protected RegisteredAction offAction;
	
	public GestureListener(PApplet parent, GestureAnalyzer analyzer) {
		p = parent;
		ga = analyzer;
		ga.registerStartAction("startListening", this, new Class[] { PointInTime.class });
		ga.registerUpdateAction("keepListening", this, new Class[] { PointInTime.class });
		ga.registerStopAction("stopListening", this, new Class[] { PointInTime.class });	
		active = false;
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
	
	/**
	 * Registers the given method with the on action.
	 *
	 * @param name the name of the method to register
     * @param o the Object holding the method
     */
    public void registerOnAction(String name, Object o) {
        onAction = new RegisteredAction(name, o);
    }
	 	   
    /**
     * Registers the given method with the off action.
     *
     * @param name the name of the method to register
     * @param o the Object holding the method
     */
    public void registerOffAction(String name, Object o) {
    	offAction = new RegisteredAction(name, o);
    }
	
    public void activate() {
    	if (!active) {
    		active = true;
    		if (onAction != null) {
        		onAction.invoke();
        	}
    	}
    }
    
    public void deactivate() {
    	if (active) {
    		active = false;
        	if (offAction != null) {
        		offAction.invoke();
        	}
		}
    }
    
    public boolean isActive() { return active; }
}
