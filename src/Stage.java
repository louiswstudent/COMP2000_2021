import java.awt.Graphics;
import java.awt.*;

public class Stage {
    Grid grid;
    Actor train;
    Actor car;
    Actor boat;

    public Stage() {
        grid = new Grid();
        train = new Train(grid.cellAtColRow(0, 0));
        car = new Car(grid.cellAtColRow(0, 15));
        boat = new Boat(grid.cellAtColRow(12, 9));
    }

    public void paint(Graphics g, Point mouseLoc) {
        grid.paint(g, mouseLoc);
        train.paint(g);
        car.paint(g);
        boat.paint(g);
    }
}
