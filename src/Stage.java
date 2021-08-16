import java.awt.Graphics;
import java.awt.*;
import java.util.Optional;
import java.util.ArrayList;
public class Stage {
    Grid grid;
    Actor train;
    Actor car;
    Actor boat;
    ArrayList<Actor> actors;

    public Stage() {
        grid = new Grid();
        actors = new ArrayList<Actor>();
        actors.add(new Train(grid.cellAtColRow(0, 0)));
        actors.add(new Car(grid.cellAtColRow(0, 15)));
        actors.add(new Boat(grid.cellAtColRow(12, 9)));
    }

    public void paint(Graphics g, Point mouseLoc) {
        Cell cell = grid.cellAtPoint(mouseLoc).orElse(grid.cells[0][0]);
        g.setColor(Color.BLACK);
        g.drawString("cell at " + cell.x + ", " + cell.y,730,20);
        
        grid.paint(g, mouseLoc);
        Actor actor;
        for (int i=0; i<actors.size(); i++) {
            actor = actors.get(i);
            actor.paint(g);
            if (actor.loc==cell) {
                g.drawString(actor.getClass().getName(), 730,50);
                g.setColor(Color.BLACK);
                g.drawString("at cell",760,50);
            }
        }
    }
}
