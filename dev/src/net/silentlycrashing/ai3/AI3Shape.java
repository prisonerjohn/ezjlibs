package net.silentlycrashing.ai3;

import processing.core.*;

public class AI3Shape extends AI3AbstractShape {
    
    public AI3Shape(AI3Shape _shape) {
        super(_shape);
    }
    
    public AI3Shape(String _filePath) {
        super();
        load(_filePath);
    }

    private void load(String _filePath) {
        String fullText = (PApplet.join(p.loadStrings(_filePath), "\n"));
        String[] dimensions = PApplet.split(PApplet.trim(PApplet.split(PApplet.split(fullText, "%%BoundingBox:")[1], "%%")[0]), " ");
        int tmpX = Integer.parseInt(dimensions[0]);
        int tmpY = Integer.parseInt(dimensions[1]);
        w = Integer.parseInt(dimensions[2]) - tmpX;
        h = Integer.parseInt(dimensions[3]) - tmpY;

        String data[] = PApplet.split(PApplet.trim(PApplet.split(PApplet.split(fullText, "%%EndSetup")[1], "%%PageTrailer")[0]), "\n");
        for (int i=0; i < data.length; i++) {
            String[] params = PApplet.split(data[i], " ");
            char mode = params[params.length - 1].toLowerCase().charAt(0);

            if (mode == 'm') {
                float x1 = Float.parseFloat(params[0]) - tmpX;
                float y1 = h - Float.parseFloat(params[1]) + tmpY;
                pts.add(new AI3BeginPoint(x1, y1));
            }

            else if (mode == 'l') {
                float x1 = Float.parseFloat(params[0]) - tmpX;
                float y1 = h - Float.parseFloat(params[1]) + tmpY;
                pts.add(new AI3EdgePoint(x1, y1));
            }

            else if (mode == 'c') {
                float cx1 = Float.parseFloat(params[0]) - tmpX;
                float cy1 = h - Float.parseFloat(params[1]) + tmpY;
                float cx2 = Float.parseFloat(params[2]) - tmpX;
                float cy2 = h - Float.parseFloat(params[3]) + tmpY;
                float x2  = Float.parseFloat(params[4]) - tmpX;
                float y2  = h - Float.parseFloat(params[5]) + tmpY;

                pts.add(new AI3BezierPoint(x2, y2, cx1, cy1, cx2, cy2));
            }

            else if (mode == 'f' || mode == 's') {
                pts.add(new AI3EndPoint());
            }
        }
    }
}
