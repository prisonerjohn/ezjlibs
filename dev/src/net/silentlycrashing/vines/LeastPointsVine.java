package net.silentlycrashing.vines;

import processing.core.PVector;

public class LeastPointsVine extends BasicVine {
    
    public final static int DEFAULT_MIN_DIST = 30;
    public final static int DEFAULT_MAX_DIST = 60;
    public final static float DEFAULT_D_SLOPE = 0.5f;
    
    private int minDistance;
    private int maxDistance;
    private float deltaSlope;
    
    protected PVector lastPt;
    protected PVector beforeLastPt;
    
    public LeastPointsVine() {
        this(DEFAULT_RES, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE);
    }

    public LeastPointsVine(int _res) {
        this(_res, DEFAULT_MIN_DIST, DEFAULT_MAX_DIST, DEFAULT_D_SLOPE);       
    }
    
    public LeastPointsVine(int _minDistance, int _maxDistance, float _deltaSlope) {
        this(DEFAULT_RES, _minDistance, _maxDistance, _deltaSlope);     
    }

    public LeastPointsVine(int _res, int _minDistance, int _maxDistance, float _deltaSlope) {
        super(_res);
        
        minDistance = _minDistance;
        maxDistance = _maxDistance;
        deltaSlope = _deltaSlope;  
        
        lastPt = new PVector();
        beforeLastPt = new PVector();
    }
    
    @Override
    public boolean addPoint(PVector _pt) {
        // if the new point is far enough from the last key point...
        float d = lastPt.dist(_pt);
        if (d > minDistance) {
            // ...AND there are less than 2 key points total
            if (numPts < 2 || 
                    // ...OR the new point is too far away from the last point
                    d < maxDistance ||
                    // ...OR the slope between the new point and the last key point is 
                    //    different enough from the slope between the last 2 key points...
                    Math.abs(slope(_pt, lastPt) - slope(lastPt, beforeLastPt)) > deltaSlope) {
                // ...add a new key point
                beforeLastPt = lastPt;
                lastPt = _pt;
                return super.addPoint(_pt);
            }
        }
        
        return false;
    }
    
    @Override
    public void clear() {
        lastPt = new PVector();
        beforeLastPt = new PVector();
        super.clear();
    }
    
    private static float slope(PVector _v1, PVector _v2) {
        return slope(_v1.x, _v1.y, _v2.x, _v2.y);
    }
    
    private static float slope(float _x1, float _y1, float _x2, float _y2) {
        if (_x2 - _x1 == 0) return 0;  // avoid division by 0!
        return (_y2 - _y1) / (_x2 - _x1);
    }
}
