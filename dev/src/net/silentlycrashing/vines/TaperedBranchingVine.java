package net.silentlycrashing.vines;

import java.util.*;
import processing.core.*;

public class TaperedBranchingVine extends TaperedVine {

    //-----------------------------------------------------
    protected List<BasicBranch> branches;

    //-----------------------------------------------------
    public TaperedBranchingVine() {
        this(DEFAULT_RES, true);
    }

    //-----------------------------------------------------
    public TaperedBranchingVine(int _res) {
        this(_res, true);
    }

    //-----------------------------------------------------
    public TaperedBranchingVine(int _res, boolean _useLeastPoints) {
        super(_res, _useLeastPoints);
        branches = new LinkedList<BasicBranch>();
    }

    //-----------------------------------------------------
    @Override
    public boolean addPoint(PVector _pt) {
        if (super.addPoint(_pt)) {
            if (pts.size()%5 == 0) {
                // add a branch
                float ang = PApplet.atan2(beforeLastPt.y - lastPt.y, beforeLastPt.x - lastPt.x) - PApplet.PI;
                branches.add(new TaperedGrowingBranch(10, lastPt.x, lastPt.y, ang, 10, 30, PApplet.radians(-5)));
                branches.add(new TaperedGrowingBranch(10, lastPt.x, lastPt.y, ang, 10, 30, PApplet.radians(5)));    
            }            
            return true;
        }

        return false;
    }

    //-----------------------------------------------------
    @Override
    public void render() {
        // render the vine
        super.render();

        // render the branches
        for (BasicBranch b : branches) {
            b.render();
        }
    }

    //-----------------------------------------------------
    @Override
    public void clear() {
        super.clear();
        if (branches != null) branches.clear();
    }
}
