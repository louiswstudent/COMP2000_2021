import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

class Cell extends Rectangle {
    Terrain terrain;
    static int size = 35;
    Color clr;
    char col;
    int row;


    public Cell(char inCol, int inRow, int inX, int inY) {
        super(inX, inY, size, size);
        col = inCol;
        row = inRow;

        double t = Math.random();
        
        
        if (t<=0.1) {
            terrain = new Road();
        } else if (t<=0.3) {
            terrain = new Water();
        } else if (t<=0.7) {
            terrain = new Grass();
        } else if (t<=0.95) {
            terrain = new Mountain();
        } else {
            terrain = new Building();
        }
        clr = terrain.getColor();
    }

    void paint(Graphics g, Point mousePos) {
        if(contains(mousePos)) {
            g.setColor(clr.darker()); // Darker, seems to just make it grey. SO in a future use, we can pull the HSV from the terrain entity.
            terrain.displayElevation(g); // -  And multiply the V by a coefficient to create the dimming effect.
            // DisplayElevation should be reconsidered in future placement.
        } else {
            g.setColor(clr);
        }
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }

    @Override
    public boolean contains(Point p) {
        if (p != null) {
            return(super.contains(p));
        } else {
            return false;
        }
    }
}