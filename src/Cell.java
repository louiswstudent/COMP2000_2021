import java.awt.*;
class Cell extends Rectangle {
    // fields
    static int size = 35;

    //constructors
    public Cell(int x, int y){
        super(x, y, size, size);
    }
    //methods
    void paint(Graphics g, Point mousePos){
        if(mousePos==null){ //npe aversion
            g.setColor(Color.RED);  
        }else if(this.contains(mousePos)){ 
            g.setColor(Color.GRAY);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }
}