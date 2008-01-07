package net.silentlycrashing.util;

import java.lang.reflect.*;
import processing.core.*;

/**
 * An action that can be saved and then invoked by the code.
 */
/* $Id$ */
public class RegisteredAction {
    Object registeredObject;
    Method registeredMethod;
    
    /**
     * Builds a RegisteredAction without Method arguments.
     * 
     * @param name the name of the method to register
     * @param o the Object holding the method
     */
    public RegisteredAction(String name, Object o) {
        Class c = o.getClass();
        try {
            Method method = c.getMethod(name, new Class[]{});
            link(o, method);
        } catch (Exception e) {
            PApplet.println("Could not register "+name+"() for "+o);
            e.printStackTrace();
        }
    }
    
    /**
     * Builds a RegisteredAction with Method arguments.
     * 
     * @param name the name of the method to register
     * @param o the Object holding the method
     * @param cargs the arguments
     */
    public RegisteredAction(String name, Object o, Class cargs[]) {
        Class c = o.getClass();
        try {
            Method method = c.getMethod(name, cargs);
            link(o, method);
        } catch (Exception e) {
            PApplet.println("Could not register "+name+"() for "+o);
            e.printStackTrace();
        }
    }
    
    /**
     * Links the passed Object and Method to this RegisteredAction.
     * 
     * @param o the Object to link
     * @param m the Method to link
     */
    public void link(Object o, Method m) {
        registeredObject = o;
        registeredMethod = m;
    }

    /**
     * Invokes the RegisteredAction without arguments.
     */
    public void invoke() {
        invoke(new Object[] {});
    }
    
    /**
     * Invokes the RegisteredAction with arguments.
     * 
     * @param oargs the arguments as an Object array
     */
    public void invoke(Object oargs[]) {
        try {
            registeredMethod.invoke(registeredObject, oargs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
