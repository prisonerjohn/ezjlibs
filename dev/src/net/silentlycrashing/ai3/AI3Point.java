package net.silentlycrashing.ai3;

import processing.core.*;

public abstract class AI3Point {

    public float x;
    public float y;

    public AI3Point(float _x, float _y) {
        x = _x;
        y = _y;
    }

    public abstract void render(PApplet _p);
}
