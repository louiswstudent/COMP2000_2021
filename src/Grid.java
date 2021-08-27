import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;
import java.util.function.Consumer;

class Grid {
    Cell[][] cells = new Cell[20][20];

    private int[] getEdgePoint() { // Returns a Cell value along the edge of the grid.
        int x = (int)Math.floor(Math.random()*20);
        int y = (int)Math.floor(Math.random()*20);
        int flat = 0;
        if (Math.random()<0.5) // 50% Chance for short or far side
            flat = 19;
        if (Math.random()<0.5) { // 50% Chance for top/bottom side.
            x = flat;
        } else
            y = flat;
        int[] p = new int[2];
        p[0]=x;
        p[1]=y;
        return p;
    }
    private int[] getEdgeNormal(int x, int y) {
        int[] n = new int[2];
        if (x==0) {
            n[0] = 1;
        } else if (x==19) {
            n[0] = -1;
        }
        if (y==0) {
            n[1] = 1;
        } else if (y==19) {
            n[1] = -1;
        }
        return n;
    }
    private int[] getEdgeNormal(int[] p) {
        return getEdgeNormal(p[0], p[1]);
    }
    private boolean pointInDomain(int[] b) {
       return (Math.max(0, Math.min(19, b[0]))==b[0] && Math.max(0, Math.min(19, b[1]))==b[1]);
    }

    public Grid() {
        for(int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(colToLabel(i), j, 10+35*i, 10+35*j);
            }
        }

        /* Terrain Generation (bonus / no marks)
            OCD was kicking in looking at the random map...

            Start by generating grass everywhere

            Useful code:
             Normal Vector [x,y] for each edge
            In a formation like this?
                 v v v
                >     <
                >     <
                 ^ ^ ^
            So we have bias for water streams and roads.

            Grass starts everywhere
            Mountains 
            Water randomly selects edge tiles (flip for X/Y dominant) | (i%2 * width) & (j%2 * height) (random increment based on random()*height floor)
            Generate float [x,y] with Direction of water. Make magnitude +/- 0.4-1.4 or we will take forever...
            Move across until the edge of the map is reached for those XY values.
            Any cell it crosses over (floor of x/y sum offset by startpos) is set to water.

            Road we randomly select Grass edge tiles and make a straight line across them
            5% chance we make an intersect with width (1-2) on the tangent vector.

            Along the sides of the road, if there's grass. We place a building at a 20% chance.

            Mountains we generate in corners and cluster them together. Ignoring everything, because they're the boss.

            Should we write all of this somewhere else? probably
        */
        int[] g = new int[2]; // goal
        int[] b; // base/current position
        int[] n; //the normal vector
        int p; // polarity
        Cell cell; // selected cell
        // Water streams
        for (int i=0;i<4;i++) { // generate 4 streams
            b  = getEdgePoint();
            n = getEdgeNormal(b);
           
            while (pointInDomain(b)) {
                cell = cells[b[0]][b[1]];
                cell.terrain = new Water();
                b[0] += Math.round(n[0]/1.5 + Math.random());
                b[1] += Math.round(n[1]/1.5 + Math.random());
            }
        }
         // Mountain clusters
        for (int i=0;i<3;i++) { // generate 3 mountain ranges.
            b = getEdgePoint();
            n = getEdgeNormal(b);
            int s = n[0]+n[1];
            for (int x=0;x<7;x++) { // Bongo-select method... Don't judge.
                g[0] = b[0]+s*(int)Math.round(Math.random()*4);
                g[1] = b[1]+s*(int)Math.round(Math.random()*4);
                if (pointInDomain(g)) {
                    cell = cells[g[0]][g[1]];
                    cell.terrain = new Mountain();
                }
            }
        }

        // now, a road.
        
        b = getEdgePoint();
        n = getEdgeNormal(b);
        while (pointInDomain(b)) {
            cell = cells[b[0]][b[1]];
            cell.terrain = new Road();
            b[0] += n[0];
            b[1] += n[1];
            if (Math.random()<=0.35) { //add a building
                p = 1; // polarity
                if (Math.random()<0.5)
                    p=-1;
                g[0] = b[0]+n[1]*p;
                g[1] = b[1]+n[0]*p;
                if (pointInDomain(g)) {
                    cell = cells[g[0]][g[1]];
                    cell.terrain = new Building();
                }
            }
            if (Math.random()<=0.2) { // Chance for a split road
                p = 1; // polarity
                if (Math.random()<0.5)
                    p=-1;
                int[] o = new int[2];// Idk how to replicate by value XD 
                o[0] = b[0];
                o[1] = b[1];
                while (pointInDomain(o)) {
                    cell = cells[o[0]][o[1]];
                    cell.terrain = new Road();
                    o[0] += n[1]*p;
                    o[1] += n[0]*p;
                } 
            }
        }

    }

    private char colToLabel(int col) {
        return (char) (col + 65);
    }

    private int labelToCol(char col) {
        return (int) col - 65;
    }

     /**
     * Takes a cell consumer (i.e. a function that has a single `Cell` argument and
     * returns `void`) and applies that consumer to each cell in the grid.
     * @param func The `Cell` to `void` function to apply at each spot.
     */
    public void doToEachCell(Consumer<Cell> func) {
        for(int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells[i].length; j++) { 
                func.accept(cells[i][j]);
            }
        }
      }

    public void paint(Graphics g, Point mousePos) {
        this.doToEachCell(cell -> cell.paint(g, mousePos));
        //.paint(g, mousePos);
    }

    private Optional<Cell> cellAtColRow(int c, int r) {
        if(c >= 0 && c < cells.length && r >= 0 && r < cells[c].length) {
            return Optional.of(cells[c][r]);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Cell> cellAtColRow(char c, int r) {
        return cellAtColRow(labelToCol(c), r);
    }
    public Optional<Cell> cellAtPoint(Point p) {
        for(int i=0; i < cells.length; i++) {
            for(int j=0; j < cells[i].length; j++) {
                if(cells[i][j].contains(p)) {
                    return Optional.of(cells[i][j]);
                }
            }
        }
        return Optional.empty();
    }
}