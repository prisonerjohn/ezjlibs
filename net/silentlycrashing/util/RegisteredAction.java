package net.silentlycrashing.util;


import processing.core.PApplet;
import java.lang.reflect.Method;

/**
 * A registered action to associate with an Component state
 *
 * @author Elie Zananiri
 */
public class RegisteredAction {
    Object registeredObject;
    Method registeredMethod;
    
    /**
     * builds an RegisteredAction without method arguments
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
     * builds an RegisteredAction with method arguments
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
     * links the given Object and Method to this action
     * 
     * @param o the Object to link
     * @param m the Method to link
     */
    public void link(Object o, Method m) {
        registeredObject = o;
        registeredMethod = m;
    }

    /**
     * invokes the action without arguments
     */
    public void invoke() {
        invoke(new Object[] {});
    }
    
    /**
     * invokes the action with arguments
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
