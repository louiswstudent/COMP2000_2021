import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;
import java.util.function.Consumer;

class Grid {
    Cell[][] cells = new Cell[20][20];

    public Grid() {
        for(int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(colToLabel(i), j, 10+35*i, 10+35*j);
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