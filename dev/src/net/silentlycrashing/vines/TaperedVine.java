package net.silentlycrashing.vines;

import processing.core.*;

public class TaperedVine extends LeastPointsVine {
    
    public final static int DEFAULT_MIN_WIDTH = 1;
    public final static int DEFAULT_MAX_WIDTH = 10;
    
    protected int minWidth;
    protected int maxWidth;

    public TaperedVine() {
        this(DEFAULT_RES, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH);
    }

    public TaperedVine(int _res) {
        this(_res, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH);
    }

    public TaperedVine(int _minDistance, int _maxDistance, float _deltaSlope) {
        this(DEFAULT_RES, _minDistance, _maxDistance, _deltaSlope, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH);
    }

    public TaperedVine(int _res, int _minDistance, int _maxDistance, float _deltaSlope) {
        this(_res, _minDistance, _maxDistance, _deltaSlope, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH);
    }
    
    public TaperedVine(int _res, int _minWidth, int _maxWidth) {
        this(_res, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, _minWidth, _maxWidth);
    }
    
    public TaperedVine(int _minWidth, int _maxWidth) {
        this(DEFAULT_RES, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, _minWidth, _maxWidth);
    }
    
    public TaperedVine(int _res, int _minDistance, int _maxDistance, float _deltaSlope, int _minWidth, int _maxWidth) {
        super(_res, _minDistance, _maxDistance, _deltaSlope);
        
        minWidth = _minWidth;
        maxWidth = _maxWidth;
    }
    
    @Override
    public void render() {
        float d = (maxWidth - minWidth) / (float)Math.max(1, vertices.size());
        for (int i = 0; i < vertices.size(); i++) {
            PVector v = vertices.get(i);
            float diam = d * (vertices.size() - i) + minWidth;
            p.ellipse(v.x, v.y, diam, diam);
        }
    }
}
