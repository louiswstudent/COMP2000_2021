import java.awt.*;

public class Car extends Actor {
    public Car(Cell inLoc) {
        loc = inLoc;
        color = new Color(148, 33, 146); // or we could use Color.MAGENTA
    }
}
