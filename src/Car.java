import java.awt.*;
import java.util.ArrayList;
public class Car extends Actor {
    public Car(Cell inLoc) {
        loc = inLoc;
        color = new Color(148, 33, 146); // or we could use Color.MAGENTA
        body = new ArrayList<Polygon>();
         // Local Namespace 
         int x0 = loc.x; // x-startpos
         int y0 = loc.y; // y-startpos
         int w = loc.width; // width
         int h = loc.height; // height
         int x10 = w/10;
         int y10 = h/10;
         // Body
         Polygon poly = new Polygon();
         poly.addPoint(x0+x10, y0+y10*5);
         poly.addPoint(x0+x10*3, y0+y10*5);
         poly.addPoint(x0+x10*4, y0+y10*2);
         poly.addPoint(x0+x10*8, y0+y10*2);
         poly.addPoint(x0+x10*9, y0+y10*5);
         poly.addPoint(x0+x10*11, y0+y10*5);
         poly.addPoint(x0+x10*11, y0+y10*8);
         poly.addPoint(x0+x10, y0+y10*8);
         body.add(poly);
         
        // Wheels
         Polygon front = new Polygon();
         Polygon back = new Polygon();
         double circleX;
         double circleY;
         int sides = 6;
         int angle = 180/sides;
         int r = 2; // radius
         for(int s=0; s<=sides; s++) {
            circleX = x10*(-r + 2*s*r/sides);
            circleY = y10*(r*Math.sin(Math.toRadians(s*angle)));
            front.addPoint(x0 + x10*4 + (int) circleX, y0+y10*8 + (int) circleY);
            back.addPoint(x0 + x10*9 + (int) circleX, y0+y10*8 + (int) circleY);
         }
        body.add(front);
        body.add(back);
    }
}
