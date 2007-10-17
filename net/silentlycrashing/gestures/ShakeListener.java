package net.silentlycrashing.gestures;

import java.util.*;
import processing.core.*;

public class ShakeListener extends GestureListener {
	public static final boolean LEFT_TO_RIGHT = true;
	public static final boolean RIGHT_TO_LEFT = false;
	public static final int SHAKE_DURATION = 60;
	public static final int SHAKE_STROKES = 10;
	
	private LinkedList<PointInTime> tempPoints;
	private LinkedList<PointInTime> keyPoints;
	private boolean hDir; 
	private boolean shaking;
	
	/**
	 * Builds a ShakeListener.
	 * 
	 * @param parent the parent PApplet
	 */
	public ShakeListener(PApplet parent) {
		super(parent);
		p.registerPost(this);
		
		shaking = false;
		
		tempPoints = new LinkedList<PointInTime>();
		keyPoints = new LinkedList<PointInTime>();
	}
	
	/**
	 * Draws lines between all the mouse points.
	 */
	public void drawLines() {
		PointInTime tmp1, tmp2;
	    ListIterator<PointInTime> i = tempPoints.listIterator();
	    tmp1 = i.next();
	    while (i.hasNext()) {
	    	tmp2 = i.next();
	    	p.line(tmp1.x(), tmp1.y(), tmp2.x(), tmp2.y());
	    	tmp1 = tmp2;
	    }
	}
	
	/**
	 * Draws circles at each key point.
	 */
	public void drawKeys() {
		PointInTime tmp;
		ListIterator<PointInTime> i = keyPoints.listIterator();
		while (i.hasNext()) {
			tmp = i.next();
			p.ellipse(tmp.x(), tmp.y(), 10, 10);
	    }
	}

	public void startListening() {
		PointInTime pit = new PointInTime(p.mouseX, p.mouseY, p.frameCount);
		tempPoints.add(pit);
		keyPoints.add(pit);
	}

	public void listen() {
	    PointInTime pit = new PointInTime(p.mouseX, p.mouseY, p.frameCount);
	    tempPoints.add(pit);
	  
	    int s = tempPoints.size();
	    if (s < 3) {
	    	hDir = getHDir(tempPoints.get(s-1).x(), tempPoints.get(s-2).x());
	    } else {
	    	if (hDir != getHDir(tempPoints.get(s-1).x(), tempPoints.get(s-2).x())) {
	    		// changing direction, add a key point
	    		hDir = !hDir;
	    		keyPoints.add(tempPoints.get(s-2));
	    	}
	    }
	}
	
	public void stopListening() {
		tempPoints.clear();
		keyPoints.clear();
		
		shaking = false;
		invokeStopAction();
	}
	
	/**
	 * Removes old entries from the key points list.
	 * <p>Called automatically by the PApplet after draw().</p>
	 */
	public void post() {
		if (p.mousePressed) {
			// clean up
	    	PointInTime tmp;
			while (keyPoints.size() > 0) {
				tmp = keyPoints.getFirst();
				if (p.frameCount-tmp.birth() > SHAKE_DURATION*2) {
					keyPoints.removeFirst();
				} else {
					break;
				}
		    }
		}
    	
    	if (isShaking()) {
    		//PApplet.println("You're shaking!");
    		if (!shaking) {
    			shaking = true;
    			invokeStartAction();
    		}
    	} else {
    		//PApplet.println("Shake harder!");
    		if (shaking) {
    			shaking = false;
    			invokeStopAction();
    		}
    	}
	}

	boolean getHDir(int x1, int x2) {
		if (x1 < x2) {
			return RIGHT_TO_LEFT;
		} else {
			return LEFT_TO_RIGHT;
		}
	}

	boolean isShaking() {
		if (keyPoints.size() >= SHAKE_STROKES) {
			if (keyPoints.getLast().birth()-keyPoints.getFirst().birth() >= SHAKE_DURATION) {
				return true;
			}
		}
		return false;
	}
}
