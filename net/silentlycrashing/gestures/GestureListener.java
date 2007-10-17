package net.silentlycrashing.gestures;

import net.silentlycrashing.util.*;
import java.awt.*;
import java.awt.event.*;
import processing.core.*;

public abstract class GestureListener {
	protected PApplet p;
	
	protected RegisteredAction startAction;
	protected RegisteredAction stopAction;
	
	/**
	 * Builds a GestureListener.
	 * 
	 * @param parent the parent PApplet
	 */
	public GestureListener(PApplet parent) {
		p = parent;
		p.registerMouseEvent(this);
		p.registerPost(this);
	}
	
	/**
     * Registers the given method with the start action.
     * 
     * @param name the name of the method to register
     * @param o the Object holding the method
     */
	public void registerStartAction(String name, Object o) {
        startAction = new RegisteredAction(name, o);
    }
	
	/**
     * Invokes the method registered with the start action.
     */
    public void invokeStartAction() {
        if (startAction != null) {
        	startAction.invoke();
        }
    }
    
    /**
     * Registers the given method with the stop action.
     * 
     * @param name the name of the method to register
     * @param o the Object holding the method
     */
	public void registerStopAction(String name, Object o) {
        stopAction = new RegisteredAction(name, o);
    }
	
	/**
     * Invokes the method registered with the stop action.
     */
    public void invokeStopAction() {
        if (stopAction != null) {
        	stopAction.invoke();
        }
    }
	
	/**
     * Handles a MouseEvent.
     * <p>Registered to be called automatically by the PApplet.</p>
     * 
     * @param event the incoming MouseEvent
     */
	public void mouseEvent(MouseEvent event) {
		switch (event.getID()) {
			case MouseEvent.MOUSE_PRESSED:
				startListening();
				break;
			case MouseEvent.MOUSE_RELEASED:
				stopListening();
				break;
			case MouseEvent.MOUSE_CLICKED:
				break;
			case MouseEvent.MOUSE_DRAGGED:
				listen();
				break;
			case MouseEvent.MOUSE_MOVED:
				break;
		}
	}

	/** Called when the mouse is pressed. */
	public abstract void startListening();
	/** Called when the mouse is dragged. */
	public abstract void listen();
	/** Called when the mouse is released. */
	public abstract void stopListening();
	
	/**
	 * A Point with a time stamp (in frames).
	 */
	protected class PointInTime {
		private Point point;
		private int birthFrame;
		
		/**
		 * Builds a PointInTime.
		 * 
		 * @param x the x-coord
		 * @param y the y-coord
		 * @param b the birth frame
		 */
		public PointInTime(int x, int y, int b) {
			this(new Point(x, y), b);
		}
		
		/**
		 * Builds a PointInTime.
		 * 
		 * @param pt the Point
		 * @param b the birth frame
		 */
		public PointInTime(Point pt, int b) {
			this.point = pt;
			this.birthFrame = b;
		}
		
		// getters
		public int x() { return point.x; }
		public int y() { return point.y; }
		public Point point() { return point; }
		public int birth() { return birthFrame; }
	}
}
