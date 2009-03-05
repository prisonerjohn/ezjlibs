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

    public void render(PApplet _p) {
        for (int i=0; i < pts.size(); i++) {
            pts.get(i).render(_p);
        }  
    }
}
