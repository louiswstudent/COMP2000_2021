import java.awt.*;
import java.util.ArrayList;
public class Train extends Actor {
    public Train(Cell inLoc) {
        loc = inLoc;
        color = Color.RED;
        body = new ArrayList<Polygon>();
         // Local Namespace 
         int x0 = loc.x; // x-startpos
         int y0 = loc.y; // y-startpos
         int x = loc.width; // width
         int y = loc.height; // height
         // Body
         Polygon poly = new Polygon();
         poly.addPoint(x0+x/10, y0+y*6/20);
         poly.addPoint(x0+x*19/20, y0+y*6/20);
         poly.addPoint(x0+x*19/20, y0+y*16/20);
         poly.addPoint(x0+x/10, y0+y*16/20);
         body.add(poly);
         
        // Breaker
        poly = new Polygon();
        poly.addPoint(x0+x*2/10, y0+y*4/20);
        poly.addPoint(x0+x*6/20, y0+y*4/20);
        poly.addPoint(x0+x*6/20, y0+y*18/20);
        poly.addPoint(x0+x*2/10, y0+y*18/20);
        body.add(poly);

        // Wheels
         Polygon front = new Polygon();
         Polygon mid = new Polygon();
         Polygon back = new Polygon();
         Polygon top = new Polygon();
         double circleX;
         double circleY;
         int sides = 12;
         int angle = 180/sides;
         int r = 3; // radius
         for(int s=0; s<=sides; s++) {
            circleX = (-r + 2*s*r/sides)*x/20;
            circleY = (r*Math.sin(Math.toRadians(s*angle)))*y/20;
            front.addPoint(x0 + 5*x/20 + (int) circleX, y0+16*y/20 + (int) circleY);
            mid.addPoint(x0 + 9*x/20 + (int) circleX, y0+16*y/20 + (int) circleY);
            back.addPoint(x0 + 16*x/20 + (int) circleX, y0+16*y/20 + (int) circleY);
            top.addPoint(x0 + 16*x/20 + (int) circleX, y0+6*y/20 - (int) circleY);
         }
        body.add(front);
        body.add(back);
        body.add(mid);
        body.add(top);
    }
}
