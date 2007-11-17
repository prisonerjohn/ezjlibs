package net.silentlycrashing.gestures;

import net.silentlycrashing.util.*;
import processing.core.*;

public abstract class SimpleGestureListener extends GestureListener {
	protected String activePattern;
	protected boolean active;
	
	protected RegisteredAction onAction;
	protected RegisteredAction offAction;
	
	/**
	 * Builds a SimpleGestureListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param pattern the move pattern to match for the Listener to be active
	 */
	public SimpleGestureListener(PApplet parent, GestureAnalyzer analyzer, String pattern) {
		super(parent, analyzer);
		activePattern = pattern;
		active = false;
	}

	/** 
	 * Does nothing.
	 * 
	 * @param p the first PointInTime of the gesture (unused)
	 */
	public void startListening(PointInTime pt) {}
	
	/** 
	 * Checks if the regex pattern is matched and sets the Listener as active or not.
	 * 
	 * @param p the current PointInTime of the gesture
	 */
	public void keepListening(PointInTime pt) {
		if (ga.matches(activePattern)) {
			activate();
		} else {
			deactivate();
		}
	}
	
	/** 
	 * Resets the Listener.
	 * 
	 * @param p the last PointInTime of the gesture
	 */
	public void stopListening(PointInTime pt) {
		deactivate();
	}
	
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
