package net.silentlycrashing.ai3;

public class AI3BeginPoint extends AI3Point {

    public AI3BeginPoint(float _x, float _y) {
        super(_x, _y);
    }

    @Override
    public void render() {
        p.beginShape();
        p.vertex(x, y); 
    }

}
