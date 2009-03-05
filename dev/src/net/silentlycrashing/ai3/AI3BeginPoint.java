package net.silentlycrashing.ai3;

import processing.core.*;

public class AI3BeginPoint extends AI3Point {

    public AI3BeginPoint(float _x, float _y) {
        super(_x, _y);
    }

    @Override
    public void render(PApplet _p) {
        _p.beginShape();
        _p.vertex(x, y); 
    }

}
