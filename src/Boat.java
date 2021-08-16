import java.awt.*;
import java.util.ArrayList;
public class Boat extends Actor {
    public Boat(Cell inLoc) {
        loc = inLoc;
        color = Color.ORANGE;
        body = new ArrayList<Polygon>();
        // Local Namespace 
        int x0 = loc.x + loc.width/2;
        int y0 = loc.y;
        int w = loc.width;
        int h = loc.height;
        // Mast
        Polygon poly = new Polygon();
        poly.addPoint(x0, y0);
        poly.addPoint(x0+ w/2, y0 + h/3);
        poly.addPoint(x0+ w/3, y0 + h/2);
        poly.addPoint(x0, y0 + h/2);
        body.add(poly);
        // Stick
        int xs = 3; // x-size
        int ys = h/2;
        y0 += h/3;
        poly = new Polygon();
        poly.addPoint(x0, y0);
        poly.addPoint(x0+ xs, y0);
        poly.addPoint(x0+ xs, y0 + ys);
        poly.addPoint(x0, y0 + ys);
        body.add(poly);
        // body
        x0 = loc.x;
        y0 = loc.y + h*3/4;
        xs = w;
        ys = h/5;
        poly = new Polygon();
        poly.addPoint(x0, y0);
        poly.addPoint(x0+ xs, y0);
        poly.addPoint(x0+ xs*3/4, y0 + ys);
        poly.addPoint(x0+ xs/4, y0 + ys);
        body.add(poly);
    }
}
