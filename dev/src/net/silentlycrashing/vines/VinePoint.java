package net.silentlycrashing.vines;

import processing.core.PVector;

public class VinePoint extends PVector {
    
    public static long COUNT;
    
    public float s; 
    public long id;
    
    public VinePoint() {
        this(0, 0, 0, 1);
    }

    public VinePoint(float _x, float _y) {
        this(_x, _y, 0, 1);
    }

    public VinePoint(float _x, float _y, float s) {
        this(_x, _y, 0, s);
    }
    
    public VinePoint(float _x, float _y, float _z, float _s) {
        super(_x, _y, _z);
        s = _s;
        id = COUNT++;
    }

}
