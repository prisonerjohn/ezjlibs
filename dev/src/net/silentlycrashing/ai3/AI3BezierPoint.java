package net.silentlycrashing.ai3;

public class AI3BezierPoint extends AI3Point {

    public float cx1;
    public float cy1;
    public float cx2;
    public float cy2;

    public AI3BezierPoint(float _x, float _y, float _cx1, float _cy1, float _cx2, float _cy2) {
        super(_x, _y);

        cx1 = _cx1;
        cy1 = _cy1;
        cx2 = _cx2;
        cy2 = _cy2;
    }

    @Override
    public void render() {
        p.bezierVertex(cx1, cy1, cx2, cy2, x, y);
    }

}
