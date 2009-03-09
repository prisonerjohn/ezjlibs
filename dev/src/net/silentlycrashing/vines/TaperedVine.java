package net.silentlycrashing.vines;

import java.util.*;
import processing.core.*;

public class TaperedVine extends BasicVine {

    public final static int DEFAULT_MIN_WIDTH = 1;
    public final static int DEFAULT_MAX_WIDTH = 10;
    public final static int DEFAULT_END_LENGTH = 100;

    protected int minWidth = DEFAULT_MIN_WIDTH;
    protected int maxWidth = DEFAULT_MAX_WIDTH;

    protected int endLength = DEFAULT_END_LENGTH;
    protected float sizeSlice;

    //-----------------------------------------------------
    public TaperedVine() {
        this(DEFAULT_RES, true);
    }

    //-----------------------------------------------------
    public TaperedVine(int _res) {
        this(_res, true);
    }

    //-----------------------------------------------------
    public TaperedVine(int _res, boolean _useLeastPoints) {
        super(_res, _useLeastPoints);
    }

    //-----------------------------------------------------
    public void initTaper(int _minWidth, int _maxWidth) {
        initTaper(_minWidth, _maxWidth, 0);
    }

    //-----------------------------------------------------
    public void initTaper(int _minWidth, int _maxWidth, int _endLength) {
        minWidth = _minWidth;
        maxWidth = _maxWidth;
        endLength = _endLength;
        if (endLength > 0) {
            sizeSlice = (maxWidth - minWidth) / (float)endLength;
        }
    }

    //-----------------------------------------------------
    @Override
    public boolean addPoint(PVector _pt) {
        if (super.addPoint(_pt)) {
            // re-calculate all the sizes
            if (endLength == 0) {
                resizeNoEndLength();
            } else {
                resizeWithEndLength();
            }

            return true;
        }

        return false;
    }

    //-----------------------------------------------------
    @Override
    public void render() {
        for (VinePoint vp : vertices) {
            p.ellipse(vp.x, vp.y, vp.s, vp.s);
        }
    }
    
    //-----------------------------------------------------
    private void resizeNoEndLength() {
        sizeSlice = (maxWidth - minWidth) / (float)Math.max(1, vertices.size());
        
        int i = vertices.size() - 1;
        for (ListIterator<VinePoint> li = vertices.listIterator(vertices.size()); li.hasPrevious();) {
            VinePoint vp = li.previous();
            float s = sizeSlice * (vertices.size() - i) + minWidth;
            if (Math.abs(vp.s - s) < 0.001f) {
                break;
            }
            vp.s = s;
            i--;
        }
    }

    //-----------------------------------------------------
    private void resizeWithEndLength() {
        int i = vertices.size() - 1;
        for (ListIterator<VinePoint> li = vertices.listIterator(vertices.size()); li.hasPrevious();) {
            VinePoint vp = li.previous();
            float s = (i < (vertices.size() - endLength))? maxWidth : sizeSlice * (vertices.size() - i) + minWidth;
            if (vp.s == s) {
                break;
            }
            vp.s = s;
            i--;
        }
    }
}
