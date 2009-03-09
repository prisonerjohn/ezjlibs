package net.silentlycrashing.vines;

import java.util.*;

import processing.core.*;
import toxi.geom.*;

public class BasicVine extends Vines {
    
    //-----------------------------------------------------
    public final static int RECALC_PTS = 4;
    
    public final static int DEFAULT_RES = 4;
    public final static int DEFAULT_MIN_DIST = 30;
    public final static int DEFAULT_MAX_DIST = 60;
    public final static float DEFAULT_D_SLOPE = 0.5f;
    
    //-----------------------------------------------------
    public BernsteinPolynomial bernstein;
    
    protected int numPts;
    
    protected List<PVector> pts;
    
    protected List<PVector> delta;
    protected List<PVector> coeffA;
    protected List<Float> bi;
    
    protected List<VinePoint> vertices;
    
    private PVector deltaP;
    private PVector deltaQ;
    
    //-----------------------------------------------------
    // Least Points variables
    protected boolean useLeastPoints;
    
    private int minDistance = DEFAULT_MIN_DIST;
    private int maxDistance = DEFAULT_MAX_DIST;
    private float deltaSlope = DEFAULT_D_SLOPE;
    
    protected PVector lastPt;
    protected PVector beforeLastPt;
    
    //-----------------------------------------------------
    public BasicVine() {
        this(DEFAULT_RES, true);
    }
    
  //-----------------------------------------------------
    public BasicVine(int _res) {
        this(_res, true);
    }
    
    //-----------------------------------------------------
    public BasicVine(int _res, boolean _useLeastPoints) {
        useLeastPoints = _useLeastPoints;
        
        bernstein = new BernsteinPolynomial(_res);
        clear();
        
        deltaP = new PVector();
        deltaQ = new PVector();
    }
    
    //-----------------------------------------------------
    public void initLeastPoints(int _minDistance, int _maxDistance, float _deltaSlope) {
        minDistance = _minDistance;
        maxDistance = _maxDistance;
        deltaSlope = _deltaSlope;
        useLeastPoints = true;
    }
    
    //-----------------------------------------------------
    public boolean addPoint(PVector _pt) {
        if (useLeastPoints) {
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
                    
                    addPointAndUpdate(_pt);
                    return true;
                }
            }

            return false;
            
        } else {
            addPointAndUpdate(_pt);
            return true;
        }
    }
    
    //-----------------------------------------------------
    public void render() {
        p.beginShape();
        for (VinePoint vp : vertices) {
            p.vertex(vp.x, vp.y);
        }
        p.endShape();
    }
    
    //-----------------------------------------------------
    public void clear() {
        pts = new LinkedList<PVector>();
        delta = new LinkedList<PVector>();
        coeffA = new LinkedList<PVector>();
        bi = new LinkedList<Float>();
        vertices = new LinkedList<VinePoint>();
        numPts = 0;
        
        lastPt = new PVector();
        beforeLastPt = new PVector();
    }
    
    //-----------------------------------------------------
    public int getNumPoints() {
        return numPts;
    }
    
    //-----------------------------------------------------
    private void addPointAndUpdate(PVector _pt) {
        // add the point
        pts.add(_pt);
        numPts++;
        
        updateCPoints(_pt);
        updateVertices();
    }
    
    //-----------------------------------------------------
    private void updateCPoints(PVector _pt) {
        if (numPts == 1) {
            delta.add(new PVector());
            coeffA.add(new PVector());
            bi.add(new Float(0)); 
        } 
        
        else if (numPts == 2) {
            delta.add(new PVector());  // dummy value for now
            bi.add(new Float(-.25f));
        }
        
        else if (numPts == 3) {
            delta.add(new PVector());  // dummy value for now
            
            // set the coefficient for point 1
            PVector p0 = pts.get(0);
            PVector d0 = delta.get(0);
            coeffA.add(new PVector(
                (_pt.x - p0.x - d0.x) * .25f,
                (_pt.y - p0.y - d0.y) * .25f
            ));    
        } 
        
        else if (numPts > 3) {
            delta.add(new PVector());  // dummy value for now
            
            // set the bi for the last point
            float b = -1 / (4 + bi.get(numPts - 3));
            bi.add(new Float(b));
            
            // set the coefficient for the last point
            PVector pPre = pts.get(pts.size() - 3);
            PVector cPre = coeffA.get(pts.size() - 3);
            coeffA.add(new PVector(
                -(_pt.x - pPre.x - cPre.x) * b,
                -(_pt.y - pPre.y - cPre.y) * b
            ));
            
            // set the delta value for the last points until the first one
            for (int i = numPts - 2; i > Math.max(0, numPts - 2 - RECALC_PTS); i--) {
                PVector dCurr = delta.get(i);
                PVector cCurr = coeffA.get(i);
                PVector dPost = delta.get(i+1);
                float bCurr = bi.get(i);
                dCurr.set(
                    cCurr.x + dPost.x * bCurr,
                    cCurr.y + dPost.y * bCurr,
                    0
                );
            }    
        }  
    }
    
    //-----------------------------------------------------
    private void updateVertices() {
        if (numPts > RECALC_PTS) {
            // delete the vertices corresponding to the last RECALC_PTS pts
            // we re-calculate them below
            for (int i=0; i < (RECALC_PTS-1)*bernstein.resolution; i++) {
                vertices.remove(vertices.size()-1);
            }
        }
        
        for (int i = Math.max(0, numPts - (RECALC_PTS+1)); i < numPts - 1; i++) {
            PVector p = pts.get(i);
            PVector q = pts.get(i + 1);
            deltaP.set(delta.get(i));
            deltaP.add(p);
            deltaQ.set(q);
            deltaQ.sub(delta.get(i + 1));
            
            for (int k = 0; k < bernstein.resolution; k++) {
                float x = p.x * bernstein.b0[k] + deltaP.x * bernstein.b1[k]
                        + deltaQ.x * bernstein.b2[k] + q.x * bernstein.b3[k];
                float y = p.y * bernstein.b0[k] + deltaP.y * bernstein.b1[k]
                        + deltaQ.y * bernstein.b2[k] + q.y * bernstein.b3[k];
                vertices.add(new VinePoint(x, y));
            }
        }
    }
    
    //-----------------------------------------------------
    private static float slope(PVector _v1, PVector _v2) {
        return slope(_v1.x, _v1.y, _v2.x, _v2.y);
    }
    
    //-----------------------------------------------------
    private static float slope(float _x1, float _y1, float _x2, float _y2) {
        if (_x2 - _x1 == 0) return 0;  // avoid division by 0!
        return (_y2 - _y1) / (_x2 - _x1);
    }
}
