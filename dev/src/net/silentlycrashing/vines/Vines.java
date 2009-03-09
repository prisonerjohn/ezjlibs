package net.silentlycrashing.vines;

import processing.core.*;

public class Vines {
    public static PApplet p;

    public static void init(PApplet _p) {
        p = _p;
        VinePoint.COUNT = 0;
    }
}
