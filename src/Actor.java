import java.awt.*;
import java.util.ArrayList;
public abstract class Actor {
    Color color;
    Cell loc;
    ArrayList<Polygon> body;

    public void paint(Graphics g) {
        g.setColor(color);
       // g.fillRect(loc.x + 5, loc.y + 5, loc.width - 10, loc.height - 10);
        for (int i=0; i<body.size();i++) 
            g.fillPolygon(body.get(i));
       // g.setColor(Color.GRAY);
        //g.drawRect(loc.x + 5, loc.y + 5, loc.width - 10, loc.height - 10);
    }
}
