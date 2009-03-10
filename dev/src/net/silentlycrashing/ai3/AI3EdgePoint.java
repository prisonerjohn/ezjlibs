package net.silentlycrashing.ai3;

public class AI3EdgePoint extends AI3Point {

    public AI3EdgePoint(float _x, float _y) {
        super(_x, _y);
    }

    @Override
    public void render() {
        p.vertex(x, y);
    }

}
