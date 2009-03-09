package net.silentlycrashing.vines;

import java.util.*;
import processing.core.*;

public class TaperedBranch extends BasicBranch {

    //-----------------------------------------------------
    public final static int DEFAULT_MIN_WIDTH = 1;
    public final static int DEFAULT_MAX_WIDTH = 10;

    //-----------------------------------------------------
    protected int minWidth = DEFAULT_MIN_WIDTH;
    protected int maxWidth = DEFAULT_MAX_WIDTH;
    protected float sizeSlice;

    //-----------------------------------------------------
    public TaperedBranch(float _x, float _y, float _a) {
        this(DEFAULT_RES, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE);
    }

    //-----------------------------------------------------
    public TaperedBranch(int _res, float _x, float _y, float _a) {
        this(_res, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE);
    }

    //-----------------------------------------------------
    public TaperedBranch(float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle) {
        this(DEFAULT_RES, _x, _y, _a, _numPts, _segLength, _segAngle);
    }

    //-----------------------------------------------------
    public TaperedBranch(int _res, float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle) {
        super(_res, _x, _y, _a, _numPts, _segLength, _segAngle);
        resize();
    }

    //-----------------------------------------------------
    public void initTaper(int _minWidth, int _maxWidth) {
        minWidth = _minWidth;
        maxWidth = _maxWidth;
        resize();
    }

    //-----------------------------------------------------
    @Override
    public boolean addPoint(PVector _pt) {
        super.addPoint(_pt);
        resize();

        return true;
    }

    //-----------------------------------------------------
    @Override
    public void render() {
        for (VinePoint vp : vertices) {
            p.ellipse(vp.x, vp.y, vp.s, vp.s);
        }
    }

    //-----------------------------------------------------
    protected void resize() {
        sizeSlice = (maxWidth - minWidth) / (float)Math.max(1, vertices.size());

        int i = vertices.size() - 1;
        for (ListIterator<VinePoint> li = vertices.listIterator(vertices.size()); li.hasPrevious();) {
            VinePoint vp = li.previous();
            float s = sizeSlice * (vertices.size() - i) + minWidth;
            vp.s = s;
            i--;
        }
    }
}
