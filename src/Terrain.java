import java.awt.Graphics;
import java.awt.Color;
public abstract class Terrain {
    float elevation;
    float hue;
    float sat;

    public void displayElevation(Graphics g) { // Another case of something that might need to be CALLed at a higher hierarch (we call from Cell, but definitely should consider moving to Grid)
        g.setColor(Color.DARK_GRAY);
        g.drawString("Terrain Type: "+ this.toString(), 740, 50);
        g.drawString("Elevation: "+ (500 + (elevation)*5500) + "m", 740, 70); // Elevation range allowed: 500-6000
    }
    public Color getColor() { // Realistically, we only load this to Cell object once. But the method is more appropriate for future use... probably.
        return Color.getHSBColor(hue, sat, (float)(0.5 + elevation/2));
    }
}
