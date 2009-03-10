package net.silentlycrashing.ai3;

public abstract class AI3Point extends AI3 {

    public float x;
    public float y;

    public AI3Point(float _x, float _y) {
        x = _x;
        y = _y;
    }

    public abstract void render();
}
