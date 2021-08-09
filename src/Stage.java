import java.awt.*;
class Stage {
    Grid grid;
    Actor[] actors; // Actor classes?
    public Stage(Grid g, Actor[] acs){
        this.grid = g;
        this.actors=acs;
    }

    // methods
    public void paint(Graphics g, Point mousePos){
        grid.paint(g, mousePos);
        for(int i = 0; i < actors.length; i++){
            actors[i].paint(g, mousePos);
        }
    }
}
