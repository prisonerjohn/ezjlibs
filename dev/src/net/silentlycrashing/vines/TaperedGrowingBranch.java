package net.silentlycrashing.vines;

import processing.core.PVector;

public class TaperedGrowingBranch extends TaperedBranch {
    
    public final static float DEFAULT_GROW_SPEED = .2f;
    
    private float growSpeed;
    private float currMinWidth;
    private float currMaxWidth;

    public TaperedGrowingBranch(float _x, float _y, float _a) {
        this(DEFAULT_RES, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_GROW_SPEED);
    }
    
    public TaperedGrowingBranch(float _x, float _y, float _a, float _growSpeed) {
        this(DEFAULT_RES, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, _growSpeed);
    }

    public TaperedGrowingBranch(int _res, float _x, float _y, float _a) {
        this(_res, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_GROW_SPEED);
    }
    
    public TaperedGrowingBranch(int _res, float _x, float _y, float _a, float _growSpeed) {
        this(_res, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, _growSpeed);
    }
    
    public TaperedGrowingBranch(float _x, float _y, float _a,  int _minWidth, int _maxWidth) {
        this(DEFAULT_RES, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_GROW_SPEED);
    }
    
    public TaperedGrowingBranch(float _x, float _y, float _a,  int _minWidth, int _maxWidth, float _growSpeed) {
        this(DEFAULT_RES, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, _growSpeed);
    }
    
    public TaperedGrowingBranch(float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle, float _growSpeed) {
        this(DEFAULT_RES, _x, _y, _a, _numPts, _segLength, _segAngle, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, _growSpeed);
    }

    public TaperedGrowingBranch(int _res, float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle, int _minWidth, int _maxWidth) {
        this(_res, _x, _y, _a, _numPts, _segLength, _segAngle, _minWidth, _maxWidth, DEFAULT_GROW_SPEED);
    }
    
    public TaperedGrowingBranch(int _res, float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle, int _minWidth, int _maxWidth, float _growSpeed) {
        super(_res, _x, _y, _a, _numPts, _segLength, _segAngle, _minWidth, _maxWidth);
        growSpeed = _growSpeed;
        currMinWidth = 0;
        currMaxWidth = 0;
    }
    
    @Override
    public void render() {
        float d = (currMaxWidth - currMinWidth) / (float)Math.max(1, vertices.size());
        for (int i = 0; i < vertices.size(); i++) {
            PVector v = vertices.get(i);
            float diam = d * (vertices.size() - i) + currMinWidth;
            p.ellipse(v.x, v.y, diam, diam);
        }
        
        currMinWidth = Math.min(minWidth, currMinWidth + growSpeed);
        currMaxWidth = Math.min(maxWidth, currMaxWidth + growSpeed);
    }

}
