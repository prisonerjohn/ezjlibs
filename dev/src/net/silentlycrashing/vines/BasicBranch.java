package net.silentlycrashing.vines;

import processing.core.*;

public class BasicBranch extends BasicVine {
    
    public final static int DEFAULT_NUM_PTS = 10;
    public final static int DEFAULT_S_LENGTH = 30;
    public final static float DEFAULT_S_ANGLE = PApplet.radians(5);
    
    /*int x;
    int y;
    int a;
    
    int numPts;
    int segLength;
    int offsetAng;*/
    
    public BasicBranch(float _x, float _y, float _a) {
        this(DEFAULT_RES, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE);
    }

    public BasicBranch(int _res, float _x, float _y, float _a) {
        this(_res, _x, _y, _a, DEFAULT_NUM_PTS, DEFAULT_S_LENGTH, DEFAULT_S_ANGLE);       
    }  
    
    public BasicBranch(float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle) {
        this(DEFAULT_RES, _x, _y, _a, _numPts, _segLength, _segAngle);
    }
    
    public BasicBranch(int _res, float _x, float _y, float _a, int _numPts, int _segLength, float _segAngle) {
        super(_res);
        
        // generate the branch points
        float currX = _x;
        float currY = _y;
        float currA = _a; PApplet.println(" --"+PApplet.degrees(currA));
        addPoint(new PVector(currX, currY));
        
        for (int i=0; i < _numPts; i++) {
          currX += Math.cos(currA) * _segLength;
          currY += Math.sin(currA) * _segLength;
          addPoint(new PVector(currX, currY));
          currA += _segAngle;
        }     
    }
}
