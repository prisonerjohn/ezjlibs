package net.silentlycrashing.gestures;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import net.silentlycrashing.util.*;
import processing.core.*;

/**
 * A GestureAnalyzer that listens for a matching gesture after the movement is completed.
 * <p>The analysis algorithm used here is based on the <a href="http://www.smardec.com/products/mouse-gestures.html">MouseGestures</a> library by Smardec.</p>
 * <p>Debugging:<br>
 * The "verbose" flag can be set to print the recorded gestures to standard output.<br>
 * The "debug" flag can be set to plot the key points on the display.</p>
 */
/* $Id$ */
public class GestureAnalyzer {
	public static final String LEFT = "L";
	public static final String RIGHT = "R";
	public static final String UP = "U";
	public static final String DOWN = "D";
	
	public static final int KEY_SIZE = 10;
	
	protected PApplet p;
	
	private int buttonToCheck;
	private int gridSize;
	private Point startPoint;
	private StringBuffer gesture;
	
	private Vector startActions;
	private Vector updateActions;
	private Vector stopActions;
	
	private boolean verbose;
	private boolean debug;
	
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
		
		buttonToCheck = button;
		gridSize = gSize;
		startPoint = null;
		gesture = new StringBuffer();
		
		startActions = new Vector();
		updateActions = new Vector();
		stopActions = new Vector();
		
		verbose = false;
		debug = false;
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
    	RegisteredAction startAction;
        for (Iterator i = startActions.iterator(); i.hasNext();) {
        	startAction = (RegisteredAction)i.next();
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
    	RegisteredAction updateAction;
        for (Iterator i = updateActions.iterator(); i.hasNext();) {
        	updateAction = (RegisteredAction)i.next();
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
    	RegisteredAction stopAction;
        for (Iterator i = stopActions.iterator(); i.hasNext();) {
        	stopAction = (RegisteredAction)i.next();
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
		if ((event.getButton() != buttonToCheck) && (event.getButton() != MouseEvent.NOBUTTON)) {
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
				try {
					update(event.getPoint());
				} catch (NullPointerException e) {
					// XXX only happens on Windows, I think it's related to this NOBUTTON shit
					System.out.println("StartPoint is missing...");
				}
				break;
			case MouseEvent.MOUSE_MOVED:
				break;
		}
	}

	/** 
	 * Sets the start Point and invokes all start RegisteredActions.
	 * <p>Called when the mouse is pressed.</p>
	 * 
	 * @param pt the current mouse Point
	 */
	public void start(Point pt) {
		startPoint = pt;
		invokeStartActions();
	}
	
	/** 
	 * Checks if a new move has occurred and if so, invokes all update RegisteredActions.
	 * <p>Called when the mouse is dragged.</p>
	 * 
	 * @param pt the current mouse Point
	 */
	public void update(Point pt) {
		int dX = pt.x-startPoint.x;
		int dY = pt.y-startPoint.y;
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
        
        startPoint = pt;
        invokeUpdateActions();
	}
	
	/** 
	 * Invokes all stop RegisteredActions and resets the GestureAnalyzer.
	 * <p>Called when the mouse is released.</p>
	 * 
	 * @param pt the current mouse Point (unused)
	 */
	public void stop(Point pt) {
		invokeStopActions();
		reset();
	}
	
	/**
	 * Resets the GestureAnalyzer.
	 */
	private void reset() {
    	startPoint = null;
        gesture.delete(0, gesture.length());
        
        if (verbose) 
        	PApplet.println();
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
        
        if (verbose) 
        	PApplet.print(move);
        if (debug) {
        	p.noStroke();
        	p.fill(204, 0, 0);
        	p.ellipse(startPoint.x, startPoint.y, KEY_SIZE, KEY_SIZE);
        }
    }
    
    /**
     * Checks if the passed regular expression matches the saved gesture.
     * 
     * @param regex the regular expression to check
     *
     * @return whether or not there is a match
     */
    public boolean matches(String regex) {
    	return getGesture().matches(regex);
    }
    
    public int getGridSize() { return gridSize; }
    public void setGridSize(int s) { gridSize = s; }
    public String getGesture() { return gesture.toString(); }
    public boolean isRecognized() { return (gesture.length() > 0); }
    public void setVerbose(boolean v) { verbose = v; }
    public boolean isVerbose() { return verbose; }
    public void setDebug(boolean d) { debug = d; }
    public boolean isDebug() { return debug; }
}
