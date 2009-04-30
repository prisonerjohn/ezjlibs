package net.silentlycrashing.ai3;

import processing.core.*;

public class AI3Tween extends AI3Shape {
    
    public AI3Shape start;
    public AI3Shape target;
    
    public AI3Tween(AI3Shape _start, AI3Shape _target) {
        super(_start);
        start = _start;
        target = _target;
    }
    
    public void tween(float ratio) {
        // tween the color
        color = p.lerpColor(start.color, target.color, ratio);
        
        // move the anchor point
        x = PApplet.lerp(start.x, target.x, ratio);
        y = PApplet.lerp(start.y, target.y, ratio);
        
        // move the contour points
        pts.clear();
        
        for (int i=0; i < start.pts.size(); i++) {
            AI3Point pt1 = start.pts.get(i);
            AI3Point pt2 = target.pts.get(i);
            if (pt1 instanceof AI3BeginPoint && pt2 instanceof AI3BeginPoint) {
                float x1 = PApplet.lerp(pt1.x, pt2.x, ratio);
                float y1 = PApplet.lerp(pt1.y, pt2.y, ratio);
                pts.add(new AI3BeginPoint(x1, y1));
            }

            else if (pt1 instanceof AI3EdgePoint && pt2 instanceof AI3EdgePoint) {
                float x1 = PApplet.lerp(pt1.x, pt2.x, ratio);
                float y1 = PApplet.lerp(pt1.y, pt2.y, ratio);
                pts.add(new AI3EdgePoint(x1, y1));
            }

            else if (pt1 instanceof AI3BezierPoint && pt2 instanceof AI3BezierPoint) {
                AI3BezierPoint pt1b = (AI3BezierPoint)pt1;
                AI3BezierPoint pt2b = (AI3BezierPoint)pt2;
                float x2  = PApplet.lerp(pt1b.x, pt2b.x, ratio);
                float y2  = PApplet.lerp(pt1b.y, pt2b.y, ratio);
                float cx1 = PApplet.lerp(pt1b.cx1, pt2b.cx1, ratio);
                float cy1 = PApplet.lerp(pt1b.cy1, pt2b.cy1, ratio);
                float cx2 = PApplet.lerp(pt1b.cx2, pt2b.cx2, ratio);
                float cy2 = PApplet.lerp(pt1b.cy2, pt2b.cy2, ratio);
                pts.add(new AI3BezierPoint(x2, y2, cx1, cy1, cx2, cy2));
            }

            else if (pt1 instanceof AI3EndPoint && pt2 instanceof AI3EndPoint) {
                pts.add(new AI3EndPoint());
            }
        }
    }
    
    public void setTarget(AI3Shape _target) {
        start = new AI3Shape(this);
        target = _target;
    }
}
