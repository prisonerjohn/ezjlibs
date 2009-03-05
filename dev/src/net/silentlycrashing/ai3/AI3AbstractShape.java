package net.silentlycrashing.ai3;

import java.util.*;
import processing.core.*;

public class AI3AbstractShape {

    public int x;
    public int y;
    public int w;
    public int h;

    public ArrayList<AI3Point> pts;

    public AI3AbstractShape() {
        pts = new ArrayList<AI3Point>();  
    }
    
    public AI3AbstractShape(AI3Shape _shape) {
        pts = new ArrayList<AI3Point>();
        
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

    public void render(PApplet _p) {
        for (AI3Point pt : pts) {
            pt.render(_p);
        }  
    }
}
