package net.silentlycrashing.ai3;

import processing.core.*;

public class AI3EndPoint extends AI3Point {

    public AI3EndPoint() {
        super(0, 0);
    }

    @Override
    public void render(PApplet _p) {
        // TODO Auto-generated method stub
        _p.endShape();
    }

}
