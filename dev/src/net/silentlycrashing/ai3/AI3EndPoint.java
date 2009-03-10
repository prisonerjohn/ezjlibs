package net.silentlycrashing.ai3;

public class AI3EndPoint extends AI3Point {

    public AI3EndPoint() {
        super(0, 0);
    }

    @Override
    public void render() {
        p.endShape();
    }

}
