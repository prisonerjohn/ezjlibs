package net.silentlycrashing.vines;

import java.util.*;
import processing.core.*;

public class TaperedEndBranchingVine extends TaperedEndVine {
    
    protected List<BasicBranch> branches;

    public TaperedEndBranchingVine() {
        this(DEFAULT_RES, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_END_LENGTH);
    }

    public TaperedEndBranchingVine(int _res) {
        this(_res, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_END_LENGTH);
    }

    public TaperedEndBranchingVine(int _minDistance, int _maxDistance, float _deltaSlope) {
        this(DEFAULT_RES, _minDistance, _maxDistance, _deltaSlope, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_END_LENGTH);
    }

    public TaperedEndBranchingVine(int _res, int _minDistance, int _maxDistance, float _deltaSlope) {
        this(_res, _minDistance, _maxDistance, _deltaSlope, DEFAULT_MIN_WIDTH, DEFAULT_MAX_WIDTH, DEFAULT_END_LENGTH);
    }

    public TaperedEndBranchingVine(int _minWidth, int _maxWidth) {
        this(DEFAULT_RES, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, _minWidth, _maxWidth, DEFAULT_END_LENGTH);
    }

    public TaperedEndBranchingVine(int _res, int _minWidth, int _maxWidth) {
        this(_res, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE, _minWidth, _maxWidth, DEFAULT_END_LENGTH);
    }

    public TaperedEndBranchingVine(int _res, int _minDistance, int _maxDistance, float _deltaSlope, int _minWidth, int _maxWidth, int _endLength) {
        super(_res, _minDistance, _maxDistance, _deltaSlope, _minWidth, _maxWidth, _endLength);
        branches = new LinkedList<BasicBranch>();
    }
    
    @Override
    public boolean addPoint(PVector _pt) {
        if (super.addPoint(_pt)) {
            if (pts.size()%5 == 0) {
                // add a branch
                float ang = PApplet.atan2(beforeLastPt.y - lastPt.y, beforeLastPt.x - lastPt.x) - PApplet.PI;
                branches.add(new TaperedGrowingBranch(10, lastPt.x, lastPt.y, ang, 5, 30, PApplet.radians(-5), 1, 10));
                branches.add(new TaperedGrowingBranch(10, lastPt.x, lastPt.y, ang, 5, 30, PApplet.radians(5), 1, 10));    
            }            
            return true;
        }
        
        return false;
    }
    
    @Override
    public void render() {
        // render the vine
        super.render();
        
        // render the branches
        for (BasicBranch b : branches) {
            b.render();
        }
        
    }

    @Override
    public void clear() {
        super.clear();
        if (branches != null) branches.clear();
    }
}
