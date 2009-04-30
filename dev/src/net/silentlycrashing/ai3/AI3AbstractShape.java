package net.silentlycrashing.ai3;

import java.util.*;
import processing.core.*;

public class AI3AbstractShape extends AI3 {

    public float x;
    public float y;
    public float w;
    public float h;
    
    public int color;

    public List<AI3Point> pts;

    public AI3AbstractShape() {
        pts = new ArrayList<AI3Point>();  
        x = 0;
        y = 0;
        color = 0;
    }
    
    public AI3AbstractShape(AI3Shape _shape) {
        pts = new ArrayList<AI3Point>();  
        
        x = _shape.x;
        y = _shape.y;
        w = _shape.w;
        h = _shape.h;
        
        color = _shape.color;
        
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
        p.noStroke();
        p.fill(color);
        
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
    
    public void setColor(int _r, int _g, int _b) {
        if (_r > 255) _r = 255; else if (_r < 0) _r = 0;
        if (_g > 255) _g = 255; else if (_g < 0) _g = 0;
        if (_b > 255) _b = 255; else if (_b < 0) _b = 0;

        setColor(0xFF000000 | (_r << 16) | (_g << 8) | _b);
    }
    
    public void setColor(int _r, int _g, int _b, int _a) {
        if (_a > 255) _a = 255; else if (_a < 0) _a = 0;
        if (_r > 255) _r = 255; else if (_r < 0) _r = 0;
        if (_g > 255) _g = 255; else if (_g < 0) _g = 0;
        if (_b > 255) _b = 255; else if (_b < 0) _b = 0;

        setColor((_a << 24) | (_r << 16) | (_g << 8) | _b);
    }
    
    public void setColor(int _hex) {
        color = _hex;
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
