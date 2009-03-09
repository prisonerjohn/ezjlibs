package net.silentlycrashing.vines;

import processing.core.*;

public class TaperedBranch extends BasicBranch {
    
    public final static int DEFAULT_MIN_WIDTH = 1;
    public final static int DEFAULT_MAX_WIDTH = 10;
    
    protected int minWidth;
    protected int maxWidth;

    public TaperedBranch(float _x, float _y, float _a) {
        this(DEFAULT_RES, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH);
    }

    public TaperedBranch(int _res, float _x, float _y, float _a) {
        this(_res, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH);
    }
    
    public TaperedBranch(float _x, float _y, float _a,  int _minWidth, int _maxWidth) {
        this(DEFAULT_RES, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH);
    }

    public TaperedBranch(float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle) {
        this(DEFAULT_RES, _x, _y, _a, _numPts, _segLength, _segAngle, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH);
    }

    public TaperedBranch(int _res, float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle, int _minWidth, int _maxWidth) {
        super(_res, _x, _y, _a, _numPts, _segLength, _segAngle);
        
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
