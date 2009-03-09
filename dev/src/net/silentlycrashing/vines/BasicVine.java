package net.silentlycrashing.vines;

import java.util.*;

import processing.core.*;
import toxi.geom.*;

public class BasicVine {
    
    public static PApplet p;
    public static void init(PApplet _p) {
        p = _p;
    }
    
    public final static int DEFAULT_RES = 4;
    
    public BernsteinPolynomial bernstein;
    
    protected int numPts;
    
    protected List<PVector> pts;
    
    protected List<PVector> delta;
    protected List<PVector> coeffA;
    protected List<Float> bi;
    
    protected List<PVector> vertices;
    
    private PVector deltaP;
    private PVector deltaQ;
    
    public BasicVine() {
        this(DEFAULT_RES);
    }
    
    public BasicVine(int _res) {
        bernstein = new BernsteinPolynomial(_res);
        clear();
        
        deltaP = new PVector();
        deltaQ = new PVector();
    }
    
    public boolean addPoint(PVector _pt) {
        // add the point
        pts.add(_pt);
        numPts++;
        
        updateCPoints(_pt);
        updateVertices();
        
        return true;
    }
    
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
            for (int i= numPts - 2; i > 0; i--) {
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
    
    private void updateVertices() {
        vertices.clear();
        
        for (int i = 0; i < numPts - 1; i++) {
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
                vertices.add(new PVector(x, y));
            }
        }
    }
    
    public void render() {
        p.beginShape();
        for (PVector v : vertices) {
            p.vertex(v.x, v.y);
        }
        p.endShape();
    }
    
    public void clear() {
        pts = new LinkedList<PVector>();
        delta = new LinkedList<PVector>();
        coeffA = new LinkedList<PVector>();
        bi = new LinkedList<Float>();
        vertices = new LinkedList<PVector>();
        numPts = 0;
    }
    
    public int getNumPoints() {
        return numPts;
    }
}
