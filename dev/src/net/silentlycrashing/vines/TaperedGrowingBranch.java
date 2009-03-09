package net.silentlycrashing.vines;

import java.util.*;

public class TaperedGrowingBranch extends TaperedBranch {

    //-----------------------------------------------------
    public final static float DEFAULT_GROW_SPEED = .2f;

    //-----------------------------------------------------
    private float growSpeed = DEFAULT_GROW_SPEED;
    private float currMinWidth = 0;
    private float currMaxWidth = 0;

    //-----------------------------------------------------
    public TaperedGrowingBranch(float _x, float _y, float _a) {
        this(DEFAULT_RES, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE);
    }

    //-----------------------------------------------------
    public TaperedGrowingBranch(int _res, float _x, float _y, float _a) {
        this(_res, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE);
    }

    //-----------------------------------------------------
    public TaperedGrowingBranch(float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle) {
        this(DEFAULT_RES, _x, _y, _a, _numPts, _segLength, _segAngle);
    }

    //-----------------------------------------------------
    public TaperedGrowingBranch(int _res, float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle) {
        super(_res, _x, _y, _a, _numPts, _segLength, _segAngle);
    }

    //-----------------------------------------------------
    public void initTaper(int _minWidth, int _maxWidth, float _growSpeed) {
        super.initTaper(_minWidth, _maxWidth);
        growSpeed = _growSpeed;
    }
    
    //-----------------------------------------------------
    @Override
    protected void resize() {
        sizeSlice = (currMaxWidth - currMinWidth) / (float)Math.max(1, vertices.size());
        
        int i = vertices.size() - 1;
        for (ListIterator<VinePoint> li = vertices.listIterator(vertices.size()); li.hasPrevious();) {
            VinePoint vp = li.previous();
            float s = sizeSlice * (vertices.size() - i) + currMinWidth;
            vp.s = s;
            i--;
        }
        
        currMinWidth = Math.min(minWidth, currMinWidth + growSpeed);
        currMaxWidth = Math.min(maxWidth, currMaxWidth + growSpeed);
    }

    //-----------------------------------------------------
    @Override
    public void render() {
        super.render();
        resize();
    }
}
