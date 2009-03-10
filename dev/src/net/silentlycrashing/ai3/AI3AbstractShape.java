package net.silentlycrashing.ai3;

import java.util.*;
import processing.core.*;

public class AI3AbstractShape extends AI3 {

    public float x;
    public float y;
    public float w;
    public float h;

    public List<AI3Point> pts;

    public AI3AbstractShape() {
        pts = Collections.synchronizedList(new ArrayList<AI3Point>());  
        x = 0;
        y = 0;
    }
    
    public AI3AbstractShape(AI3Shape _shape) {
        pts = Collections.synchronizedList(new ArrayList<AI3Point>());  
        
        x = _shape.x;
        y = _shape.y;
        w = _shape.w;
        h = _shape.h;
        
        for (AI3Point pt : _shape.pts) {
            if (pt instanceof AI3BeginPoint) {
                pts.add(new AI3BeginPoint(pt.x, pt.y));
            } else if (pt instanceof AI3EdgePoint) {
                pts.add(new AI3EdgePoint(pt.x, pt.y));
            } else if (pt instanceof AI3BezierPoint) {
                pts.add(new AI3BezierPoint(pt.x, pt.y, ((AI3BezierPoint)pt).cx1, ((AI3BezierPoint)pt).cy1, ((AI3BezierPoint)pt).cx2, ((AI3BezierPoint)pt).cy2));
            } else if (pt instanceof AI3EndPoint) {
                pts.add(new AI3EndPoint());
            }
        }
    }
    
    public void render() {
        render(0, 0);
    }

    public void render(float _rx, float _ry) {
        p.pushMatrix();
        p.translate(-x, -y);
        p.translate(_rx, _ry);
        
        for (AI3Point pt : pts) {
            pt.render();
        }
        
        p.popMatrix();
    }
    
    public void setAnchor(PVector _pos) {
        setAnchor(_pos.x, _pos.y);
    }
    
    public void setAnchor(float _x, float _y) {
        x = _x;
        y = _y;
    }
    
    public void info() {
        PApplet.println("-------------- AI3Shape INFO -------------");
        for (AI3Point pt : pts) {
            if (pt instanceof AI3BeginPoint) {
                PApplet.println("-\tBegin\t(" + pt.x + ",\t" + pt.y + ")");
            } else if (pt instanceof AI3EdgePoint) {
                PApplet.println("-\tEdge\t(" + pt.x + ",\t" + pt.y + ")");
            } else if (pt instanceof AI3BezierPoint) {
                PApplet.println("-\tBezier\t(" + pt.x + ",\t" + pt.y + ")\t(" + ((AI3BezierPoint)pt).cx1 + ",\t" + ((AI3BezierPoint)pt).cy1 + ")\t(" + ((AI3BezierPoint)pt).cx2 + ",\t" + ((AI3BezierPoint)pt).cy2 + ")");
            } else if (pt instanceof AI3EndPoint) {
                PApplet.println("-\tEnd");
            }
        }       
        PApplet.println("------------------------------------------");
    }
}
