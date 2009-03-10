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
    
    public void scale(float _s) {
        for (AI3Point pt : pts) {
            if (pt instanceof AI3BeginPoint || pt instanceof AI3EdgePoint) {
                pt.x = PApplet.lerp(x, pt.x, _s);
                pt.y = PApplet.lerp(y, pt.y, _s);
            } else if (pt instanceof AI3BezierPoint) {
                AI3BezierPoint ptb = (AI3BezierPoint)pt;
                ptb.x = PApplet.lerp(x, ptb.x, _s);
                ptb.y = PApplet.lerp(y, ptb.y, _s);
                ptb.cx1 = PApplet.lerp(x, ptb.cx1, _s);
                ptb.cy1 = PApplet.lerp(y, ptb.cy1, _s);
                ptb.cx2 = PApplet.lerp(x, ptb.cx2, _s);
                ptb.cy2 = PApplet.lerp(y, ptb.cy2, _s);
            }
        }
    }
    
    public void info() {
        PApplet.println("-------------- AI3Shape INFO -------------");
                PApplet.println("--- Dimensions [" + w + ", " + h + "]");
        for (AI3Point pt : pts) {
            if (pt instanceof AI3BeginPoint) {
                PApplet.println("------ Begin   (" + PApplet.nfc(pt.x, 7) + ", " + PApplet.nfc(pt.y, 7) + ")");
            } else if (pt instanceof AI3EdgePoint) {
                PApplet.println("------ Edge    (" + PApplet.nfc(pt.x, 7) + ", " + PApplet.nfc(pt.y, 7) + ")");
            } else if (pt instanceof AI3BezierPoint) {
                PApplet.println("------ Bezier  (" + PApplet.nfc(pt.x, 7) + ", " + PApplet.nfc(pt.y, 7) + ")\t(" + PApplet.nfc(((AI3BezierPoint)pt).cx1, 7) + ", " + PApplet.nfc(((AI3BezierPoint)pt).cy1, 7) + ")\t(" + PApplet.nfc(((AI3BezierPoint)pt).cx2, 7) + ", " + PApplet.nfc(((AI3BezierPoint)pt).cy2, 7) + ")");
            } else if (pt instanceof AI3EndPoint) {
                PApplet.println("------ End");
            }
        }       
        PApplet.println("------------------------------------------");
    }
}
