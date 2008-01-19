package net.silentlycrashing.gestures;

import java.util.*;

import net.silentlycrashing.util.PointInTime;
import processing.core.*;

public class TimedGestureListener extends ConcurrentGestureListener {
	protected String validPattern;
	protected int minMoves;
	protected int minDuration;
	protected LinkedList keyMoveTimes;
	
	/**
	 * Builds a TimedGestureListener.
	 * 
	 * @param parent the parent PApplet
	 * @param analyzer the linked GestureAnalyzer
	 * @param vPattern the move pattern to match for it to be valid
	 * @param aPattern the move pattern to match for the Listener to be active
	 * @param n the minimum number of moves of the gesture (in the duration range)
	 * @param d the minimum duration of the gesture
	 */
	public TimedGestureListener(PApplet parent, GestureAnalyzer analyzer, String vPattern, String aPattern, int n, int d) {
		super(parent, analyzer, aPattern);
		
		validPattern = vPattern;
		minMoves = n;
		minDuration = d;
		keyMoveTimes = new LinkedList();
		
		p.registerPost(this);
	}

	public void startListening(PointInTime pt) {
		super.startListening(pt);
		
		if (inBounds) {
			if (ga.matches(validPattern)) {
				keyMoveTimes.add(new Integer(pt.birthFrame()));
			}
		}
	}

	public void keepListening(PointInTime pt) {
		if (inBounds) {
			if (ga.matches(validPattern)) {
				keyMoveTimes.add(new Integer(pt.birthFrame()));
			}
			setActive();
		}
	}
	
	public void stopListening(PointInTime pt) {
		if (inBounds) {
			super.stopListening(pt);
		
			// reset everything
			keyMoveTimes.clear();
		}
	}
	
	/**
	 * Removes old entries from the key move list.
	 */
	public void post() {
		if (p.mousePressed) {
			while (keyMoveTimes.size() > 0) {
				if ((p.frameCount-((Integer)(keyMoveTimes.getFirst())).intValue()) > minDuration*2) {
					keyMoveTimes.removeFirst();
				} else {
					break;
				}
		    }
			
			setActive();
		}
	}
	
	protected void setActive() {
		if (ga.matches(activePattern)) {
			if (keyMoveTimes.size() >= minMoves) {
				// there are enough moves
				if ((((Integer)keyMoveTimes.getLast()).intValue()-((Integer)keyMoveTimes.getFirst()).intValue()) >= minDuration) {
					// the moves are fast enough
					activate();
				} else {
					// the moves are too slow
					deactivate();
				}
			} else {
				// there aren't enough moves
				deactivate();
			}		
		} else {
			// the pattern is not matched
			deactivate();
		}
	}
}
