package net.silentlycrashing.gestures;

import java.awt.event.*;
import processing.core.*;

/**
 * Records gestures made using the mouse for analysis.
 */
/* $Id$ */
public class MouseGestureAnalyzer extends GestureAnalyzer {
	private int buttonToCheck;
	
	/**
	 * Builds a MouseGestureAnalyzer with default button and minimum offset.
	 * 
	 * @param parent the parent PApplet
	 */
	public MouseGestureAnalyzer(PApplet parent) {
		this(parent, MouseEvent.BUTTON1, 30);
	}
	
	/**
	 * Builds a MouseGestureAnalyzer with default minimum offset.
	 * 
	 * @param parent the parent PApplet
	 * @param button the mouse button to check
	 */
	public MouseGestureAnalyzer(PApplet parent, int button) {
		this(parent, button, 30);
	}
	
	/**
	 * Builds a MouseGestureAnalyzer.
	 * 
	 * @param parent the parent PApplet
	 * @param button the mouse button to check
	 * @param min the minimum offset
	 */
	public MouseGestureAnalyzer(PApplet parent, int button, int min) {
		super(parent, min);
		
		p.registerMouseEvent(this);
		buttonToCheck = button;
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
}
