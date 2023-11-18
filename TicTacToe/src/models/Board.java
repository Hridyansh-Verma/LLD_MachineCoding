package models;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int dimensions;

    public int getDimensions() {
        return dimensions;
    }

    private List<List<Cell>> cells;

    public List<List<Cell>> getCells() {
        return cells;
    }

    public Board(int dimensions)
    {
        this.dimensions=dimensions;
        cells=new LinkedList<>();
        for(int i=0;i<dimensions;i++)
        {
            List<Cell> cellRow = new LinkedList<>();
            for(int j=0;j<dimensions;j++)
            {
                cellRow.add(new Cell(i,j));
            }
            cells.add(cellRow);
        }
    }
    public void displayBoard() {
        //Print the board.
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.size(); j++) {
                if (cells.get(i).get(j).getCellState()==CellState.EMPTY) {
                    System.out.print("|  |");
                } else {
                    System.out.print("|" +
                            cells.get(i).get(j)
                                    .getPlayer().getPlayerSymbol() + "|");
                }
            }
            System.out.println();
        }
    }

    public void applyMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        cells.get(row).get(col)
                .setCellState(CellState.FILLED);
        cells.get(row).get(col)
                .setPlayer(move.getPlayer());
    }
}
