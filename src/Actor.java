import java.awt.*;
// louis wellock 9/8/21
public interface Actor {
    public void paint(Graphics g, Point mousePos);
}
class Train implements Actor {
    static int size = 20;
    int os = (Cell.size-size)/2;
    Cell cell;
    public Train(Cell c) {
        cell=c;
    }

    public void paint(Graphics g, Point mousePos){
        int x = cell.x+os;
        int y = cell.y+os;
        g.setColor(Color.RED);
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }
}
class Car implements Actor {
    static int size = 20;
    int os = (Cell.size-size)/2;
    Cell cell;
    public Car(Cell c) {
        cell=c;
    }


    public void paint(Graphics g, Point mousePos){
        int x = cell.x+os;
        int y = cell.y+os;
        g.setColor(Color.magenta); // RGB for purple is like 40,0,75
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }
}
class Boat implements Actor {
    static int size = 20;
    int os = (Cell.size-size)/2;
    Cell cell;
    public Boat(Cell c) {
        cell=c;
    }

    public void paint(Graphics g, Point mousePos){
        int x = cell.x+os;
        int y = cell.y+os;
        g.setColor(Color.ORANGE);
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }
}
// class Car implements Actor {

// }
// class Boat implements Actor {

// }