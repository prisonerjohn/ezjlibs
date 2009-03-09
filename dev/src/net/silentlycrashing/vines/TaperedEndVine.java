package net.silentlycrashing.vines;

import processing.core.*;

public class TaperedEndVine extends TaperedVine {
    
    public final static int DEFAULT_END_LENGTH = 100;
    
    protected int endLength;
    protected float d;
    
    public TaperedEndVine() {
        this(DEFAULT_RES, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_END_LENGTH);
    }

    public TaperedEndVine(int _res) {
        this(_res, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_END_LENGTH);
    }
    
    public TaperedEndVine(int _minWidth, int _maxWidth) {
        this(DEFAULT_RES, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, _minWidth, _maxWidth, DEFAULT_END_LENGTH);
    }

    public TaperedEndVine(int _minDistance, int _maxDistance, float _deltaSlope) {
        this(DEFAULT_RES, _minDistance, _maxDistance, _deltaSlope, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_END_LENGTH);
    }

    public TaperedEndVine(int _res, int _minDistance, int _maxDistance, float _deltaSlope) {
        this(_res, _minDistance, _maxDistance, _deltaSlope, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_END_LENGTH);
    }
    
    public TaperedEndVine(int _res, int _minWidth, int _maxWidth) {
        this(_res, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, _minWidth, _maxWidth, DEFAULT_END_LENGTH);
    }
    
    public TaperedEndVine(int _res, int _minDistance, int _maxDistance, float _deltaSlope, int _minWidth, int _maxWidth, int _endLength) {
        super(_res, _minDistance, _maxDistance, _deltaSlope, _minWidth, _maxWidth);
        
        endLength = _endLength;
        d = (maxWidth - minWidth) / (float)endLength;
    }

    @Override
    public void render() {
        for (int i = 0; i < vertices.size(); i++) {
            PVector v = vertices.get(i);
            if (i < vertices.size() - endLength) {
                p.ellipse(v.x, v.y, maxWidth, maxWidth);
            } else {
                float diam = d * (vertices.size() - i) + minWidth;
                p.ellipse(v.x, v.y, diam, diam);
            }
        }
    }
}
