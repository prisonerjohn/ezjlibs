package net.silentlycrashing.gestures;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import net.silentlycrashing.util.*;
import processing.core.*;

public class GestureAnalyzer {
	public static final String LEFT = "L";
	public static final String RIGHT = "R";
	public static final String UP = "U";
	public static final String DOWN = "D";
	
	protected PApplet p;
	
	private int buttonToCheck;
	private int gridSize;
	private Point startPoint;
	private StringBuffer gesture;
	
	private Vector<RegisteredAction> startActions;
	private Vector<RegisteredAction> updateActions;
	private Vector<RegisteredAction> stopActions;
	
	/**
	 * Builds a GestureAnalyzer with default button and grid size.
	 * 
	 * @param parent the parent PApplet
	 */
	public GestureAnalyzer(PApplet parent) {
		this(parent, MouseEvent.BUTTON1, 30);
	}
	
	/**
	 * Builds a GestureAnalyzer with default grid size.
	 * 
	 * @param parent the parent PApplet
	 * @param button the mouse button to check
	 */
	public GestureAnalyzer(PApplet parent, int button) {
		this(parent, button, 30);
	}
	
	/**
	 * Builds a GestureAnalyzer.
	 * 
	 * @param parent the parent PApplet
	 * @param button the mouse button to check
	 * @param gSize the grid size
	 */
	public GestureAnalyzer(PApplet parent, int button, int gSize) {
		p = parent;
		p.registerMouseEvent(this);
		//p.registerPost(this);
		
		buttonToCheck = button;
		gridSize = gSize;
		startPoint = null;
		gesture = new StringBuffer();
		
		startActions = new Vector<RegisteredAction>();
		updateActions = new Vector<RegisteredAction>();
		stopActions = new Vector<RegisteredAction>();
	}
	
	/**
     * Registers the given method as a start action.
     * 
     * @param name the name of the method to register
     * @param o the Object holding the method
     * @param cargs the arguments
     */
	public void registerStartAction(String name, Object o, Class cargs[]) {
        startActions.add(new RegisteredAction(name, o, cargs));
    }
	
	/**
     * Invokes the start actions.
     */
    public void invokeStartActions() {
    	for (RegisteredAction startAction : startActions) {
    		startAction.invoke(new Object[] { new PointInTime(startPoint, p.frameCount) });
    	}
    }
    
    /**
     * Registers the given method as an update action.
     * 
     * @param name the name of the method to register
     * @param o the Object holding the method
     * @param cargs the arguments
     */
	public void registerUpdateAction(String name, Object o, Class cargs[]) {
        updateActions.add(new RegisteredAction(name, o, cargs));
    }
	
	/**
     * Invokes the update actions.
     */
    public void invokeUpdateActions() {
    	for (RegisteredAction updateAction : updateActions) {
    		updateAction.invoke(new Object[] { new PointInTime(startPoint, p.frameCount) });
    	}
    }
    
    /**
     * Registers the given method as a stop action.
     * 
     * @param name the name of the method to register
     * @param o the Object holding the method
     * @param cargs the arguments
     */
	public void registerStopAction(String name, Object o, Class cargs[]) {
        stopActions.add(new RegisteredAction(name, o, cargs));
    }
	
	/**
     * Invokes the stop actions.
     */
    public void invokeStopActions() {
    	for (RegisteredAction stopAction : stopActions) {
    		stopAction.invoke(new Object[] { new PointInTime(startPoint, p.frameCount) });
    	}
    }
	
	/**
     * Handles a MouseEvent.
     * <p>Registered to be called automatically by the PApplet.</p>
     * 
     * @param event the incoming MouseEvent
     */
	public void mouseEvent(MouseEvent event) {
		if (event.getButton() != buttonToCheck) {
			return;
		}
		
		switch (event.getID()) {
			case MouseEvent.MOUSE_PRESSED:
				start(event.getPoint());
				break;
			case MouseEvent.MOUSE_RELEASED:
				stop(event.getPoint());
				break;
			case MouseEvent.MOUSE_CLICKED:
				break;
			case MouseEvent.MOUSE_DRAGGED:
				update(event.getPoint());
				break;
			case MouseEvent.MOUSE_MOVED:
				break;
		}
	}

	/** Called when the mouse is pressed. */
	public void start(Point endPoint) {
		startPoint = endPoint;
		invokeStartActions();
	}
	
	/** Called when the mouse is dragged. */
	public void update(Point endPoint) {
		int dX = endPoint.x-startPoint.x;
		int dY = endPoint.y-startPoint.y;
		int dXAbs = Math.abs(dX);
		int dYAbs = Math.abs(dY);

        if ((dXAbs < gridSize) && (dYAbs < gridSize)) {
        	// the points are too close together
        	return;
        }
            
        float tanAbs = ((float)dXAbs)/dYAbs;
        if (tanAbs < 1) {
            if (dY < 0)
                saveMove(UP);
            else
                saveMove(DOWN);
        } else {
            if (dX < 0)
                saveMove(LEFT);
            else
                saveMove(RIGHT);
        }
        
        startPoint = endPoint;
        invokeUpdateActions();
	}
	
	/** Called when the mouse is released. */
	public void stop(Point newPoint) {
		System.out.println(gesture);
		invokeStopActions();
		clear();
	}
	
	private void clear() {
    	startPoint = null;
        gesture.delete(0, gesture.length());
    }
	
	/**
     * Adds a move to the buffer.
     *
     * @param move the recognized move
     */
    private void saveMove(String move) {
        if ((gesture.length() > 0) && (gesture.charAt(gesture.length() - 1) == move.charAt(0))) {
        	// the move is identical to the last one, skip it
        	return;
        }

        gesture.append(move);
        invokeUpdateActions();
    }
    
    public boolean matches(String regex) {
    	return getGesture().matches(regex);
    }
    
    public int getGridSize() { return gridSize; }
    public void setGridSize(int s) { gridSize = s; }
    public String getGesture() { return gesture.toString(); }
    public boolean isRecognized() { return (gesture.length() > 0); }
}
