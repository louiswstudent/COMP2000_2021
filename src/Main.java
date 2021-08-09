import javax.swing.*;
import java.awt.*;
class Main extends JFrame {
    class App extends JPanel {
        Grid grid; // not needed anymore?
        Actor[] actors;// not needed anymore?
        Stage stage;
        public App() {
            setPreferredSize(new Dimension(720, 720));
            // the simple definition of our 'stage'
            grid = new Grid();
            actors = new Actor[3];
            actors[0] = new Train(grid.cells[1][1]);
            actors[1] = new Car(grid.cells[2][2]);
            actors[2] = new Boat(grid.cells[3][3]);
            //load our implementation to stage
            stage = new Stage(grid, actors);
        }

        @Override
        public void paint(Graphics g) {
            stage.paint(g, getMousePosition());
        }
    }
    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            this.repaint();
        }
    }
}